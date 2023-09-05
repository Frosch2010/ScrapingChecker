import android.util.Log
import com.frosch2010.scrapingchecker.exceptions.InvalidUrlFormatException
import com.frosch2010.scrapingchecker.interfaces.IScrapingPermissionService
import com.frosch2010.scrapingchecker.models.ScrapingPermissionResult
import com.frosch2010.scrapingchecker.utils.UrlUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Service for checking scraping permission based on robots.txt content.
 */
class ScrapingPermissionService(private val userAgents: List<String>): IScrapingPermissionService {

    private val urlUtils = UrlUtils()

    override suspend fun isScrapingAllowed(url: String): ScrapingPermissionResult {
        try {
            val baseUrl = urlUtils.getBaseUrl(url) ?: throw InvalidUrlFormatException("Invalid URL format")
            val pathUrl = urlUtils.getPathUrl(url) ?: throw InvalidUrlFormatException("Invalid URL format")

            val robotsTxtContent = fetchRobotsTxtContent(baseUrl)
            if (robotsTxtContent.isBlank()) {
                return ScrapingPermissionResult(false, "Failed to fetch robots.txt content")
            }

            val directives = parseRobotsTxt(robotsTxtContent)

            val userAgentsToCheck = mutableListOf("*")
            if(userAgents.isNotEmpty()) {
                userAgentsToCheck.addAll(userAgents)
            }

            val isAllowed = checkUserAgentDirectives(directives, userAgentsToCheck, pathUrl)

            return ScrapingPermissionResult(isAllowed)
        } catch (e: InvalidUrlFormatException) {
            return ScrapingPermissionResult(false, e.message)
        } catch (e: IllegalArgumentException) {
            return ScrapingPermissionResult(false, e.message)
        } catch (e: IOException) {
            return ScrapingPermissionResult(false, "Failed to fetch robots.txt content")
        }
    }

    /**
     * Fetches the content of robots.txt from the specified base URL.
     *
     * @param baseUrl The base URL to fetch the robots.txt content from.
     * @return The content of robots.txt.
     * @throws IOException If there is an error fetching the content.
     */
    private suspend fun fetchRobotsTxtContent(baseUrl: String): String {
        val robotsTxtUrl = "$baseUrl/robots.txt"

        return try {
            val url = URL(robotsTxtUrl)
            val connection = withContext(Dispatchers.IO) {
                url.openConnection()
            } as HttpURLConnection
            connection.requestMethod = "GET"

            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))

            val content = StringBuilder()
            reader.useLines { lines ->
                lines.forEach { line ->
                    content.append(line).append("\n")
                }
            }

            content.toString()
        } catch (e: Exception) {
            Log.e("ScrapingPermissionService", "Failed to fetch robots.txt content", e)
            throw IOException("Failed to fetch robots.txt content")
        }
    }

    /**
     * Parses the content of robots.txt and extracts directives.
     *
     * @param robotsTxtContent The content of robots.txt to parse.
     * @return A map of user agents to their corresponding directives.
     */
    private fun parseRobotsTxt(robotsTxtContent: String): Map<String, List<String>> {
        val directives = mutableMapOf<String, MutableList<String>>()
        val lines = robotsTxtContent.lines()

        var currentUserAgent: String? = null

        for (line in lines) {
            if (line.startsWith("User-agent:", ignoreCase = true)) {
                currentUserAgent = line.substringAfter("User-agent:", "").trim()
                directives[currentUserAgent] = mutableListOf()
            } else if (line.startsWith("Disallow:", ignoreCase = true)) {
                val disallowedPath = line.substringAfter("Disallow:", "").trim()
                if (currentUserAgent != null) {
                    directives[currentUserAgent]?.add(disallowedPath)
                }
            }
        }

        return directives
    }

    /**
     * Checks the user agent directives to determine scraping permission.
     *
     * @param directives The map of user agents to their corresponding directives.
     * @param userAgentsToCheck The list of user agents to check directives for.
     * @return True if scraping is allowed, false otherwise.
     */
    private fun checkUserAgentDirectives(directives: Map<String, List<String>>, userAgentsToCheck: List<String>, urlPath: String): Boolean {
        for (userAgent in userAgentsToCheck) {
            if (directives.containsKey(userAgent)) {
                val disallowedPaths = directives[userAgent] ?: emptyList()
                for (path in disallowedPaths) {
                    if (path.isNotEmpty() && path == "/" || urlPath.startsWith(path)) {
                        return false // Scraping not allowed
                    }
                }
            }
        }
        return true // Scraping is allowed
    }
}
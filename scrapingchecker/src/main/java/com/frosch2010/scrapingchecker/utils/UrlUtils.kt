package com.frosch2010.scrapingchecker.utils

import java.net.URI
import java.net.URISyntaxException

/**
 * Utility class for working with URLs.
 */
class UrlUtils {

    /**
     * Extracts the base URL from a given URL.
     *
     * @param url The URL to extract the base URL from.
     * @return The base URL, or null if the URL format is invalid.
     */
    fun getBaseUrl(url: String): String? {
        val uri = try {
            URI(url)
        } catch (e: URISyntaxException) {
            return null
        }
        return "${uri.scheme}://${uri.host}"
    }

    /**
     * Extracts the path URL from a given URL.
     *
     * @param url The URL to extract the path URL from.
     * @return The path URL, or null if the URL format is invalid.
     */
    fun getPathUrl(url: String): String? {
        val uri = try {
            URI(url)
        } catch (e: URISyntaxException) {
            return null
        }
        return uri.path
    }
}
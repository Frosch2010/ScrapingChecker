package com.frosch2010.scrapingchecker.utils

import com.frosch2010.scrapingchecker.interfaces.IUrlUtils
import java.net.URI
import java.net.URISyntaxException

/**
 * Utility class for working with URLs.
 */
class UrlUtils: IUrlUtils {

    override fun getBaseUrl(url: String): String? {
        val uri = try {
            URI(url)
        } catch (e: URISyntaxException) {
            return null
        }

        if(uri.scheme == null || uri.host == null) {
            return null
        }

        return "${uri.scheme}://${uri.host}"
    }

    override fun getPathUrl(url: String): String? {
        val uri = try {
            URI(url)
        } catch (e: URISyntaxException) {
            return null
        }

        if(uri.scheme == null || uri.host == null || uri.path == null) {
            return null
        }

        return uri.path
    }
}
package com.frosch2010.scrapingchecker.interfaces

interface IUrlUtils {
    /**
     * Extracts the base URL from a given URL.
     *
     * @param url The URL to extract the base URL from.
     * @return The base URL, or null if the URL format is invalid.
     */
    fun getBaseUrl(url: String): String?
    /**
     * Extracts the path URL from a given URL.
     *
     * @param url The URL to extract the path URL from.
     * @return The path URL, or null if the URL format is invalid.
     */
    fun getPathUrl(url: String): String?
}
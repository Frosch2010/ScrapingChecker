package com.frosch2010.scrapingchecker.interfaces

import com.frosch2010.scrapingchecker.models.ScrapingPermissionResult

interface IScrapingPermissionService {

    /**
     * Checks if scraping is allowed for the given URL.
     *
     * @param url The URL to check scraping permission for.
     * @return A [ScrapingPermissionResult] indicating whether scraping is allowed and any error message.
     */
    suspend fun isScrapingAllowed(url: String): ScrapingPermissionResult
}
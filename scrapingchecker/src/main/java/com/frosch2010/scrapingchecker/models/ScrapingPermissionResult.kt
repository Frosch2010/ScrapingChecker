package com.frosch2010.scrapingchecker.models

/**
 * Represents the result of a scraping permission check.
 *
 * @property isAllowed Indicates whether scraping is allowed (true) or not (false).
 * @property errorMessage An optional error message if scraping is not allowed due to an error.
 */
data class ScrapingPermissionResult(
    val isAllowed: Boolean,
    val errorMessage: String? = null
)
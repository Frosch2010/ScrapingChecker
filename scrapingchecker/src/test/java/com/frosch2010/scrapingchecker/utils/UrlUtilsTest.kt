package com.frosch2010.scrapingchecker.utils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class UrlUtilsTest {

    private lateinit var urlUtils: UrlUtils

    @Before
    fun setUp() {
        urlUtils = UrlUtils()
    }

    @Test
    fun testGetBaseUrlValidUrl() {
        val url = "https://www.example.com/path/to/resource"
        val baseUrl = urlUtils.getBaseUrl(url)
        assertEquals("https://www.example.com", baseUrl)
    }

    @Test
    fun testGetBaseUrlInvalidUrl() {
        val url = "invalid-url"
        val baseUrl = urlUtils.getBaseUrl(url)
        assertNull(baseUrl)
    }

    @Test
    fun testGetPathUrlValidUrl() {
        val url = "https://www.example.com/path/to/resource"
        val pathUrl = urlUtils.getPathUrl(url)
        assertEquals("/path/to/resource", pathUrl)
    }

    @Test
    fun testGetPathUrlInvalidUrl() {
        val url = "invalid-url"
        val pathUrl = urlUtils.getPathUrl(url)
        assertNull(pathUrl)
    }
}

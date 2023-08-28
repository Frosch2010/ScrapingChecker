package com.frosch2010.scrapingchecker.exceptions

/**
 * Exception thrown when the format of a URL is invalid.
 *
 * @param message The error message.
 * @param cause The cause of the exception.
 */
class InvalidUrlFormatException(message: String, cause: Throwable? = null) : Exception(message, cause)

[![](https://jitpack.io/v/Frosch2010/ScrapingChecker.svg)](https://jitpack.io/#Frosch2010/ScrapingChecker) [![](https://jitpack.io/v/Frosch2010/ScrapingChecker/month.svg)](https://jitpack.io/#Frosch2010/ScrapingChecker)
# ScrapingChecker

An Android library for simplifying web scraping permission validation using robots.txt parsing.

## Overview

ScrapingChecker is designed to streamline the process of validating web scraping permissions by parsing the content of robots.txt files. It offers a simple and convenient way to determine whether scraping is allowed for a given URL, making ethical data extraction from websites easier.

## Features

- Parsing and interpretation of robots.txt directives.
- Checking user-agent-specific permissions.
- Handling exceptions and error cases.

## Installation

Add ScrapingChecker to your Android project using Gradle:

```gradle
implementation 'com.github.frosch2010:ScrapingChecker:1.0.2'
```

## Usage

### Checking Scraping Permission

You can use the `ScrapingPermissionService` to determine whether scraping is allowed for a given URL. Here's an example of how to use it:

```kotlin
// Use lifecycleScope.launch with Dispatchers.IO to run in the IO context
lifecycleScope.launch(Dispatchers.IO) {
    // Create a ScrapingPermissionService instance with the desired user agents
    val scrapingPermissionService = ScrapingPermissionService(emptyList()) // checks for '*'

    // or if you want to check for specific user agents:
    // val scrapingPermissionService = ScrapingPermissionService(listOf("Googlebot"))
    
    // Specify the URL you want to check for scraping permission
    val urlToCheck = "https://example.com"
    
    // Call the isScrapingAllowed function to check permission
    val result = scrapingPermissionService.isScrapingAllowed(urlToCheck)

    if (result.isAllowed) {
        // Scraping is allowed for the given URL by the specified user agent(s)
        // You can perform scraping operations here
    } else {
        // Scraping is not allowed for the given URL by the specified user agent(s)
        // Check result.errorMessage for more details on the restriction
    }
}
```

## Changelog

**1.0.2**

- added sample
- added unit tests for urlUtils
- fixed issues

**1.0.1**

- Initial release

## License

    Copyright 2023, Frosch2010

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

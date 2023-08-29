# ScrapingChecker

**ScrapingChecker** - An Android library for simplifying web scraping permission validation using robots.txt parsing.

## Overview

ScrapingChecker is designed to streamline the process of validating web scraping permissions by parsing the content of robots.txt files. It offers a simple and convenient way to determine whether scraping is allowed for a given URL, making ethical data extraction from websites easier.

## Features

- Parsing and interpretation of robots.txt directives.
- Checking user-agent-specific permissions.
- Handling exceptions and error cases.

## Installation

Add ScrapingChecker to your Android project using Gradle:

```gradle
implementation 'com.github.frosch2010:ScrapingChecker:1.0.1
```

## Usage

### Checking Scraping Permission
```kotlin
lifecycleScope.launch(Dispatchers.IO) { // Use Dispatchers.IO to run in the IO context
    val scrapingPermissionService = ScrapingPermissionService()
    val result = scrapingPermissionService.isScrapingAllowed("https://example.com", "MyUserAgent")

    if (result.isAllowed) {
        // Scraping is allowed
    } else {
        // Scraping is not allowed, check result.errorMessage for details
    }
}
```

## Changelog

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

# Fake News
[![Maintainability](https://api.codeclimate.com/v1/badges/bac7d09d044e95a0667c/maintainability)](https://codeclimate.com/github/Jak-Sidious/FakeNews/maintainability)   [![CircleCI](https://circleci.com/gh/Jak-Sidious/FakeNews/tree/master.svg?style=svg)](https://circleci.com/gh/Jak-Sidious/FakeNews/tree/master)


Fake News is an application that focuses on utilisation of the [NewsApi](https://newsapi.org/) to display news from the various sources that are provided therein as well as providing additoinal navigational functionality to cater to the use cases beyond the display of news sources. 

## Getting Started
This project is built with Gradle, the [Android Gradle Plugin](https://developer.android.com/studio/releases/gradle-plugin) 
Follow the steps below to setup the project locally

* Install `Java JDK` `version 8`
* Clone [FakeNews](https://github.com/Jak-Sidious/FakeNews) into your working folder
* Generate an apikey from [NewsApi](https://newsapi.org/register)
* Generate a variable inside the gradle.properties file called `apiKey` that you shall use your api-key
* Start Android Studio
* Select OpenProject and select the generated root project folder from your git clone
* Once the project has compiled -> RUn the project

## Installing the Pre-commit Hook
There are two ways to do this the first being accessing the `Gradle` menu on the right hand side of the IDE and then clicking on the `git hooks` section, followed by clicking the function `installGitHookls`

Additionally, the command ```./gradlew installGitHooks``` can be run from inside the terminal top ensure that the pre-commit hook is successfully installed.

## Running Tests
> ./gradlew connectedCheck

This command runs both Insturmentation and Unit Tests that are collectively reported by jacoco
![Application Coverage](https://github.com/Jak-Sidious/FakeNews/blob/ch-167417002-Modify-ReadMe/app/src/main/res/drawable/coverage.png)


## Build variants
Use the Android Studio *Build Variants* button to choose between **debug** and **release** flavors combined with debug and release build types

## Consumed Endpoints
Fake news utilises the following end points 
* [Sources](https://newsapi.org/v2/sources?apiKey=API_KEY) All News sources
* [Everything](https://newsapi.org/v2/everything?q=newsSource&apiKey=API_KEY) All Data from a particular news source
* [Serach Query](https://newsapi.org/everything?q=SearchQuery&language=en&sortby=publishedAt&apiKey=API_KEY) all news about a search query with the most recent first.

## Maintainers
This project is mantained by:
* [Jakana Kiwanuka](https://github.com/Jak-Sidious)


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request

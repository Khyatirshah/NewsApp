# News Reader App

This is a simple News Reader Android application built using **Jetpack Compose**, **MVVM** architecture, and **LiveData** for observing data changes. The app allows users to browse news articles, view details, bookmark articles, and filter articles by category.

## Features

- **Browse News Articles**: Fetch top headlines from a news API and display them in a scrollable list.
- **View Article Details**: Click on an article to view its full details on a separate screen.
- **Bookmark Articles**: Save your favorite articles locally for easy access.
- **Filter by Category**: Filter the news articles based on selected categories.
- **SQLite Database**: Store bookmarked articles using SQLite with a custom `ArticleDao` class.

## Technologies

- **Kotlin**
- **Jetpack Compose**: UI components are created using Jetpack Compose.
- **MVVM Architecture**: The app uses a Model-View-ViewModel (MVVM) architecture.
- **SQLite**: Used for storing and retrieving bookmarked articles.
- **Coroutines**: For managing background threads.
- **Retrofit**: For making network requests to fetch the news.
- **Navigation Component**: For navigating between screens in the app.

## Getting Started

### Prerequisites

- Android Studio (latest version)
- Kotlin 1.6+
- Basic knowledge of Jetpack Compose and MVVM architecture


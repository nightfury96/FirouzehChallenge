# 📰 Firouzeh News App

A modern Android news application built for the **Firouzeh Android Challenge**. This app displays top headlines about Microsoft, Apple, Google, and Tesla in a clean, modular, and maintainable architecture — with offline support and modern UI using Jetpack Compose.

---

## 🚀 Features

- 🔍 Fetches news articles related to:
    - Microsoft
    - Apple
    - Google
    - Tesla
- 📅 Time range: From **yesterday to today**
- 🔄 Articles are shown in a **round-robin pattern**:
    - Microsoft → Apple → Google → Tesla → repeat
- 🖼️ Card UI with:
    - Title
    - Thumbnail image
    - Brief description
    - Query tag (e.g., Microsoft)
    - Publication date (formatted)
- 📖 Detail screen with:
    - Title, full content or summary
    - Image
    - Author and source
    - Human-readable date
- 🗂️ Caches articles using **Room** for offline viewing
- 📦 Fully modular and Clean Architecture:
    - `domain`, `data`, `presentation`, `app`

---

## 🧱 Architecture Overview

### Clean + Modular
firouzehnews/

├── app/              # MainActivity, Hilt setup, entry point

├── domain/           # Entities, interfaces, use cases (no frameworks)

├── data/             # Retrofit, Room, repository implementations

├── presentation/     # ViewModel, Compose UI, Navigation, Theme

### Key Libraries

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Coil](https://coil-kt.github.io/coil/)

---

## 🛠️ Setup & Run

### 1. Clone the repository

```bash
git clone https://github.com/nightfury96/firouzeh-news.git
cd firouzeh-news


### 2. Add your [NewsAPI](https://newsapi.org/) key to local.properties
NEWS_API_KEY=your_api_key_here

### 3. Build & Run
./gradlew clean build
or from Android Studio: Run > app
```


## ✨ Potential Future Enhancements
	•	🧩 Pagination support for large result sets
	•	🌐 Query filters (e.g., country, language, source)
	•	🗓️ Date picker for custom ranges
	•	🔎 Search within news results
	•	📥 Download & save articles
	•	🛠️ Dark theme support
	•	🧪 UI tests and repository mocking

# A program written entirely in Kotlin using the principles of Clean Architecture according to the MVVM pattern.

Application for getting weather for every day. Written in Kotlin using the Jetpack Compose framework

## Screenshots

[<img src="meta/screenshots/screenshot_first.png" width=160>](meta/screenshots/screenshot_first.png)
[<img src="meta/screenshots/screenshot_second.png" width=160>](meta/screenshots/screenshot_second.png)

## Libraries

* [Jetpack Compose](https://developer.android.com/jetpack/compose) Jetpack Compose is Android’s
  recommended modern toolkit for building native UI. It simplifies and accelerates UI development on
  Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.

* [Kotlin flows](https://developer.android.com/kotlin/flow) In coroutines, a flow is a type that can
  emit multiple values sequentially, as opposed to suspend functions that return only a single
  value. For example, you can use a flow to receive live updates from a database.

* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) Coroutines is a rich library for
  coroutines developed by JetBrains. It contains a number of high-level primitives with support for
  coroutines, which are discussed in this guide, including startup, asynchrony, and others.

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Data related to
  the user interface that is not destroyed when the application is rotated. Easily schedule
  asynchronous tasks for optimal execution.

* [Room](https://developer.android.com/jetpack/androidx/releases/room) The Room persistence library
  provides an abstraction layer over SQLite to allow for more robust database access while
  harnessing the full power of SQLite.

* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) An interface
  that automatically responds to lifecycle events.

* [Compose-Destinations](https://github.com/raamcosta/compose-destinations?ysclid=llpa5lprz4866685466)
  A KSP library that processes annotations and generates code that uses Official Jetpack Compose
  Navigation under the hood. It hides the complex, non-type-safe and boilerplate code you would have
  to write otherwise.
  No need to learn a whole new framework to navigate - most APIs are either the same as with the
  Jetpack Components or inspired by them.

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) Data objects that
  notify views of changes to the underlying database.

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) Hilt is a
  dependency injection library for Android that reduces the execution time of manual dependency
  injection into your project. Performing manual dependency injection requires that you create each
  class and its dependencies manually, and use containers to reuse and manage dependencies.

* [Retrofit2](https://github.com/square/retrofit) Type-safe HTTP client for Android and Java.

* [OkHttp](https://github.com/square/okhttp) HTTP + HTTP/2 client for Android and Java applications.

* [Coil](https://coil-kt.github.io/coil) An image loading library for Android backed by Kotlin Coroutines.

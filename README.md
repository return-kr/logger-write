# The library writes all logs on the application Internal Storage #
## Use of the library ##
### Add library dependency to the app level build.gradle file. ###

```gradle
dependencies {
    implementation 'com.github.return-kr:logger-write:$latest_stable_version'
}
```
### Add the following to the settings.gradle file. ###
```gradle
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
### Initialize the LoggerWrite class inside onCreate method of your Application class. ###
```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val loggerWrite = LoggerWrite()
    }
}
```
### Get the application media directory and set the target directory name using below method. ###
```kotlin
val logDirectory = getOutputDirectory("logcats")

private fun getOutputDirectory(directoryName: String): File {
    val directory = applicationContext.externalMediaDirs.firstOrNull()?.let {
        File(it, directoryName).apply { mkdirs() }
    }
    return if (directory != null && directory.exists())
        directory else applicationContext.filesDir
}
```
### Call the function inside a coroutine on background dispatcher to write the logs in a file. ###
```kotlin
CoroutineScope(Dispatchers.IO).launch {
    loggerWrite.writeLogcat(logDirectory)
}
```
### Set the listener to get the write task callbacks. ###
```kotlin
loggerWrite.addOnLoggerListener(object : OnLoggerListener {
    override fun onLogWriteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onLogWriteFail(e: Exception) {
        TODO("Not yet implemented")
    }
})
```
***End of Doc***
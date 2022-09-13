package com.bhaskar.loggerwrite

import java.io.File

/**
 * This library writes all logcat in a file when the application starts
 *                                                           -- Bhaskar
 */
class LoggerWrite {
    private var onLoggerListener: OnLoggerListener ? = null

    /**
     * Listener for logs write task callbacks
     */
    fun addOnLoggerListener(onLoggerListener: OnLoggerListener) {
        this.onLoggerListener = onLoggerListener
    }

    /**
     * Function which initiates the writing task for all logs of the application
     * Run it using Coroutines
     */
    fun writeLogcat(logDirectory: File) {
        val logFile = File("$logDirectory/logs.txt")
        if (logFile.exists()) {
            logFile.delete()
        }
        val cmd = "logcat -f$logFile"
        try {
            Runtime.getRuntime().exec(cmd)
            onLoggerListener?.onLogWriteSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            onLoggerListener?.onLogWriteFail(e)
        }
    }
}
package com.bhaskar.loggerwrite

interface OnLoggerListener {
    fun onLogWriteSuccess()
    fun onLogWriteFail(e: Exception)
}
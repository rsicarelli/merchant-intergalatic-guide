package br.com.rsicarelli.merchantintergalaticguide.extensions

fun String.splitByBlankSpace(): List<String> = this.split("\\s".toRegex(), limit = 0)

fun String.lastString() = this.substring(this.lastIndexOf(" ") + 1)

fun String.firstString() = this.substring(0, this.indexOf(' '))

fun String.removeSuffixAndTrim(suffix: String) = this.removeSuffix(suffix).trim()



package android.ptc.com.ptcflixing.util

fun Int.priceFormatter(symbol: String, seperation: String): String {
    return String.format("$symbol %${seperation}d", this)
}

fun Int.savingFormatter(): String {
    return String.format("-%d%%", this)
}
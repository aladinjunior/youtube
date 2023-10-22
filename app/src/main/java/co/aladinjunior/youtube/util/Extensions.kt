package co.aladinjunior.youtube.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatted() : String {
    return SimpleDateFormat("d MM yyyy", Locale("pt", "BR")).format(this)
}
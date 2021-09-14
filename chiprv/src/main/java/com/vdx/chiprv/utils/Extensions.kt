package com.vdx.chiprv.utils

import android.graphics.Color
import androidx.core.graphics.ColorUtils

fun String.changeTextToInitials(): String {
    val text: String? = this
    try {
        return if (text != null && text.isNotEmpty()) {
            if (text.length <= 2) {
                return text
            }
            val split = text.split(" ").toTypedArray()
            var result = split[0][0].toString()
            if (split.size >= 2) {
                result += split[1][0].toString()
            }
            result
        } else {
            ""
        }
    } catch (ignored: Exception) {
    }
    return ""
}

fun Int.getUniqueColor(text: String): Int {
    val opacity = "#dd"
    var cn = text.trim { it <= ' ' }.replace("\\s{2,}".toRegex(), " ")
    cn += cn.uppercase()
    val hexColor = String.format(
        "$opacity%06X", 0xFFFFFF and cn.hashCode()
    )
    return try {
        if (!isDark(Color.parseColor(hexColor))) {
            Color.parseColor("#1033DD")
        } else Color.parseColor(hexColor)
    } catch (e: java.lang.Exception) {
        Color.parseColor("#1033DD")
    }
}

private fun isDark(color: Int): Boolean {
    return ColorUtils.calculateLuminance(color) < 0.5
}

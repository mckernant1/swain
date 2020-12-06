package com.github.mckernant1.lol.swain.output

import com.beust.klaxon.Klaxon
import java.io.File
import java.io.Serializable

fun printData(data: Serializable, format: Format, output: String?) {
    val klaxon = Klaxon()
    val printable = when (format) {
        Format.JSON -> klaxon.toJsonString(data)
        else -> throw UnsupportedOperationException("Format '$format' is not valid")
    }

    if (output != null) {
        File(output).writeText(printable)
    } else {
        println(printable)
    }
}


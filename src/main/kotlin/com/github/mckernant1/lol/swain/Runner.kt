package com.github.mckernant1.lol.swain

import kotlinx.cli.ExperimentalCli
import kotlin.system.exitProcess

@ExperimentalCli
fun main(args: Array<String>) {
    try {
        createParser().parse(args)
    } catch (e: Exception) {
        System.err.println(e.message ?: "An unexpected error occurred")
        exitProcess(1)
    }
}

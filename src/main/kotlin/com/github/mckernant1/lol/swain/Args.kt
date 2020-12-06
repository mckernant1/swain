package com.github.mckernant1.lol.swain

import com.github.mckernant1.lol.swain.commands.PlayerCommand
import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli

@ExperimentalCli
fun createParser(): ArgParser {
    val parser = ArgParser("swain")
    parser.subcommands(PlayerCommand())
    return parser
}

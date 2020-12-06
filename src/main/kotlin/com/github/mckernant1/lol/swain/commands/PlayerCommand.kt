package com.github.mckernant1.lol.swain.commands

import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import java.io.Serializable

@ExperimentalCli
class PlayerCommand : AbstractSubcommand("player", "Gets information about a player") {

    private val inGameName by argument(
        ArgType.String,
        description = "The Players In Game Name"
    )

    override fun fetchData(): Serializable {
        return teamClient.getAllTeams().flatMap { it.players }.find { it.summonerName == inGameName } ?: error("Player is not on any team")
    }
}

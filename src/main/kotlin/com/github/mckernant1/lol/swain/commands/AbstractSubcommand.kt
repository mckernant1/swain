package com.github.mckernant1.lol.swain.commands

import com.github.mckernant1.lol.heimerdinger.config.EsportsApiConfig
import com.github.mckernant1.lol.heimerdinger.games.GameClient
import com.github.mckernant1.lol.heimerdinger.leagues.LeagueClient
import com.github.mckernant1.lol.heimerdinger.schedule.ScheduleClient
import com.github.mckernant1.lol.heimerdinger.team.TeamClient
import com.github.mckernant1.lol.heimerdinger.tournaments.TournamentClient
import com.github.mckernant1.lol.swain.output.Format
import com.github.mckernant1.lol.swain.output.LogLevel
import com.github.mckernant1.lol.swain.output.printData
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.config.Configurator
import java.io.Serializable

@ExperimentalCli
abstract class AbstractSubcommand(name: String, actionDescription: String) : Subcommand(name, actionDescription) {
    protected val logger: Logger = LogManager.getLogger(this::class.java)

    private val esportsApiConfig = EsportsApiConfig(
        logger = {
            logger.debug(it)
        }
    )

    protected val leagueClient by lazy { LeagueClient(esportsApiConfig) }
    protected val teamClient by lazy { TeamClient(esportsApiConfig) }
    protected val gameClient by lazy { GameClient(esportsApiConfig) }
    protected val scheduleClient by lazy { ScheduleClient(esportsApiConfig) }
    protected val tournamentClient by lazy { TournamentClient(esportsApiConfig) }


    private val format by option(
        ArgType.Choice<Format>(),
        shortName = "f",
        description = "Format for output"
    ).default(Format.JSON)

    private val output by option(
        ArgType.String,
        shortName = "o",
        description = "Output file name"
    )

    private val logLevel by option(
        ArgType.Choice<LogLevel>(),
        shortName = "l",
        description = "Specify the log level"
    ).default(LogLevel.ERROR)

    abstract fun fetchData(): Serializable

    override fun execute() {
        Configurator.setRootLevel(logLevel.level)
        val serializableData = fetchData()
        printData(serializableData, format, output)
    }

}

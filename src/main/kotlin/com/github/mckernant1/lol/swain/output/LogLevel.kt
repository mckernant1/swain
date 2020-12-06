package com.github.mckernant1.lol.swain.output

import org.apache.logging.log4j.Level

enum class LogLevel(val level: Level) {
    DEBUG(Level.DEBUG),
    WARN(Level.WARN),
    ERROR(Level.ERROR),
    INFO(Level.INFO)
}

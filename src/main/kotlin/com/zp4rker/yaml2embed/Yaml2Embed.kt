package com.zp4rker.yaml2embed

import com.zp4rker.disbot.Bot
import com.zp4rker.disbot.bot
import net.dv8tion.jda.api.requests.GatewayIntent

/**
 * @author zp4rker
 */

fun main(args: Array<String>) {
    bot {
        name = "Yaml2Embed"
        version = Bot::class.java.`package`.implementationVersion

        token = args[0]
        prefix = "+"

        intents = GatewayIntent.GUILD_MESSAGES.rawValue

        commands = arrayOf(PostCommand)
    }
}
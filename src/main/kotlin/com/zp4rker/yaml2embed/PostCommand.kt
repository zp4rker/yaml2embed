package com.zp4rker.yaml2embed

import com.zp4rker.disbot.command.Command
import com.zp4rker.disbot.extenstions.embed
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author zp4rker
 */
object PostCommand : Command(aliases = arrayOf("post"), minArgs = 2, mentionedChannels = 1, permission = Permission.MESSAGE_MANAGE) {

    override fun handle(args: Array<String>, message: Message, channel: TextChannel) {
        val fileName = args[0].let { "$it${if (it.endsWith(".yml")) "" else ".yml"}" }
        val file = File(fileName)

        if (!file.exists()) {
            channel.sendMessage(embed {
                title { text = "Unable to find file!" }

                description = "Could not find file: `$fileName`"

                color = "#ec644b"
            }).queue { it.delete().queueAfter(3, TimeUnit.SECONDS) }
            return
        }

        val data = YamlEmbed(Yaml().load(file.inputStream()))

        val embed = embed {
            data.author?.let {
                author {
                    if (it.containsKey("name")) name = it["name"].toString()
                    if (it.containsKey("iconUrl")) iconUrl = it["iconUrl"].toString()
                    if (it.containsKey("url")) url = it["url"].toString()
                }
            }

            data.title?.let {
                title {
                    if (it.containsKey("text")) text = it["text"].toString()
                    if (it.containsKey("url")) url = it["url"].toString()
                }
            }

            data.description?.let { description = it }

            data.footer?.let {
                footer {
                    if (it.containsKey("text")) text = it["text"].toString()
                    if (it.containsKey("iconUrl")) iconUrl = it["iconUrl"].toString()
                }
            }

            data.fields?.forEach {
                field {
                    if (it.containsKey("name")) name = it["name"].toString()
                    if (it.containsKey("value")) value = it["value"].toString()
                    if (it.containsKey("inline")) inline = it["inline"].toString().toBoolean()
                }
            }

            data.color?.let { color = it }

            data.thumbnail?.let { thumbnail = it }

            data.image?.let { image = it }
        }

        message.mentionedChannels[0].sendMessage(embed).queue()
    }

}
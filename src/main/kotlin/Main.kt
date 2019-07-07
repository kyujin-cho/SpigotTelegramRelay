package dev.kyujin.spigottelegramrelay

import me.ivmg.telegram.Bot
import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.text
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.plugin.ApiVersion
import org.bukkit.plugin.java.annotation.plugin.Description
import org.bukkit.plugin.java.annotation.plugin.Plugin
import org.bukkit.plugin.java.annotation.plugin.Website
import org.bukkit.plugin.java.annotation.plugin.author.Author
import java.util.logging.Level

@Plugin(name="SpigotTelegramRelay", version = "0.0.1")
@Description("Broadcasts messages from Minecraft and Telegram, bi-directionally")
@ApiVersion(ApiVersion.Target.v1_13)
@Author("Kyujin Cho")
@Website("blog.r3mark.xyz")
public class Main: JavaPlugin() {
    val bot: Bot
    val conf = config
    val API_KEY: String
    val roomIds: List<Long>

    init {
        API_KEY = conf.getString("Telegram.APIKey") ?: throw NoConfigException("Telegram.APIKey not provided")
        roomIds = conf.getLongList("Telegram.TargetRoomIds")
        if (roomIds.isEmpty()) {
            throw NoConfigException("Telegram.TargetRoomIds not provided")
        }
        bot = bot {
            token = API_KEY
            dispatch {
                text { bot, update ->
                    val text = update.message?.text ?: return@text
                    val from = update.message?.from ?: return@text

                    logger.log(Level.INFO, "Got Message $text from ${from.firstName}")

                    if (server.onlinePlayers.isNotEmpty())
                        server.broadcastMessage("<TG> ${from.firstName} ${from.lastName?:""} : $text")
                    else
                        logger.log(Level.INFO, "Server empty; Skipping relay to minecraft chat")
                }
            }
        }
    }

    override fun onEnable() {
        super.onEnable()
        bot.startPolling()
        server.pluginManager.registerEvents(MessageListener(bot, roomIds[0]), this)
    }

    override fun onDisable() {
        super.onDisable()
    }
}

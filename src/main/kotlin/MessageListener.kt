package dev.kyujin.spigottelegramrelay

import me.ivmg.telegram.Bot
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class MessageListener(val bot: Bot, val roomId: Long): Listener {
    @EventHandler
    fun playerChat(e: AsyncPlayerChatEvent) {
        bot.sendMessage(chatId = roomId, text = "<MC> ${e.player.name}: ${e.message}")
    }
}
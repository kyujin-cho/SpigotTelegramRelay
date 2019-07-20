# Spigot-Telegram Chat Relay Plugin
Simple plugin which relays chat between Spigot Minecraft Server and Telegram Chat, written in Kotlin.    
All text messages on specified Telegram chatroom/channel will be relayed to Minecraft Chat with prefix \<TG>, and vice versa (with prefix \<MC>).

## Installation
1. Download plugin JAR file from [Release](https://github.com/kyujin-cho/SpigotTelegramRelay/releases) tab
2. Save it to Spigot `plugins` folder
3. Create `SpigotTelegramRelay` inside `plugins` folder, then create `config.yml` file inside it.   
User-specific options (Bot API key, target channel ID) should be specified here, formatted like `config.yml.template` in this repository. Note that messages from Minecraft Chat will be relayed only to first chat id of `TargetRoomIds` list.
4. Start server as usual!



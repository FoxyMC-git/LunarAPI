package xyz.selyu.lunar

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener
import xyz.selyu.lunar.commands.EmoteCommand
import xyz.selyu.lunar.commands.LCTitleCommand
import xyz.selyu.lunar.listeners.PlayerListeners

class LunarAPI : JavaPlugin(), PluginMessageListener {

    companion object {
        lateinit var instance: LunarAPI
    }

    override fun onEnable() {
        instance = this

        logger.info("LunarAPI successfully enabled")

        server.messenger.registerOutgoingPluginChannel(this, "Lunar-Client")
        server.messenger.registerIncomingPluginChannel(this, "Lunar-Client", this)
        server.getPluginCommand("emote").executor = EmoteCommand()
        server.getPluginCommand("lctitle").executor = LCTitleCommand()
        server.pluginManager.registerEvents(PlayerListeners(), this)
    }

    override fun onPluginMessageReceived(p0: String?, p1: Player?, p2: ByteArray?) {
        //
    }
}
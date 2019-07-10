package xyz.selyu.lunar.packet

import org.bukkit.entity.Player
import xyz.selyu.lunar.LunarAPI
import java.io.ByteArrayOutputStream

interface LCPacket {
    fun send(player: Player)

    fun send0(player: Player, data: ByteArrayOutputStream) {
        player.sendPluginMessage(LunarAPI.instance, "Lunar-Client", data.toByteArray())
    }
}
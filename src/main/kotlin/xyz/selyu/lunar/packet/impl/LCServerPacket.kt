package xyz.selyu.lunar.packet.impl

import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.LCPacket
import xyz.selyu.lunar.util.writeString
import java.io.ByteArrayOutputStream

class LCServerPacket(private val server: String) : LCPacket {

    override fun send(player: Player) {
        val outputStream = ByteArrayOutputStream()

        outputStream.write(11)
        outputStream.write(writeString(this.server))

        outputStream.close()

        send0(player, outputStream)
    }
}
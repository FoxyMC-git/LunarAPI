package xyz.selyu.lunar.packet.impl

import org.bukkit.Material
import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.LCPacket
import xyz.selyu.lunar.util.writeInt
import xyz.selyu.lunar.util.writeLong
import xyz.selyu.lunar.util.writeString
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

class LCCooldownPacket(private val msg: String, private val duration: Int,
                       private val material: Material) : LCPacket {

    override fun send(player: Player) {
        val outputStream = ByteArrayOutputStream()

        outputStream.write(3)

        outputStream.write(writeString(msg))
        outputStream.write(writeLong(TimeUnit.SECONDS.toMillis(duration.toLong())))
        outputStream.write(writeInt(material.id))

        outputStream.close()

        send0(player, outputStream)
    }
}
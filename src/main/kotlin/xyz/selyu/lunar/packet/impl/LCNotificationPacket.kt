package xyz.selyu.lunar.packet.impl

import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.LCPacket
import xyz.selyu.lunar.util.writeLong
import xyz.selyu.lunar.util.writeString
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

class LCNotificationPacket(private val msg: String,
                           private val type: Type, private val delay: Int) : LCPacket {

    override fun send(player: Player) {
        val outputStream = ByteArrayOutputStream()

        outputStream.write(9)
        outputStream.write(writeString(msg))
        outputStream.write(writeLong(TimeUnit.SECONDS.toMillis(delay.toLong())))
        outputStream.write(writeString(type.name))

        outputStream.close()

        send0(player, outputStream)
    }

    enum class Type {
        INFO,
        ERROR,
        NEUTRAL
        ;
    }
}
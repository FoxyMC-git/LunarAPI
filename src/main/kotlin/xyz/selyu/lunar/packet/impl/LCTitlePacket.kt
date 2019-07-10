package xyz.selyu.lunar.packet.impl

import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.LCPacket
import xyz.selyu.lunar.util.writeFloat
import xyz.selyu.lunar.util.writeLong
import xyz.selyu.lunar.util.writeString
import java.util.concurrent.TimeUnit
import java.io.ByteArrayOutputStream

class LCTitlePacket(private val sub: Boolean, private val msg: String,
                    private val size: Float, private val duration: Int,
                    private val fadeInTime: Int, private val fadeOutTime: Int) : LCPacket {

    override fun send(player: Player) {
        val outputStream = ByteArrayOutputStream()

        outputStream.write(14)

        outputStream.write(writeString(if (sub) "subtitle" else "normal"))
        outputStream.write(writeString(msg))
        outputStream.write(writeFloat(size))
        outputStream.write(writeLong(TimeUnit.SECONDS.toMillis(duration.toLong())))
        outputStream.write(writeLong(TimeUnit.SECONDS.toMillis(fadeInTime.toLong())))
        outputStream.write(writeLong(TimeUnit.SECONDS.toMillis(fadeOutTime.toLong())))

        outputStream.close()

        send0(player, outputStream)
    }
}
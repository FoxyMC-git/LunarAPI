package xyz.selyu.lunar.packet.impl

import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.LCPacket
import xyz.selyu.lunar.util.uuidToBytes
import org.bukkit.Bukkit
import xyz.selyu.lunar.util.writeInt
import java.io.ByteArrayOutputStream

class LCEmotePacket(private val id: Int) : LCPacket {

    override fun send(player: Player) {
        val outputStream = ByteArrayOutputStream()

        outputStream.write(26)
        outputStream.write(uuidToBytes(player.uniqueId))
        outputStream.write(writeInt(id))

        outputStream.close()

        Bukkit.getOnlinePlayers().forEach {
            send0(it, outputStream)
        }
    }

    enum class LCEmote(id: Int) {

        WAVE(0),
        HANDS_UP(1),
        FLOSS(2),
        DAB(3),
        T_POSE(4),
        SHRUG(5),
        FACEPALM(6)
        ;

        private val id: Int = id

        fun getId(): Int {
            return id
        }
    }
}
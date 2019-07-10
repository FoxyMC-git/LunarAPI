package xyz.selyu.lunar.packet.impl

import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.LCPacket
import xyz.selyu.lunar.util.writeBoolean
import xyz.selyu.lunar.util.writeString
import java.io.ByteArrayOutputStream

class LCStaffModulePacket(private val mod: Module, private val state: Boolean) : LCPacket {

    override fun send(player: Player) {
        val outputStream = ByteArrayOutputStream()

        outputStream.write(12)
        outputStream.write(writeString(mod.name))
        outputStream.write(writeBoolean(state))

        outputStream.close()

        send0(player, outputStream)
    }

    enum class Module {
        XRAY,
        BUNNY_HOP,
        NAME_TAGS,
        NO_CLIP
        ;
    }
}
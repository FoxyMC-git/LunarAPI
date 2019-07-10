package xyz.selyu.lunar.commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.impl.LCEmotePacket
import java.lang.IllegalArgumentException
import java.lang.StringBuilder

class EmoteCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender?, cmd: Command?, label: String?, args: Array<out String>?): Boolean {
        if (sender !is Player) {
            return true
        }

        when (args?.size) {
            0 -> {
                sender.sendMessage("${ChatColor.RED}/emote <emote>")
            }

            1 -> {
                val emote: LCEmotePacket.LCEmote?

                try {
                    emote = LCEmotePacket.LCEmote.valueOf(args[0].toUpperCase())
                } catch (e: IllegalArgumentException) {
                    sender.sendMessage("${ChatColor.RED}The emote ${args[0]} doesn't exist.")

                    val emotesFormatted = StringBuilder()

                    for (e in LCEmotePacket.LCEmote.values()) {
                        emotesFormatted.append("- $e\n")
                    }

                    sender.sendMessage("${ChatColor.RED}All emotes:\n$emotesFormatted")
                    return true
                }

                LCEmotePacket(emote.getId()).send(sender)
            }
        }

        return true
    }
}
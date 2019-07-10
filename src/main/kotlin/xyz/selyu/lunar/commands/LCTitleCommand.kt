package xyz.selyu.lunar.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.selyu.lunar.packet.impl.LCTitlePacket

class LCTitleCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender?, cmd: Command?, label: String?, args: Array<out String>?): Boolean {
        if (sender !is Player) {
            return true
        }

        when (args?.size) {
            0 -> {
                sender.sendMessage("${ChatColor.RED}/lctitle <msg>")
            }

            1 -> {
                Bukkit.getOnlinePlayers().forEach {
                    LCTitlePacket(false, args[0], 1F, 5, 1, 1).send(it)
                }
            }
        }

        return true
    }
}
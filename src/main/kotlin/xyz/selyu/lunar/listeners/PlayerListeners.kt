package xyz.selyu.lunar.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import xyz.selyu.lunar.LunarAPI
import xyz.selyu.lunar.packet.impl.LCCooldownPacket
import xyz.selyu.lunar.packet.impl.LCStaffModulePacket
import xyz.selyu.lunar.packet.impl.LCNotificationPacket
import xyz.selyu.lunar.packet.impl.LCServerPacket

class PlayerListeners : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val plugin = LunarAPI.instance

        plugin.server.scheduler.runTaskLater(plugin, {
            for(i in LCStaffModulePacket.Module.values()) {
                LCStaffModulePacket(i, true).send(e.player)
                LCServerPacket("Zeala Network").send(e.player)
            }
        }, 40L)

        plugin.server.scheduler.runTaskTimer(plugin, {
            LCNotificationPacket("Welcome to Zeala Network", LCNotificationPacket.Type.INFO, 1).send(e.player)
        }, 40L, 40L)

        plugin.server.scheduler.runTaskTimer(plugin, {
            LCCooldownPacket("Example Cooldown", 15, Material.DIAMOND).send(e.player)
        }, 40L, 300L)
    }
}
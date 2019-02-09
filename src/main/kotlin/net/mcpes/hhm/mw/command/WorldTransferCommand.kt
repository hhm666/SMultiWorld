package net.mcpes.hhm.mw.command

import cn.nukkit.Server
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import net.mcpes.hhm.guiapi.NukkitPlayer
import net.mcpes.hhm.mw.gui.WorldGUIManager
import net.mcpes.summit.hhm.base.utils.language.translate

class WorldTransferCommand : Command("w") {
    override fun execute(sender: CommandSender, s: String, strings: Array<out String>): Boolean {
        if (sender !is NukkitPlayer) {
            sender.sendMessage("smultiworld.command.w.mustBePlayer" translate arrayOf())
            return true
        }
        if (strings.size != 1) {
            sender.showGUI(WorldGUIManager.worldTransfer)
            return true
        }
        if (Server.getInstance().isLevelLoaded(strings[0])) {
            sender.teleport(Server.getInstance().getLevelByName(strings[0]).safeSpawn)
            sender.sendMessage("smultiworld.command.w.success" translate arrayOf(strings[0]))
        } else {
            sender.sendMessage("smultiworld.command.w.fail" translate arrayOf(strings[0]))
        }
        return true
    }
}
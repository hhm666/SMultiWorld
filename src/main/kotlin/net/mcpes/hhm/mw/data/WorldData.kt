package net.mcpes.hhm.mw.data

import cn.nukkit.level.Level
import net.mcpes.hhm.mw.SMultiWorld.Companion.worlds
import net.mcpes.hhm.mw.gui.WorldGUIManager
import java.util.function.Consumer

class WorldData(val name: String, val level: Level) {
    init {
        WorldGUIManager.worldTransfer.addButton("smw_α_world_transfer_$name", name, Consumer { it.teleport(level.safeSpawn) })
    }

    fun unload() {
        WorldGUIManager.worldTransfer.delButton("smw_α_world_transfer_$name")
        worlds.remove(name)
    }
}
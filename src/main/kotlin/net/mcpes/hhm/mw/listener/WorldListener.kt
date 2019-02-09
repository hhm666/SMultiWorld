package net.mcpes.hhm.mw.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.level.LevelLoadEvent
import cn.nukkit.event.level.LevelUnloadEvent
import net.mcpes.hhm.mw.SMultiWorld.Companion.worlds
import net.mcpes.hhm.mw.data.WorldData

class WorldListener : Listener {
    @EventHandler
    fun onLoad(event: LevelLoadEvent) {
        worlds[event.level.folderName] = WorldData(event.level.folderName, event.level)
    }

    @EventHandler
    fun onLoad(event: LevelUnloadEvent) {
        worlds[event.level.folderName]?.unload()
    }
}
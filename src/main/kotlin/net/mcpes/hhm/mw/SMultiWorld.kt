package net.mcpes.hhm.mw

import cn.nukkit.Server
import cn.nukkit.level.format.LevelProviderManager
import cn.nukkit.level.format.anvil.Anvil
import cn.nukkit.level.format.leveldb.LevelDB
import cn.nukkit.level.format.mcregion.McRegion
import cn.nukkit.level.generator.Flat
import cn.nukkit.level.generator.Generator
import cn.nukkit.level.generator.Nether
import cn.nukkit.level.generator.Normal
import cn.nukkit.plugin.PluginBase
import net.mcpes.hhm.mw.command.WorldTransferCommand
import net.mcpes.hhm.mw.data.WorldData
import net.mcpes.hhm.mw.listener.WorldListener
import net.mcpes.hhm.mw.utils.WorldUtils
import net.mcpes.summit.hhm.base.utils.language.LanguageUtils
import net.mcpes.summit.hhm.base.utils.language.translate


class SMultiWorld : PluginBase() {
    override fun onLoad() {
        instance = this
        LevelProviderManager.addProvider(Server.getInstance(), Anvil::class.java)
        LevelProviderManager.addProvider(Server.getInstance(), McRegion::class.java)
        LevelProviderManager.addProvider(Server.getInstance(), LevelDB::class.java)

        Generator.addGenerator(Flat::class.java, "flat", Generator.TYPE_FLAT)
        Generator.addGenerator(Normal::class.java, "normal", Generator.TYPE_INFINITE)
        Generator.addGenerator(Normal::class.java, "default", Generator.TYPE_INFINITE)
        Generator.addGenerator(Nether::class.java, "nether", Generator.TYPE_NETHER)
        //todo: add old generator and hell generator
    }

    override fun onEnable() {
        LanguageUtils.load(this, TITLE)
        this.server.pluginManager.registerEvents(WorldListener(), this)
        WorldUtils.loadAllWorld()
        this.server.commandMap.register(this.name, WorldTransferCommand())
        info0("smultiworld.base.enabled" translate arrayOf())
    }

    companion object {
        val worlds = hashMapOf<String, WorldData>()
        const val TITLE = "§l§7|§bS§dMulti§aWorld§cα§7| §6"
        lateinit var instance: SMultiWorld

        @JvmStatic
        fun info0(msg: String) {
            instance.server.logger.info(msg)
        }

        @JvmStatic
        fun info(msg: String) {
            instance.server.logger.info(TITLE + msg)
        }
    }
}
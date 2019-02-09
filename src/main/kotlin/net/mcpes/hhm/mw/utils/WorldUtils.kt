package net.mcpes.hhm.mw.utils

import cn.nukkit.Server
import net.mcpes.hhm.mw.SMultiWorld
import net.mcpes.summit.hhm.base.utils.language.translate
import java.io.File

object WorldUtils {
    @JvmStatic
    fun loadWorld(name: String) {
        if (Server.getInstance().loadLevel(name)) {
            val level = Server.getInstance().getLevelByName(name)
            if (level.name != level.folderName) {
                SMultiWorld.info("smultiworld.utils.load.nameInconsistency" translate arrayOf(name, level.name))
            }
            SMultiWorld.info("smultiworld.utils.load.success" translate arrayOf(name))

        } else {
            SMultiWorld.info("smultiworld.utils.load.failed" translate arrayOf(name))
        }
    }

    @JvmStatic
    fun loadAllWorld() {
        val files = File(Server.getInstance().dataPath + "/worlds/").listFiles() ?: return
        for (file in files) {
            if (file.isDirectory) {
                val dat = File(file.absolutePath + "/level.dat")
                if (dat.exists()) {
                    this.loadWorld(file.name)
                } else {
                    SMultiWorld.info("smultiworld.utils.load.nameInconsistency" translate arrayOf())
                }
            }
        }
    }
}
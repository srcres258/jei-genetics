// SPDX-License-Identifier: LGPL-3.0-or-later

package srcres.mods.jeigenetics

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.Marker
import org.apache.logging.log4j.MarkerManager
import java.io.File

@Mod(modid = JEIGenetics.MOD_ID, name = JEIGenetics.MOD_NAME,
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object JEIGenetics {
    /** The modid of this mod. */
    const val MOD_ID = "jeigenetics"
    /** The name of this mod. */
    const val MOD_NAME = "JEI Genetics"

    /**
     * The logger of this mod obtained from FMLPreInitializationEvent.
     * It can only be used <b>after</b> the FMLPreInitializationEvent is fired for this mod.
     */
    lateinit var logger: Logger
    /** The marker of this mod used when logging a message which is apt to belong to the mod's category. */
    val marker: Marker = MarkerManager.getMarker("mod_main")
    /**
     * The directory containing the configuration file of the mod.
     * It can only be used <b>after</b> the FMLPreInitializationEvent is fired for this mod.
     */
    lateinit var configDir: File

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        logger = event.modLog // Obtain the mod logger.
        logger.debug(marker, "onPreInit")

        configDir = event.modConfigurationDirectory // Obtain the mod configuration directory.
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        logger.debug(marker, "onInit")

        Config.load(File(configDir, "${MOD_ID}.cfg")) // Load the mod configurations from the .cfg file.
        // Dump mod configurations to the log.
        logger.info(marker, "JEI Genetics Configuration:")
        logger.info(marker, "  - showRequirement: ${Config.showRequirement}")
        logger.info(marker, "  - showSecret: ${Config.showSecret}")
        logger.info(marker, "  - geneticsConfigs:")
        for ((root, entry) in Config.geneticsConfigs) {
            logger.info(marker, "    - ${root.uid}:")
            logger.info(marker, "      - showMutation: ${entry.showMutation}")
            logger.info(marker, "      - showProduct: ${entry.showProduct}")
        }
    }

    @Mod.EventHandler
    fun onPostInit(event: FMLPostInitializationEvent) {
        logger.debug(marker, "onPostInit")
    }
}

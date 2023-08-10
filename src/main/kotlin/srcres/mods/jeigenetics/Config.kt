// SPDX-License-Identifier: LGPL-3.0-or-later

package srcres.mods.jeigenetics

import forestry.api.genetics.AlleleManager
import forestry.api.genetics.ISpeciesRoot
import forestry.api.lepidopterology.IButterflyRoot
import net.minecraftforge.common.config.Configuration
import java.io.File
import java.util.*

object Config {
    data class GeneticsConfigEntry(
        /** When false, mutations of this species root won't be displayed. */
        val showMutation: Boolean,
        /** When false, products of this species root won't be displayed. */
        val showProduct: Boolean) {
        companion object {
            /** Instantiate a GeneticsConfigEntry with all fields set to false. */
            fun newAllFalse() = GeneticsConfigEntry(false, false)
        }
    }

    /** When false, mutation requirements won't be displayed. */
    var showRequirement = true
    /** When false, secret mutations won't be displayed. */
    var showSecret = true
    /** The map of configurations of each species root defined in Forestry. */
    val geneticsConfigs = mutableMapOf<ISpeciesRoot, GeneticsConfigEntry>()

    fun load(file: File) = loadFromConfiguration(Configuration(file))

    private fun loadFromConfiguration(cfg: Configuration) {
        try {
            // Create configuration entries and read their data with default values specified.
            showRequirement = cfg.get("All", "ShowRequirement", true,
                "Set to false to disable display of mutation requirements").boolean
            showSecret = cfg.get("All", "ShowSecret", true,
                "Set to false to disable display of secret mutations").boolean
            for ((id, root) in AlleleManager.alleleRegistry.speciesRoot) {
                val name = id.replace("root", "")
                    // Capitalize the id string after its "root" substring is deleted (aka. replaced to "").
                    // Since the method String#capitalize() has been deprecated within higher Kotlin version,
                    // use the following logic instead.
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                val showMutation = cfg.get(name, "ShowMutation", true,
                    "Set to false to disable display of mutations").boolean
                val showProduct = if (root is IButterflyRoot) false else cfg.get(name, "ShowProduct", true,
                    "Set to false to disable display of products").boolean
                geneticsConfigs[root] = GeneticsConfigEntry(showMutation, showProduct)
            }
        } finally {
            // Call Configuration#save() within the finally block in order to ensure it is completely saved.
            cfg.save()
        }
    }
}

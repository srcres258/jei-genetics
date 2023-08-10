// SPDX-License-Identifier: LGPL-3.0-or-later

package srcres.mods.jeigenetics.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import srcres.mods.jeigenetics.Config
import srcres.mods.jeigenetics.JEIGenetics
import srcres.mods.jeigenetics.jei.recipe.mutation.MutationRecipeCategory
import srcres.mods.jeigenetics.jei.recipe.mutation.MutationRecipeWrapper

@JEIPlugin
class JEIGeneticsPlugin : IModPlugin {
    override fun register(registry: IModRegistry) {
        JEIGenetics.logger.debug("JEIGeneticsPlugin register")

        for ((root, entry) in Config.geneticsConfigs) {
            if (entry.showMutation) {
                val mutations = root.getMutations(false)
                val recipes = mutations.map { MutationRecipeWrapper(it) }.toList()
                registry.addRecipes(recipes, "srcres.jeigenetics.mutation." + root.uid)
            }
            if (entry.showProduct) {
                // TODO
            }
        }
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        JEIGenetics.logger.debug("JEIGeneticsPlugin registerCategories")

        val guiHelper = registry.jeiHelpers.guiHelper
        for ((root, entry) in Config.geneticsConfigs) {
            if (entry.showMutation) {
                val defaultIndividual = root.templateAsIndividual(root.defaultTemplate)
                registry.addRecipeCategories(MutationRecipeCategory(root, guiHelper,
                    root.getMemberStack(defaultIndividual, root.iconType)))
            }
            if (entry.showProduct) {
                // TODO
            }
        }
    }
}

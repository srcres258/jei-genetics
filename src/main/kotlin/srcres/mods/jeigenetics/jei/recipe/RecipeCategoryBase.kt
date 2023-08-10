// SPDX-License-Identifier: LGPL-3.0-or-later

package srcres.mods.jeigenetics.jei.recipe

import mezz.jei.api.recipe.IRecipeCategory
import srcres.mods.jeigenetics.JEIGenetics

abstract class RecipeCategoryBase<out T : RecipeWrapperBase> : IRecipeCategory<@UnsafeVariance T> {
    override fun getModName() = JEIGenetics.MOD_NAME
}

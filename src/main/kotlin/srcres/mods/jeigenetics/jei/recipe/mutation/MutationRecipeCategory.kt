package srcres.mods.jeigenetics.jei.recipe.mutation

import forestry.api.genetics.ISpeciesRoot
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import srcres.mods.jeigenetics.gui.element.InteractiveDrawableBase
import srcres.mods.jeigenetics.jei.recipe.RecipeCategoryBase

class MutationRecipeCategory(private val root: ISpeciesRoot,
    guiHelper: IGuiHelper, icon: ItemStack) : RecipeCategoryBase<MutationRecipeWrapper>() {
//    private val background: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(
//        "jei", "textures/gui/gui_vanilla.png"), 0, 114, 82, 54)
    private val background = InteractiveDrawableBase(guiHelper)

    override fun getUid() = "srcres.jeigenetics.mutation." + root.uid
    override fun getTitle() = "JEI Genetics Mutation"
    override fun getBackground() = background
    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: MutationRecipeWrapper, ingredients: IIngredients) {
        val itemStacks = recipeLayout.itemStacks
        itemStacks.init(0, true, 18, 15)
        itemStacks.init(1, true, 71, 15)
        itemStacks.init(2, true, 125, 15)
        itemStacks.set(0, recipeWrapper.p0Stack)
        itemStacks.set(1, recipeWrapper.p1Stack)
        itemStacks.set(2, recipeWrapper.resStack)
    }
}
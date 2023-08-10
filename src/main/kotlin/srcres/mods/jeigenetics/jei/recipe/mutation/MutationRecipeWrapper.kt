package srcres.mods.jeigenetics.jei.recipe.mutation

import forestry.api.genetics.IMutation
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import srcres.mods.jeigenetics.JEIGenetics
import srcres.mods.jeigenetics.jei.recipe.RecipeWrapperBase
import srcres.mods.jeigenetics.util.GeneticsUtil

class MutationRecipeWrapper(val mutation: IMutation) : RecipeWrapperBase() {
    val p0Stack = GeneticsUtil.getItemFromSpecies(mutation.allele0, GeneticsUtil.Position.P1)
    val p1Stack = GeneticsUtil.getItemFromSpecies(mutation.allele1, GeneticsUtil.Position.P2)
    val resStack = GeneticsUtil.getItemFromTemplate(mutation.root, mutation.template, GeneticsUtil.Position.RES)
    private val testButton = GuiButton(0, 0, 0, "test")

    override fun getIngredients(ingredients: IIngredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, listOf(GeneticsUtil.getAllItemsFromSpecies(mutation.allele0),
            GeneticsUtil.getAllItemsFromSpecies(mutation.allele1)))
        ingredients.setOutputs(VanillaTypes.ITEM, GeneticsUtil.getAllItemsFromTemplate(mutation.root, mutation.template))
    }

    override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
        testButton.drawButton(minecraft, mouseX, mouseY, 0f)
    }

    override fun handleClick(minecraft: Minecraft, mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        if (testButton.isMouseOver) {
            testButton.playPressSound(minecraft.soundHandler)
            JEIGenetics.logger.debug("testButton is clicked!")
        }
        // Must return false here, otherwise the other buttons on the JEI screen can't be clicked.
        return false
    }
}

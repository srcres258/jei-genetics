package srcres.mods.jeigenetics.gui.element

import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraft.util.ResourceLocation
import srcres.mods.jeigenetics.gui.IInteractiveDrawable

class InteractiveDrawableBase(guiHelper: IGuiHelper) : IInteractiveDrawable {
    private val background: IDrawableStatic = guiHelper.createDrawable(
        ResourceLocation("jei", "textures/gui/gui_vanilla.png"), 0, 114, 82, 54)

    override fun getWidth() = 256
    override fun getHeight() = 256
    override fun draw(minecraft: Minecraft, xOffset: Int, yOffset: Int) {
        background.draw(minecraft, xOffset, yOffset)
    }
}

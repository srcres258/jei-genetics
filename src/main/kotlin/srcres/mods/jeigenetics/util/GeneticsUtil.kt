package srcres.mods.jeigenetics.util

import forestry.api.apiculture.EnumBeeType
import forestry.api.apiculture.IBeeRoot
import forestry.api.arboriculture.EnumGermlingType
import forestry.api.arboriculture.ITreeRoot
import forestry.api.genetics.IAllele
import forestry.api.genetics.IAlleleSpecies
import forestry.api.genetics.ISpeciesRoot
import forestry.api.genetics.ISpeciesType
import forestry.api.lepidopterology.EnumFlutterType
import forestry.api.lepidopterology.IButterflyRoot
import net.minecraft.item.ItemStack

object GeneticsUtil {
    enum class Position {
        P1, P2, RES;
    }

    fun getItemFromSpecies(species: IAlleleSpecies, position: Position): ItemStack {
        val root = species.root
        val template = root.getTemplate(species.uid)!!
        return getItemFromTemplate(root, template, position)
    }

    fun getItemFromTemplate(root: ISpeciesRoot, template: Array<IAllele>, position: Position): ItemStack {
        val individual = root.templateAsIndividual(template)
        return root.getMemberStack(individual, getSpeciesTypeForPosition(root, position))
    }

    fun getSpeciesTypeForPosition(root: ISpeciesRoot, position: Position): ISpeciesType
            = when(root) {
        is IBeeRoot -> when(position) {
            Position.P1 -> EnumBeeType.PRINCESS
            Position.P2 -> EnumBeeType.DRONE
            Position.RES -> EnumBeeType.QUEEN
        }
        is ITreeRoot -> EnumGermlingType.SAPLING
        is IButterflyRoot -> EnumFlutterType.BUTTERFLY
        else -> root.iconType
    }

    fun getAllItemsFromSpecies(species: IAlleleSpecies): List<ItemStack> {
        val root = species.root
        val template = root.getTemplate(species.uid)!!
        return getAllItemsFromTemplate(root, template)
    }

    fun getAllItemsFromTemplate(root: ISpeciesRoot, template: Array<IAllele>): List<ItemStack> {
        val individual = root.templateAsIndividual(template)
        return getAllSpeciesTypes(root).map { root.getMemberStack(individual, it) }
    }

    fun getAllSpeciesTypes(root: ISpeciesRoot): List<ISpeciesType> = when (root) {
        is IBeeRoot -> EnumBeeType.VALUES.toList()
        is ITreeRoot -> EnumGermlingType.VALUES.toList()
        is IButterflyRoot -> EnumFlutterType.VALUES.toList()
        else -> listOf(root.iconType)
    }
}

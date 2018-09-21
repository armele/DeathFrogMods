package com.deathfrog.mc.strongerframes;

import binnie.core.genetics.BeeModifierLogic;
import binnie.core.genetics.EnumBeeBooleanModifier;
import binnie.core.genetics.EnumBeeModifier;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IHiveFrame;
import forestry.api.core.Tabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Logic lifted from binnie.extrabees.apiary.EnumHiveFrame
 * 
 * All credit to Binnie!
 * 
 * @author Al Mele
 *
 */
public class ItemTreatedSoulFrame extends Item implements IHiveFrame, IBeeModifier 
{
	protected BeeModifierLogic logic = new BeeModifierLogic();
	
    public ItemTreatedSoulFrame(String itemName)
    {	
        this.setUnlocalizedName(itemName);
        this.setTextureName(DFStrongerFrames.MODID + ":" + itemName);
        this.setCreativeTab(Tabs.tabApiculture);
        
        // Duplicate the properties of a Soul Frame (See EnumHiveFrame class)
		logic.setModifier(EnumBeeModifier.Mutation, 1.6f, 5.0f);
		logic.setModifier(EnumBeeModifier.Lifespan, 0.85f, 0.5f);
		logic.setModifier(EnumBeeModifier.Production, 0.35f, 0.1f);
        
        // Give this frame the power of a soul frame with the durability of a proven frame.
        this.setMaxDamage(720);
        
    }

	@Override
	public float getTerritoryModifier(IBeeGenome genome, float currentModifier) {
		return logic.getModifier(EnumBeeModifier.Territory, currentModifier);
	}

	@Override
	public float getMutationModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
		return logic.getModifier(EnumBeeModifier.Mutation, currentModifier);
	}

	@Override
	public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
		return logic.getModifier(EnumBeeModifier.Lifespan, currentModifier);
	}

	@Override
	public float getProductionModifier(IBeeGenome genome, float currentModifier) {
		return logic.getModifier(EnumBeeModifier.Production, currentModifier);
	}

	@Override
	public float getFloweringModifier(IBeeGenome genome, float currentModifier) {
		return logic.getModifier(EnumBeeModifier.Flowering, currentModifier);
	}

	@Override
	public float getGeneticDecay(IBeeGenome genome, float currentModifier) {
		return logic.getModifier(EnumBeeModifier.GeneticDecay, currentModifier);
	}

	@Override
	public boolean isSealed() {
		return logic.getModifier(EnumBeeBooleanModifier.Sealed);
	}

	@Override
	public boolean isSelfLighted() {
		return logic.getModifier(EnumBeeBooleanModifier.SelfLighted);
	}

	@Override
	public boolean isSunlightSimulated() {
		return logic.getModifier(EnumBeeBooleanModifier.SunlightStimulated);
	}

	@Override
	public boolean isHellish() {
		return logic.getModifier(EnumBeeBooleanModifier.Hellish);
	}

	@Override
	public ItemStack frameUsed(IBeeHousing house, ItemStack frame, IBee queen, int wear) {
		frame.setItemDamage(frame.getItemDamage() + wear);
		if (frame.getItemDamage() >= frame.getMaxDamage()) {
			return null;
		}
		return frame;
	}

	@Override
	public IBeeModifier getBeeModifier() {
		return this;
	}

}
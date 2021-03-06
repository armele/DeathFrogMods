package com.deathfrog.mc.strongerframes;

import org.apache.logging.log4j.Level;

import com.deathfrog.mc.whitetower.EnumAjahs;

import binnie.core.BinnieCore;
import binnie.extrabees.apiary.SoulFrameRegistryHack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.apiculture.items.ItemRegistryApiculture;
import forestry.plugins.PluginApiculture;
import forestry.plugins.PluginManager;
import mods.railcraft.common.fluids.FluidHelper;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

@Mod(modid=DFStrongerFrames.MODID, version=DFStrongerFrames.VERSION)
public class DFStrongerFrames {
	public static final String MODID = "dfstrongerframes";
	public static final String VERSION = "1.0";
	
	public static Item treatedSoulFrame = null;
	
    // public static Item futurium = null;
    // public static ModBlock futuriumOre = null;
    
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	/*
    	DeathFrogUtils.log(Level.INFO, "init - registering futurium");
    	
    	futurium = new ModItem("futurium");
        GameRegistry.registerItem(futurium, futurium.getUnlocalizedName());
        futuriumOre = new ModBlock(Material.rock, "futuriumOre", futurium);
        GameRegistry.registerBlock(futuriumOre, futuriumOre.getBlockName());
        
        GameRegistry.registerWorldGenerator(new ModWorldGenerator(futuriumOre, 6), 0);
        */
    	String sfItemName = "treatedsoulframe";
    	treatedSoulFrame = new ItemTreatedSoulFrame(sfItemName);
    	GameRegistry.registerItem(treatedSoulFrame, sfItemName);


    }
    
    /**
     * Set up the proven frame recipe.
     */
    protected void initProvenFrame() {
		ItemRegistryApiculture beeItems = PluginApiculture.items;
		
        for (ItemStack container : FluidHelper.getContainersFilledWith(Fluids.CREOSOTE.getB(1))) {
        	DeathFrogUtils.log(Level.INFO, "Adding proven frame recipe with: " + container.getDisplayName());
	  		
            for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()) {
    	  		
                if (data.filledContainer != null && data.filledContainer.getItem() == container.getItem()) {
                	if (data.emptyContainer != null) {
                		// DeathFrogUtils.log(Level.INFO, "Setting Container item: " + data.emptyContainer.getItem());
                		container.getItem().setContainerItem(data.emptyContainer.getItem());
                	}
                }
            }
        	
            CraftingPlugin.addShapedRecipe(new ItemStack(beeItems.frameProven),
                    "OOO",
                    "O#O",
                    "OOO",
                    'O', container,
                    '#', beeItems.frameImpregnated);
        }    	
    }
    
    /**
     * Set up the soul frame item recipe.
     */
    protected void initSoulFrame() {
		//Item soulFrame = GameRegistry.findItem("ExtraBees", "hiveframe.extrabees.item.frame.soul");
		Item soulFrame = SoulFrameRegistryHack.registeredSoulFrameItem();
		
		if (soulFrame != null) {
	        AspectList aspects = new AspectList();
	        aspects.add(Aspect.SOUL, 2);
	        aspects.add(Aspect.ENTROPY, 2);
	        ThaumcraftApi.registerObjectTag(new ItemStack(soulFrame), aspects);
			
			
	        for (ItemStack container : FluidHelper.getContainersFilledWith(Fluids.CREOSOTE.getB(1))) {
	        	DeathFrogUtils.log(Level.INFO, "Adding treated soul frame recipe with: " + container.getDisplayName());
    	  		
                for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()) {
        	  		
                    if (data.filledContainer != null && data.filledContainer.getItem() == container.getItem()) {
                    	if (data.emptyContainer != null) {
                    		// DeathFrogUtils.log(Level.INFO, "Setting Container item: " + data.emptyContainer.getItem());
                    		container.getItem().setContainerItem(data.emptyContainer.getItem());
                    	}
                    }
	            }
	        	
	            CraftingPlugin.addShapedRecipe(new ItemStack(DFStrongerFrames.treatedSoulFrame),
	                    "OOO",
	                    "O#O",
	                    "OOO",
	                    'O', container,
	                    '#', soulFrame);
	        }
		} else {
			DeathFrogUtils.log(Level.ERROR, "Could not find a registered soul frame item from ExtraBees. Skipping Treated Soul Frame recipe.");
		}
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	DeathFrogUtils.log(Level.INFO, "postinit");
		if (PluginManager.Module.APICULTURE.isEnabled()) {
			initProvenFrame();
		}
	
		// Register this recipe only if extra bees apiculture is active.
		if (BinnieCore.isApicultureActive()) {
			initSoulFrame();
		} else {
			DeathFrogUtils.log(Level.ERROR, "Binnie mod ExtraBees does not have apiculture active. Skipping Treated Soul Frame recipe.");
		}
		
		Block quarried = GameRegistry.findBlock("Railcraft", "brick.quarried");
		if (quarried != null) {
			EnumAjahs.init(quarried);
		} else {
			DeathFrogUtils.log(Level.ERROR, "Could not find the quarried stone block in Railcraft.");
		}
    }
}

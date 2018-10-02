package com.deathfrog.mc.whitetower;

import org.apache.logging.log4j.Level;

import com.cricketcraft.chisel.api.carving.ICarvingGroup;
import com.deathfrog.mc.strongerframes.DeathFrogUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import team.chisel.block.BlockCarvable;
import team.chisel.carving.Carving;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public enum EnumAjahs {
	WHITE 	("white"),
	GREY 	("grey"),
	BLUE 	("blue"),
	RED 	("red"),
	BROWN	("brown"),
	YELLOW 	("yellow"),
	GREEN 	("green");
	
	
	protected String name = null;
	
	
	private EnumAjahs(String ajahname) {
		name = ajahname;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 */
	static  public void init(Block quarried) {
		DeathFrogUtils.log(Level.INFO, "Initializing White Tower configuration.");
			
    	String wtBlockName = "wtblock";
    	BlockCarvable wtBaseBlock = new WhiteTowerBaseBlock(Material.rock, wtBlockName);
		
    	// TODO: Using this addVariation method results in duplicate maintenance of graphics assets... 
    	// but appears to avoid a defect in the logic to identify asset location?
    	
    	//wtBaseBlock.carverHelper.addVariation("tile.wtblock.0.desc", 0, "wtblock/wtblock", wtBaseBlock, 0, DFStrongerFrames.MODID, (ISubmapManager) null, 0);
		//wtBaseBlock.carverHelper.addVariation("tile.wtblock.1.desc", 1, "wtblock/wtsolid", wtBaseBlock, 0, DFStrongerFrames.MODID, (ISubmapManager) null, 1);
    	
    	wtBaseBlock.carverHelper.addVariation("tile.wtblock.0.desc", 0, "wtblock/wtblock", 0);
    	wtBaseBlock.carverHelper.addVariation("tile.wtblock.1.desc", 1, "wtblock/wtsolid", 1);
    	
    	//wtBaseBlock.carverHelper.addVariation("tile.wtblock.0.desc", 0, "wtblock/wtblock", DFStrongerFrames.MODID);
    	//wtBaseBlock.carverHelper.addVariation("tile.wtblock.1.desc", 1, "wtblock/wtsolid", DFStrongerFrames.MODID);
    	
		int i = 2;
		
		for (EnumAjahs ajah : values()) {
			//wtBaseBlock.carverHelper.addVariation("tile.wtblock."+i+".desc", i, "wtblock/wt"+ajah.getName(), wtBaseBlock, 0, DFStrongerFrames.MODID, (ISubmapManager) null, i);
			//wtBaseBlock.carverHelper.addVariation("tile.wtblock."+(i+1)+".desc", i+1, "wtblock/wt"+ajah.getName()+"tile", wtBaseBlock, 0, DFStrongerFrames.MODID, (ISubmapManager) null, i+1);
			
			wtBaseBlock.carverHelper.addVariation("tile.wtblock."+i+".desc", i, "wtblock/wt"+ajah.getName(), i);
			wtBaseBlock.carverHelper.addVariation("tile.wtblock."+(i+1)+".desc", i+1, "wtblock/wt"+ajah.getName()+"tile", i+1);	
			
			//wtBaseBlock.carverHelper.addVariation("tile.wtblock."+i+".desc", i, "wtblock/wt"+ajah.getName(), DFStrongerFrames.MODID);
			//wtBaseBlock.carverHelper.addVariation("tile.wtblock."+(i+1)+".desc", i+1, "wtblock/wt"+ajah.getName()+"tile", DFStrongerFrames.MODID);	
			
			i = i + 2;
		}

		// Carving.chisel.registerOre("wtblock", "wtblock");
        wtBaseBlock.carverHelper.registerAll(wtBaseBlock, "wtblock");
		wtBaseBlock.carverHelper.registerOre("wtblock");
        // Carving.chisel.registerOre("wtblock", "wtblock");
		
		
		// Set aspects on all chisels
		for (ItemStack stack : Carving.chisel.getItemsForChiseling(new ItemStack(wtBaseBlock)))
		{
			// Set Thaumcraft aspects
	        AspectList aspects = new AspectList();
	        aspects.add(Aspect.EARTH, 2);
	        aspects.add(Aspect.ORDER, 2);
	        aspects.add(Aspect.FIRE, 2);
	        ThaumcraftApi.registerObjectTag(stack, aspects);
		}
		
		// Create recipes for all quarried stone chisels
		for (ItemStack stack : Carving.chisel.getItemsForChiseling(new ItemStack(quarried)))
		{
			DeathFrogUtils.log(Level.INFO, "Adding quarried block recipe: " + stack.getDisplayName());
			
			/*
			// Create recipe to be able to get the White Tower carveable block...
			GameRegistry.addShapelessRecipe(new ItemStack(wtBaseBlock),
					new ItemStack(Item.getItemFromBlock(Blocks.torch), 1),
					stack);
			*/
			
			ThaumcraftApi.addArcaneCraftingRecipe("ARCANESTONE", 
					new ItemStack(wtBaseBlock, 10), 
					new AspectList().add(Aspect.EARTH, 2).add(Aspect.ORDER, 2).add(Aspect.FIRE, 2), 
					new Object[] { 
							"Q Q", 
							" Q ", 
							"Q Q", 
							Character.valueOf('Q'), stack
					}
			);

		}
		
        
		ICarvingGroup cg = Carving.chisel.getGroup("wtblock");
		if (cg != null) {
			DeathFrogUtils.log(Level.INFO, "White Tower variations Found:" + cg.getVariations().size());
			
		} else {
			DeathFrogUtils.log(Level.INFO, "No carving group found for 'wtblock'.");
		}		
	}
}

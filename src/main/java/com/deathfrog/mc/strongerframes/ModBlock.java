package com.deathfrog.mc.strongerframes;

import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModBlock extends Block
{
	private Item toDrop = null;
	private String blockName = null;
	 
	public ModBlock(Material material, String newBlockName, Item whatToDrop)
    {
        super(material);
        blockName = newBlockName;
        this.setBlockName(blockName);
        this.setBlockTextureName(DFStrongerFrames.MODID + ":" + blockName);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(4.0F);
        this.setHarvestLevel("pickaxe", 1);
        this.toDrop = whatToDrop;
    }
    
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {	
    	Item dropMe = toDrop;
    	
        if(dropMe == null) {
        	DeathFrogUtils.log(Level.WARN, "No droppable block associated with Futurium Ore");
        	dropMe = Item.getItemFromBlock(this);
        }


        return dropMe;
    }
    
    public String getBlockName() {
    	return blockName;
    }
}
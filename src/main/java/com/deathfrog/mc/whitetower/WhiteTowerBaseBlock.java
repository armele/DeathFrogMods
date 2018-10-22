package com.deathfrog.mc.whitetower;

import com.deathfrog.mc.strongerframes.DFStrongerFrames;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.chisel.block.BlockCarvable;

public class WhiteTowerBaseBlock extends BlockCarvable
{
	private String blockName = null;
	 
	public WhiteTowerBaseBlock(Material material, String newBlockName)
    {
        super(material);
        blockName = newBlockName;
        this.setBlockName(blockName);
        this.setBlockTextureName(DFStrongerFrames.MODID + ":" + blockName);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(6.0F);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightLevel(.6F);
    }
    
    public String getBlockName() {
    	return blockName;
    }
}
package com.leafia.dev.blocks.blockbase;

import com.hbm.main.MainRegistry;
import com.leafia.contents.AddonBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AddonBlockBase extends Block {
	public AddonBlockBase(Material m,String s) {
		super(m);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(MainRegistry.controlTab);
		AddonBlocks.ALL_BLOCKS.add(this);
	}

	public AddonBlockBase(Material material) {
		super(material);
		AddonBlocks.ALL_BLOCKS.add(this);
	}

	public AddonBlockBase() {
		super(Material.ROCK);
		AddonBlocks.ALL_BLOCKS.add(this);
	}

	public AddonBlockBase(Material m,SoundType sound,String s) {
		super(m);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setSoundType(sound);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(MainRegistry.controlTab);
		AddonBlocks.ALL_BLOCKS.add(this);
	}
}

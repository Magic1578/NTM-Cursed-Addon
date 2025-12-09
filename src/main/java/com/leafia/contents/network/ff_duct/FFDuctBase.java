package com.leafia.contents.network.ff_duct;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FFDuctBase extends BlockContainer {
	public FFDuctBase(Material materialIn) {
		super(materialIn);
	}
	@Override
	public @Nullable TileEntity createNewTileEntity(World worldIn,int meta) {
		return new FFDuctTE();
	}
}
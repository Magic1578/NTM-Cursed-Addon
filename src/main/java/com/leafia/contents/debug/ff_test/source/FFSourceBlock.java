package com.leafia.contents.debug.ff_test.source;

import com.leafia.dev.blocks.blockbase.AddonBlockBase;
import com.leafia.dev.blocks.blockbase.AddonBlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FFSourceBlock extends AddonBlockContainer {
	public FFSourceBlock(Material materialIn,String s) {
		super(materialIn,s);
	}
	@Override
	public @Nullable TileEntity createNewTileEntity(World worldIn,int meta) {
		return new FFSourceTE();
	}
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}

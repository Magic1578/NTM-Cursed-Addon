package com.leafia.contents.debug.ff_test.source;

import com.hbm.lib.ForgeDirection;
import com.leafia.contents.AddonFluids.AddonFF;
import com.leafia.contents.network.ff_duct.uninos.IFFProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FFSourceTE extends TileEntity implements ITickable, IFFProvider {
	FluidTank zaza = new FluidTank(10);
	@Override
	public void update() {
		for (EnumFacing facing : EnumFacing.values())
			tryProvide(zaza,world,pos.offset(facing),ForgeDirection.getOrientation(facing));
		zaza.fill(new FluidStack(AddonFF.fluoride,10),true);
	}
}

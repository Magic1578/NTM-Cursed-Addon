package com.leafia.contents.network.ff_duct.uninos;

import com.hbm.lib.ForgeDirection;
import com.hbm.uninos.UniNodespace;
import com.hbm.util.Compat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public interface IFFReceiver extends IFFHandler {
	default void trySubscribe(FluidTank tank,FluidStack defaultStack,World world,BlockPos pos,ForgeDirection dir) {
		TileEntity te = Compat.getTileStandard(world,pos.getX(),pos.getY(),pos.getZ());
		if (te instanceof IFFConductor conductor) {
			if (!conductor.canConnect(tank.getFluid() == null ? defaultStack : tank.getFluid(),dir.getOpposite())) return;
			FFNode node = UniNodespace.getNode(world,pos,FFNet.PROVIDER);
			if (node != null && node.net != null)
				node.net.addReceiver(this);
		}
	}
	FluidTank getCorrespondingTank(FluidStack stack);
}

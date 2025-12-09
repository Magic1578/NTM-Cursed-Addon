package com.leafia.contents.network.ff_duct.uninos;

import com.hbm.lib.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public interface IFFConnector {
	/**
	 * Whether the given side can be connected to
	 */
	default boolean canConnect(FluidStack stack,ForgeDirection dir) {
		return dir != ForgeDirection.UNKNOWN;
	}
}

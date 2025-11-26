package com.leafia.dev;

import com.hbm.inventory.fluid.tank.FluidTankNTM;
import com.llib.exceptions.LeafiaDevFlaw;
import net.minecraft.nbt.NBTTagCompound;

public class NTMFNBT {
	public static NBTTagCompound getNBT(FluidTankNTM tank) {
		NBTTagCompound tag;
		try {
			tag = (NBTTagCompound)(tank.getClass().getField("addon_nbt").get(tank));
		} catch (NoSuchFieldException | IllegalAccessException e) {
			LeafiaDevFlaw flaw = new LeafiaDevFlaw(e.toString());
			flaw.appendStackTrace(e);
			throw flaw;
		}
		return tag;
	}
	public static void setNBT(FluidTankNTM tank,NBTTagCompound nbt) {
		try {
			tank.getClass().getField("addon_nbt").set(tank,nbt);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			LeafiaDevFlaw flaw = new LeafiaDevFlaw(e.toString());
			flaw.appendStackTrace(e);
			throw flaw;
		}
	}
}

package com.leafia.contents.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FFNBT {
	/**
	 * Should be called while calculating fluid distribution in networks to see if tank contents are compatible.
	 * @param sending the sending stack
	 * @param receiving the receiving tank
	 * @return true if compatible
	 */
	public static boolean areTagsCompatible(FluidStack sending,FluidTank receiving) {
		if (receiving.getFluid() == null) return true;
		NBTTagCompound sendingTag = sending.tag;
		NBTTagCompound receivingTag = receiving.getFluid().tag;
		if (receivingTag == null) return true;
		if (receivingTag.equals(sendingTag)) return true;
		//if (sending.getTankType().equals(AddonFluids.FLUORIDE)) return true; for future; MSR
		return false;
	}

	/**
	 * Should be called whilst doing transferFluid.
	 * For example MSR would need this to calculate combined temperature and fuel mixture.
	 * @param sending the sending stack
	 * @param receiving the receiving tank
	 */
	public static void transferTags(FluidTank sending,FluidTank receiving) {
		if (sending.getFluid() == null) return;
		if (receiving.getFluid() == null) return;
		NBTTagCompound sendingTag = sending.getFluid().tag;
		NBTTagCompound receivingTag = receiving.getFluid().tag;
		if (sendingTag == null) return;
		if (receivingTag == null) {
			receivingTag = new NBTTagCompound();
			for (String s : sendingTag.getKeySet())
				receivingTag.setTag(s,sendingTag.getTag(s));
		}
		receiving.getFluid().tag = receivingTag;
	}
}

package com.leafia.contents.debug.ff_test.tank;

import com.hbm.lib.ForgeDirection;
import com.leafia.contents.AddonFluids.AddonFF;
import com.leafia.contents.network.ff_duct.uninos.IFFReceiver;
import com.leafia.dev.container_utility.LeafiaPacket;
import com.leafia.dev.container_utility.LeafiaPacketReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FFTankTE extends TileEntity implements ITickable, LeafiaPacketReceiver, IFFReceiver {
	FluidTank zaza = new FluidTank(10000);

	@Override
	public void update() {
		for (EnumFacing facing : EnumFacing.values())
			trySubscribe(zaza,new FluidStack(AddonFF.fluoride,0),world,pos.offset(facing),ForgeDirection.getOrientation(facing));
		LeafiaPacket._start(this).__write(0,zaza.writeToNBT(new NBTTagCompound())).__sendToAffectedClients();
	}

	@Override
	public String getPacketIdentifier() {
		return "debug_ff_tank";
	}
	@Override
	public void onReceivePacketLocal(byte key,Object value) {
		zaza.readFromNBT((NBTTagCompound)value);
	}

	@Override
	public void onReceivePacketServer(byte key,Object value,EntityPlayer plr) { }

	@Override
	public void onPlayerValidate(EntityPlayer plr) { }

	@Override
	public FluidTank getCorrespondingTank(FluidStack stack) {
		return zaza;
	}
}

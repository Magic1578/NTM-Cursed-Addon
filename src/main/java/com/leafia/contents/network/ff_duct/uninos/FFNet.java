package com.leafia.contents.network.ff_duct.uninos;

import com.hbm.uninos.INetworkProvider;
import com.hbm.uninos.NodeNet;
import com.leafia.contents.network.FFNBT;
import com.leafia.dev.LeafiaUtil;
import com.llib.exceptions.LeafiaDevFlaw;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraftforge.fluids.FluidTank;

import java.util.*;

public class FFNet extends NodeNet<IFFReceiver,IFFProvider,FFNode,FFNet> {
	public static final INetworkProvider<FFNet> PROVIDER = FFNet::new;
	Map<IFFProvider,Set<FluidTank>> tankMap = new HashMap<>();
	public void addTank(IFFProvider prov,FluidTank tank) {
		tankMap.putIfAbsent(prov,new HashSet<>());
		tankMap.get(prov).add(tank);
	}
	@Override
	public void update() {
		if (providerEntries.isEmpty()) { tankMap.clear(); return; }
		if (receiverEntries.isEmpty()) { tankMap.clear(); return; }
		// i got tired and rushed, have this shitty algorithm
		ObjectIterator<Entry<IFFProvider>> provIt = providerEntries.object2LongEntrySet().fastIterator();
		while (provIt.hasNext()) {
			Object2LongMap.Entry<IFFProvider> entry = provIt.next();
			IFFProvider prov = entry.getKey();
			Set<FluidTank> tanks = tankMap.get(prov);
			if (tanks != null) {
				for (FluidTank tank : tanks) {
					if (tank.getFluid() == null) continue;
					final List<IFFReceiver> receivers = new ArrayList<>();
					ObjectIterator<Object2LongMap.Entry<IFFReceiver>> recIt = receiverEntries.object2LongEntrySet().fastIterator();
					int totalDemand = 0;
					while (recIt.hasNext()) {
						Object2LongMap.Entry<IFFReceiver> entry1 = recIt.next();
						IFFReceiver rec = entry1.getKey();
						FluidTank receiving = rec.getCorrespondingTank(tank.getFluid());
						if (FFNBT.areTagsCompatible(tank.getFluid(),receiving)) {
							int demand = receiving.getCapacity()-receiving.getFluidAmount();
							totalDemand += demand;
							if (demand > 0)
								receivers.add(rec);
						}
					}
					int totalAmt = tank.getFluidAmount();
					for (IFFReceiver rec : receivers) {
						FluidTank receiving = rec.getCorrespondingTank(tank.getFluid());
						int demand = receiving.getCapacity()-receiving.getFluidAmount();
						float ratio = demand/(float)totalDemand;
						int toTransfer = Math.min(demand,(int)(totalAmt*ratio)); // just to be safe
						if (toTransfer > 0) {
							// at this point, transferring is confirmed, no turning back
							int sent = LeafiaUtil.fillFF(tank,receiving,toTransfer);
							if (sent != toTransfer)
								throw new LeafiaDevFlaw("net: confirmed transfer amount ("+toTransfer+"mB) and actual amount transferred ("+sent+"mB) doesn't match, wtf");
						}
					}
				}
			}
		}
		tankMap.clear();
	}
}

package com.leafia.contents.network.ff_duct;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.ForgeDirection;
import com.hbm.uninos.UniNodespace;
import com.leafia.contents.AddonFluids;
import com.leafia.contents.network.ff_duct.uninos.FFNet;
import com.leafia.contents.network.ff_duct.uninos.FFNode;
import com.leafia.contents.network.ff_duct.uninos.IFFConductor;
import com.llib.exceptions.LeafiaDevFlaw;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class FFDuctTE extends TileEntity implements ITickable, IFFConductor {
	protected FluidType type = AddonFluids.FLUORIDE;
	protected FFNode node;

	@Override
	public void update() {
		if (!world.isRemote) {
			if (this.node == null || this.node.expired) {
				if (this.shouldCreateNode()) {
					this.node = UniNodespace.getNode(world,pos,FFNet.PROVIDER);
					if (this.node == null || this.node.expired) {
						this.node = this.createNode();
						UniNodespace.createNode(world, node);
					}
				}
			}
		}
	}
	public boolean shouldCreateNode() {
		return true;
	}

	public FluidType getType() {
		return this.type;
	}

	public void setType(FluidType type) {
		FluidType prev = this.type;
		this.type = type;
		this.markDirty();

		if (world instanceof WorldServer) {
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			world.markBlockRangeForRenderUpdate(pos, pos);
		}

		UniNodespace.destroyNode(world, pos, prev.getNetworkProvider());

		/*if(this.node != null) {
			this.node = null;
		}*/
	}

	@Override
	public boolean canConnect(@Nonnull FluidStack stack,ForgeDirection dir) {
		if (stack == null)
			throw new LeafiaDevFlaw("canConnect stack cannot be null");
		return dir != ForgeDirection.UNKNOWN && (type.getFF() == null ? type.getFF() == stack.getFluid() : type.getFF().equals(stack.getFluid()));
	}
}

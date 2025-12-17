package com.leafia.contents.network.pipe_amat;

import com.hbm.api.fluidmk2.FluidNode;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.tileentity.network.TileEntityPipeBaseNT;
import com.hbm.uninos.UniNodespace;
import com.leafia.contents.network.pipe_amat.uninos.AmatNet;
import com.leafia.contents.network.pipe_amat.uninos.AmatNode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

public class AmatDuctTE extends TileEntityPipeBaseNT {
	@Override
	public boolean canConnect(FluidType type,ForgeDirection dir) {
		TileEntity te = world.getTileEntity(pos.offset(dir.toEnumFacing()));
		if ((te instanceof TileEntityPipeBaseNT) && !(te instanceof AmatDuctTE))
			return false;
		return super.canConnect(type,dir);
	}
	/*protected AmatNode node;
	protected FluidType type = Fluids.NONE;
	protected FluidType lastType = Fluids.NONE;

	@Override
	public void update() {
		if(!world.isRemote && canUpdate()) {
			if(this.node == null || this.node.expired) {

				if(this.shouldCreateNode()) {
					this.node = UniNodespace.getNode(world,pos,AmatNet.getProvider(type));

					if(this.node == null || this.node.expired) {
						this.node = this.createNodeAmat(type);
						UniNodespace.createNode(world,this.node);
					}
				}
			}
		}
	}
	public AmatNode createNodeAmat(FluidType type) {
		TileEntity tile = (TileEntity) this;
		return new AmatNode(AmatNet.getProvider(type), tile.getPos()).setConnections(
				new DirPos(tile.getPos().getX() + 1, tile.getPos().getY(), tile.getPos().getZ(), Library.POS_X),
				new DirPos(tile.getPos().getX() - 1, tile.getPos().getY(), tile.getPos().getZ(), Library.NEG_X),
				new DirPos(tile.getPos().getX(), tile.getPos().getY() + 1, tile.getPos().getZ(), Library.POS_Y),
				new DirPos(tile.getPos().getX(), tile.getPos().getY() - 1, tile.getPos().getZ(), Library.NEG_Y),
				new DirPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ() + 1, Library.POS_Z),
				new DirPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ() - 1, Library.NEG_Z)
		);
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

		UniNodespace.destroyNode(world, pos, AmatNet.getProvider(prev));

		if(this.node != null) {
			this.node = null;
		}
	}
	@Override
	public void invalidate() {
		super.invalidate();

		if(!world.isRemote) {
			if(this.node != null) {
				UniNodespace.destroyNode(world, pos, AmatNet.getProvider(type));
			}
		}
	}*/
}

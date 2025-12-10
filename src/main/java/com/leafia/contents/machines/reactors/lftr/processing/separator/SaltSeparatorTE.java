package com.leafia.contents.machines.reactors.lftr.processing.separator;

import com.hbm.tileentity.TileEntityMachineBase;

public class SaltSeparatorTE extends TileEntityMachineBase {
	public SaltSeparatorTE() {
		super(13);
	}
	@Override
	public String getDefaultName() {
		return "tile.salt_separator.name";
	}
}

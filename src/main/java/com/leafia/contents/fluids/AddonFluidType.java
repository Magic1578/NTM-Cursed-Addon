package com.leafia.contents.fluids;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.render.misc.EnumSymbol;

import java.util.Locale;

public class AddonFluidType extends FluidType {
	private static int id = 0;
	public AddonFluidType(String name,int color,int p,int f,int r,EnumSymbol symbol) {
		super(name,color,p,f,r,symbol,name.toLowerCase(Locale.US),0xFFFFFF,1121+(id++) /* eevee */,null);
	}
}

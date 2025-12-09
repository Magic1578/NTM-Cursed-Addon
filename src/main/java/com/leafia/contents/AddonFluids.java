package com.leafia.contents;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.render.misc.EnumSymbol;
import com.leafia.contents.fluids.AddonFluidType;
import com.leafia.contents.fluids.FluorideFluid;
import com.llib.exceptions.LeafiaDevFlaw;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.lang.reflect.Field;
import java.util.List;

import static com.hbm.inventory.fluid.Fluids.*;

public class AddonFluids {
	protected static final List<FluidType> metaOrder;
	static {
		Field metaField = null;
		try {
			metaField = Fluids.class.getDeclaredField("metaOrder");
			metaField.setAccessible(true);
			metaOrder = (List<FluidType>)metaField.get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new LeafiaDevFlaw(e);
		}
	}
	public static class AddonFF {
		public static Fluid fluoride = new FluorideFluid("fluoride").setDensity(1000);
		public static void init() {
			registerFluid(fluoride);
		}
		private static void registerFluid(Fluid fluid) {
			FluidRegistry.registerFluid(fluid);
			FluidRegistry.addBucketForFluid(fluid);
		}
		public static void setFromRegistry() {
			fluoride = FluidRegistry.getFluid("fluoride");
		}
	}
	public static FluidType FLUORIDE;
	//public static FluidType FLUORINE; oh boy fluorine already exists
	public static void init() {
		FLUORIDE = new AddonFluidType("FLUORIDE",0xd3d8b9,3,0,0,EnumSymbol.NONE).setTemp(500).addTraits(LIQUID).setFFNameOverride("fluoride");
		//FLUORINE = new FluidType("FLUORINE",0xc5b055,4,0,4,EnumSymbol.NOWATER).addTraits(GASEOUS);
		metaOrder.add(FLUORIDE);
		//metaOrder.add(FLUORINE);
	}
}

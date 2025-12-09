package com.leafia.init;

import com.leafia.AddonBase;
import com.leafia.contents.building.sign.SignTE;
import com.leafia.contents.debug.ff_test.source.FFSourceTE;
import com.leafia.contents.debug.ff_test.tank.FFTankTE;
import com.leafia.contents.machines.powercores.dfc.components.cemitter.CoreCEmitterTE;
import com.leafia.contents.machines.powercores.dfc.components.exchanger.CoreExchangerTE;
import com.leafia.contents.network.ff_duct.FFDuctTE;
import com.leafia.contents.network.spk_cable.SPKCableTE;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TEInit {
	public static void preInit() {
		{
			// Debug TEs
			register(FFSourceTE.class,"debug_ff_source");
			register(FFTankTE.class,"debug_ff_tank");
		}
		register(SPKCableTE.class,"spk_cable_te");
		register(CoreCEmitterTE.class,"core_creative_emitter_te");
		register(CoreExchangerTE.class,"core_exchanger_te");
		register(SignTE.class,"letter_sign_te");
		register(FFDuctTE.class,"ff_duct_te");
	}
	private static void register(Class<? extends TileEntity> clazz,String res) {
		GameRegistry.registerTileEntity(clazz,new ResourceLocation(AddonBase.MODID,res));
	}
}

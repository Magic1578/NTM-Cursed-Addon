package com.leafia.overwrite_contents.mixin.mod.hbm;

import com.hbm.blocks.bomb.Landmine;
import com.hbm.explosion.vanillant.standard.EntityProcessorCrossSmooth;
import com.hbm.interfaces.IBomb;
import com.leafia.unsorted.explosion_vnt.EntityProcessorLandmine;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Landmine.class)
public abstract class MixinLandmine extends BlockContainer implements IBomb {
	protected MixinLandmine(Material materialIn) {
		super(materialIn);
	}
	@Redirect(method = "explode",at = @At(value = "NEW", target = "(DF)Lcom/hbm/explosion/vanillant/standard/EntityProcessorCrossSmooth;"),remap = false,require = 1)
	public EntityProcessorCrossSmooth onExplode(double nodeDist,float fixedDamage) {
		return new EntityProcessorLandmine(nodeDist,fixedDamage);
	}
}

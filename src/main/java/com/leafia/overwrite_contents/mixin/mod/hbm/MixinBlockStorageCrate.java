package com.leafia.overwrite_contents.mixin.mod.hbm;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockStorageCrate;
import com.hbm.util.I18nUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = BlockStorageCrate.class)
public abstract class MixinBlockStorageCrate extends BlockContainer {
	public MixinBlockStorageCrate(Material materialIn) { super(materialIn); }
	@SideOnly(Side.CLIENT)
	@Inject(method = "addInformation",at = @At(value = "HEAD"),require = 1)
	public void onAddInformation(ItemStack stack,World worldIn,List<String> list,ITooltipFlag flagIn,CallbackInfo ci) {
		if (this == ModBlocks.crate_tungsten)
			list.add(TextFormatting.RED+I18nUtil.resolveKey("tile.crate_tungsten.desc"));
	}
}

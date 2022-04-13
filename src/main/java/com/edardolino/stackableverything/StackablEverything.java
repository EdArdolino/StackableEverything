package com.edardolino.stackableverything;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(StackablEverything.MOD_ID)
public class StackablEverything {
	public static final String MOD_ID = "stackableverything";
	public static final Logger LOGGER = LogManager.getLogger();

	public StackablEverything() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}

	@SuppressWarnings("deprecation")
	private void setupCommon(final FMLCommonSetupEvent event) {

		for (Item Item : ForgeRegistries.ITEMS) {
			if (!Item::canBeDepleted() && Item.getMaxStackSize() < 64 && Item.getRegistryName().getNamespace() == "minecraft") {
				ObfuscationReflectionHelper.setPrivateValue(Item.class, Item, 64, "field_77777_bU");
			}
		}
	}

	private void setupClient(final FMLClientSetupEvent event) {}
}

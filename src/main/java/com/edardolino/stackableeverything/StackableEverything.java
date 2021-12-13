package com.edardolino.stackableeverything;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(StackableEverything.MOD_ID)
public class StackableEverything {
    public static final String MOD_ID = "stackableeverything";
    public static final Logger LOGGER = LogManager.getLogger();

    public StackableEverything() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
    }

    @SuppressWarnings("deprecation")
    private void setupCommon(final FMLCommonSetupEvent event) {


        for(Item Item : ForgeRegistries.ITEMS)
        {
            if( Item.getMaxStackSize() < 64 && Item.getRegistryName().getNamespace() == "minecraft")
            {
                ObfuscationReflectionHelper.setPrivateValue(Item.class, Item, 64, "f_41370_");
            }
        }

    }

    private void setupClient(final FMLClientSetupEvent event) {

    }


}

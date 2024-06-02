package com.github.sunshine724.Tab;

import com.github.sunshine724.Item.ItemSpawnerJarijari;
import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class JarijariTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JarijariMod.MODID);
    public static final RegistryObject<CreativeModeTab> JARIJARI_TAB = TABS.register("jarijari_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetabs.jarijari_tab"))
                    .icon(Jaitem.JARIHORSE.get()::getDefaultInstance)
                    .displayItems(((pParameters,pOutput) ->{
                        pOutput.accept(ItemSpawnerJarijari.SpawnerJarijari.get());
                        pOutput.accept(Jaitem.JARIHORSE.get());
                    }))
                    .build());

    public static void register(IEventBus eventBus){
        TABS.register(eventBus);
    }
}

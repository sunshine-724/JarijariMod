package com.github.sunshine724.Tab;

import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Introductiontabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JarijariMod.MODID);
    public static final RegistryObject<CreativeModeTab> INTRODUCTION_TAB = TABS.register("introduction_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetabs.introduction_tab"))
                    .icon(ItemSpawnerJarijariMod.RAW_ORIHALCON.get()::getDefaultInstance)
                    .displayItems(((pParameters,pOutput) ->{
                        pOutput.accept(IntroductionItems.RAW_ORIHALCON.get());
                    }))
                    .build());

    public static void register(IEventBus eventBus){
        TABS.register(eventBus);
    }
}

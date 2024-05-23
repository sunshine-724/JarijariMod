package com.github.sunshine724.Item;

import com.github.sunshine724.Entity.EntityInit;
import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemSpawnerJarijari {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JarijariMod.MODID);

//    public static final RegistryObject<Item> SpawnerJarijari = ITEMS.register("spawnerjarijari", () -> new Item(new Item.Properties()));


    public static final RegistryObject<ForgeSpawnEggItem> SpawnerJarijari = ITEMS.register("spawnerjarijari",
           () -> new ForgeSpawnEggItem(EntityInit.JARIJARI_ENTITY,0,0,new Item.Properties())); //テスト用コード Sunshine724

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
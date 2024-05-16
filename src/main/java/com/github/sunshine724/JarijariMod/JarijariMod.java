package com.github.sunshine724.JarijariMod;

//https://github.com/sunshine-724/JarijariMod

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.math.BigInteger;
import java.util.Iterator;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JarijariMod.MODID)
public class JarijariMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "jarijarimod"; //使えるIDは小文字、数字、"-","_"のみ(変えたらgradle.propertiesも変えること)
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public JarijariMod()
    {
        //イベントバスに関連するクラスIEventBusのeventbus静的変数を格納
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        //このクラスのインスタンスを渡してインスタンスをイベントバスのリスナーとして登録する
        //注意1:インスタンスで登録しているので、イベントで使いたいメソッドを定義する場合はインスタンスメソッドにすること
        //注意2:インスタンスメソッドを定義するときはメソッドの上に@SubscribeEventアノテーションをつけるこ
        //注意3:もし静的メソッドを使いたい場合はインスタンスではなくクラスで登録すること
        //ex. MinecraftForge.EVENT_BUS.register(Example.class);
        //尚、このメソッドを使わずとも該当クラスに@Mod.EventBusSubscriberアノテーションをつけることで自動で登録される
        MinecraftForge.EVENT_BUS.register(this);

        //addCreativeメソッド(自作メソッド)をイベントバスのリスナーとして登録する
        //注意1:上に同じくインスタンスで登録しているので、イベントで使いたいメソッドを定義する場合はインスタンスメソッドにすること
        //注意2:これの場合、アノテーションはつけない
        //注意3:もし静的メソッドを登録したい場合は該当クラスに@Mod.EventBusSubscriber(modid = "MOD名", bus = Bus.FORGE, value = Dist.CLIENT)を付けること
        //下の@Mod.EventBusSubscriberを参照
        modEventBus.addListener(this::addCreative);
    }



    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}

//package com.github.sunshine724.Entity.client.events;
//
//import com.github.sunshine724.Entity.EntityInit;
//import com.github.sunshine724.Entity.client.model.JarijariEntityModel;
//import com.github.sunshine724.Entity.client.renderer.JarijariEntityRenderer;
//import com.github.sunshine724.JarijariMod.JarijariMod;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.EntityRenderersEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//
//@Mod.EventBusSubscriber(modid = JarijariMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
//public class ClientModEvents {
//    //レンダーを登録する
//    @SubscribeEvent
//    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
//        event.registerEntityRenderer(EntityInit.JARIJARI_ENTITY.get(), JarijariEntityRenderer::new);
//    }
//
//    //レイヤーの定義をする
//    @SubscribeEvent
//    public static void registerLayerDefinication(EntityRenderersEvent.RegisterLayerDefinitions event){
//        event.registerLayerDefinition(JarijariEntityModel.LAYER_LOCATION,JarijariEntityModel::createBodyLayer);
//    }
//}

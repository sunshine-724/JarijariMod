package com.github.sunshine724.Entity.client.events;

import com.github.sunshine724.Entity.EntityInit;
import com.github.sunshine724.Entity.client.model.blue.JarijariEntityBlueModel;
import com.github.sunshine724.Entity.client.model.normal.JarijariEntityNormalModel;
import com.github.sunshine724.Entity.client.model.white.JarijariEntityWhiteModel;
import com.github.sunshine724.Entity.client.renderer.JarijariEntityCustomRenderer;
import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = JarijariMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientModEvents {
    //レンダーを登録する
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityInit.JARIJARI_ENTITY.get(),JarijariEntityCustomRenderer::new);
//        event.registerEntityRenderer(EntityInit.JARIJARI_ENTITY.get(), JarijariEntityNormalRenderer::new);
//        event.registerEntityRenderer(EntityInit.JARIJARI_ENTITY.get(), JarijariEntityWhiteRenderer::new);
//        event.registerEntityRenderer(EntityInit.JARIJARI_ENTITY.get(), JarijariEntityBlueRenderer::new);
    }

    //レイヤーの定義をする
    @SubscribeEvent
    public static void registerLayerDefinication(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(JarijariEntityNormalModel.LAYER_LOCATION, JarijariEntityNormalModel::createBodyLayer);
        event.registerLayerDefinition(JarijariEntityBlueModel.LAYER_LOCATION,JarijariEntityBlueModel::createBodyLayer);
        event.registerLayerDefinition(JarijariEntityWhiteModel.LAYER_LOCATION,JarijariEntityWhiteModel::createBodyLayer);
    }
}

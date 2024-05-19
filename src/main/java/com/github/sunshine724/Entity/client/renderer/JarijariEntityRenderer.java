package com.github.sunshine724.Entity.client.renderer;

import com.github.sunshine724.Entity.JarijariEntity;
import com.github.sunshine724.Entity.client.model.normal.JarijariEntityNormalModel;
import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JarijariEntityRenderer extends MobRenderer<JarijariEntity, JarijariEntityNormalModel<JarijariEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(JarijariMod.MODID,"textures/entity/image2.png");


    public JarijariEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new JarijariEntityNormalModel<>(ctx.bakeLayer(JarijariEntityNormalModel.LAYER_LOCATION)), 1.0f); //1:コンテキスト 2:モデル 3:モデルの影のサイズ
    }

    //場面によって表示させるテクスチャを変えれる
    @Override
    public ResourceLocation getTextureLocation(JarijariEntity entity) {
//        //雪の上ではtexture0にし、それ以外ではtexture1にする
//        if(entity.isInPowderSnow){
//            return texture 0;
//        }
//        return texture 1;
        return TEXTURE;
    }
}

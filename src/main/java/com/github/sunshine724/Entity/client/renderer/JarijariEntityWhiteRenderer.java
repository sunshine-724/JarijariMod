package com.github.sunshine724.Entity.client.renderer;


import com.github.sunshine724.Entity.JarijariEntity;
import com.github.sunshine724.Entity.client.model.normal.JarijariEntityNormalModel;
import com.github.sunshine724.Entity.client.model.white.JarijariEntityWhiteModel;
import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class JarijariEntityWhiteRenderer extends MobRenderer<JarijariEntity,JarijariEntityWhiteModel<JarijariEntity>>{
    private static final ResourceLocation TEXTURE = new ResourceLocation(JarijariMod.MODID,"textures/entity/image4.png");


    public JarijariEntityWhiteRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new JarijariEntityWhiteModel<>(ctx.bakeLayer(JarijariEntityWhiteModel.LAYER_LOCATION)), 1.0f); //1:コンテキスト 2:モデル 3:モデルの影のサイズ
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

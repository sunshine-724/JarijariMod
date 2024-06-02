package com.github.sunshine724.Entity.client.renderer;

import com.github.sunshine724.Entity.JarijariEntity;
import com.github.sunshine724.Entity.client.model.blue.JarijariEntityBlueModel;
import com.github.sunshine724.Entity.client.model.normal.JarijariEntityNormalModel;
import com.github.sunshine724.Entity.client.model.white.JarijariEntityWhiteModel;
import com.github.sunshine724.JarijariMod.JarijariMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

public class JarijariEntityCustomRenderer extends EntityRenderer<JarijariEntity> {

    private final JarijariEntityNormalModel model1;
    private final JarijariEntityBlueModel model2;
    private final JarijariEntityWhiteModel model3;

    public JarijariEntityCustomRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.model1 = new JarijariEntityNormalModel<>(ctx.bakeLayer(JarijariEntityNormalModel.LAYER_LOCATION));
        this.model2 = new JarijariEntityBlueModel<>(ctx.bakeLayer(JarijariEntityBlueModel.LAYER_LOCATION)); //ここでエラー(解消 5/20)
        this.model3 = new JarijariEntityWhiteModel<>(ctx.bakeLayer(JarijariEntityWhiteModel.LAYER_LOCATION));
    }

    @Override
    public void render(JarijariEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        EntityModel<JarijariEntity> modelToRender;

        // エンティティの体力に基づいてモデルを選択

        if(entity.getHealth() < entity.getMaxHealth()*((float) 1/3)){
            modelToRender = model3;
        }else if(entity.getHealth() < entity.getMaxHealth()*(float) 2/3){
            modelToRender = model2;
        }else{
            modelToRender = model1;
        }

        poseStack.pushPose();
        // なぜかモデルが上下反転するため、モデルを上下反転させる
        poseStack.translate(0.0, 0.75, 0.0); // モデルの高さに合わせて調整(中心座標(0.0)を軸に回転させるため移動させる)
        poseStack.scale(1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0, -0.75, 0.0); // モデルの高さに合わせて調整

//        double dx = entity.getDeltaMovement().x;
//        double dz = entity.getDeltaMovement().z;
//        float angle = (float) (Math.atan2(dz, dx) * (180.0 / Math.PI) - 90.0);
//        org.joml.Quaternionf Quaternionf = new Quaternionf(new AxisAngle4f((float) (Math.atan2(dz, dx) * (180.0 / Math.PI) - 90.0),0,0,0));
//        poseStack.mulPose(Quaternionf);
        modelToRender.renderToBuffer(poseStack, bufferSource.getBuffer(modelToRender.renderType(getTextureLocation(entity))), packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();

        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(JarijariEntity entity) {

        if(entity.getHealth() < entity.getMaxHealth()*((float) 1/3)){
            return new ResourceLocation(JarijariMod.MODID, "textures/entity/image4.png");
        }else if(entity.getHealth() < entity.getMaxHealth()*(float) 2/3){
            return new ResourceLocation(JarijariMod.MODID, "textures/entity/image3.png");
        }else{
            return new ResourceLocation(JarijariMod.MODID, "textures/entity/image2.png");
        }
    }
}

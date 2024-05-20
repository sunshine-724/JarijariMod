package com.github.sunshine724.Entity.client.model.white;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.github.sunshine724.Entity.JarijariEntity;
import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraft.client.model.EntityModel;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class JarijariEntityWhiteModel<T extends JarijariEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JarijariMod.MODID, "textures/entity/image4.png"), "main");

	private final ModelPart bone;
//	private final ModelPart head;
//	private final ModelPart neck;
//	private final ModelPart box;

	public JarijariEntityWhiteModel(ModelPart root) {
		this.bone = root.getChild("bone");
//		this.head = root.getChild("head");
//		this.neck = root.getChild("neck");
//		this.box = root.getChild("box");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 1).addBox(-2.0F, -1.125F, -5.625F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(18, 14).addBox(-2.0F, 2.875F, -3.625F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 3).addBox(1.0F, -3.125F, 1.375F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 0).addBox(-2.0F, -3.125F, 1.375F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.875F, -1.375F));

		PartDefinition neck = bone.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(17, 0).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -14.0F, 2.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition box = bone.addOrReplaceChild("box", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
	}
}
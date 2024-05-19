package com.github.sunshine724.Entity;

import com.github.sunshine724.JarijariMod.JarijariMod;
import com.github.sunshine724.Entity.JarijariEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/*エンティティの初期化クラス*/
public class EntityInit {
    /*レジスタにエンティティを登録する*/
    public static final DeferredRegister<EntityType<?>> ENTITYS = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,JarijariMod.MODID);

    //モブカテゴリとサイズとモブの名前を登録する
    public static final RegistryObject<EntityType<JarijariEntity>> JARIJARI_ENTITY = ENTITYS.register("jarijari_entity",
            () -> EntityType.Builder.<JarijariEntity>of(JarijariEntity::new, MobCategory.CREATURE)
                    .sized(2.0f,2.0f)
                    .build(new ResourceLocation(JarijariMod.MODID,"jarijari_entity").toString()));

}

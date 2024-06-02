package com.github.sunshine724.Entity;

import com.github.sunshine724.JarijariMod.JarijariMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.Nullable;


/*エンティティの初期化クラス*/
public class EntityInit {
    /*レジスタにエンティティとサウンドを登録する*/
    public static final DeferredRegister<EntityType<?>> ENTITYS = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,JarijariMod.MODID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,JarijariMod.MODID);

//    public static final RegistryObject<SoundEvent> SOUND_EVENTS_REGISTRY_HURT = registerSoundEvents("hurt_sound");
//    public static final RegistryObject<SoundEvent> SOUND_EVENTS_REGISTRY_DEATH = registerSoundEvents("death_sound");
//    public static final RegistryObject<SoundEvent> SOUND_EVENTS_REGISTRY_LIVING = registerSoundEvents("living_sound");

//    public static final RegistryObject<SoundEvent> CUSTOM_MOB_AMBIENT = SOUND_EVENTS.register("custom_mob_ambient",
//            () -> new SoundEvent(new ResourceLocation(JarijariMod.MODID,"custom_mob_ambient")));



//    @Nullable
//    SoundEvent hurtSoundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jarijarimod", "entity.custom_mob.hurt"));
//    @Nullable SoundEvent deathSoundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jarijarimod", "entity.custom_mob.death"));
//    @Nullable SoundEvent livingSoundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jarijarimod", "entity.custom_mob.living"));


//    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
//        return SOUND_EVENTS.register(name,() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(JarijariMod.MODID,name)));
//    }


    //モブカテゴリとサイズとモブの名前を登録する
    public static final RegistryObject<EntityType<JarijariEntity>> JARIJARI_ENTITY = ENTITYS.register("jarijari_entity",
            () -> EntityType.Builder.<JarijariEntity>of(JarijariEntity::new, MobCategory.CREATURE)
                    .sized(1.0f,1.0f)
                    .build(new ResourceLocation(JarijariMod.MODID,"jarijari_entity").toString()));

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}

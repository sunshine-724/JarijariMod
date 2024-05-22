//package com.github.sunshine724.Entity.client.events;
//
//import com.github.sunshine724.Entity.EntityInit;
//import com.github.sunshine724.Entity.JarijariEntity;
//import com.github.sunshine724.JarijariMod.JarijariMod;
//import net.minecraft.world.entity.SpawnPlacements;
//import net.minecraft.world.level.levelgen.Heightmap;
//import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
//import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//
//
//@Mod.EventBusSubscriber(modid = JarijariMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
//
//public class CommonModEvents {
//
//    //エンティティを初期化し、ステータスを加える
//    @SubscribeEvent
//    public static void entityAttributes(EntityAttributeCreationEvent event){
//        event.put(EntityInit.JARIJARI_ENTITY.get(), JarijariEntity.createJarijariAttributes().build());
//    }
//
//
//    //スポーンする場所を定義し、イベントレジスタに登録する
//    @SubscribeEvent
//    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event){
//        event.register(EntityInit.JARIJARI_ENTITY.get(),
//                SpawnPlacements.Type.ON_GROUND,
//                Heightmap.Types.WORLD_SURFACE,
//                JarijariEntity::canSpawn,SpawnPlacementRegisterEvent.Operation.OR
//        );
//    }
//}

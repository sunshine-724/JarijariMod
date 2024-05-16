package com.github.sunshine724.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import javax.print.AttributeException;

/*エンティティの実装*/
public class JarijariEntity extends Animal {


    //コンストラクタ
    public JarijariEntity(EntityType<JarijariEntity> type, Level Level) {
        //動物のタイプと警戒レベルを設定
        super(type, Level);
    }

    //コンストラクタ
    public JarijariEntity(Level level,double x,double y,double z){
        //上のコンストラクタでインスタンスを作成し、スポーン時の座標を設定
        this(EntityInit.JARIJARI_ENTITY.get(),level);
        setPos(x,y,z);
    }

    public JarijariEntity(Level level, BlockPos position){
        //レベルを設定し、この座標を読み取る
        this(level, position.getX(),position.getY(),position.getZ());
    }

    //繁殖メソッド
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return new JarijariEntity(level,this.blockPosition());
    }

    //aiによるエンティティの動きを追加
    @Override
    protected void registerGoals() {
        Item boneItem = Items.BONE; // 誘引対象のアイテム
        //数字が低いほど優先順位が高くなる
        //どの動きを追加したいかはGradle:net.minecraftforge:forge:~~~~/forge~~~~/net/minecraft/world.entity.ai.goalにあるサブクラスを参照
        this.goalSelector.addGoal(0,new FloatGoal(this)); //水中に浮かぶ
        this.goalSelector.addGoal(1,new PanicGoal(this,1.25D)); //パニック状態で走るため
        this.goalSelector.addGoal(3,new BreedGoal(this,1.0D));
        this.goalSelector.addGoal(5,new FollowParentGoal(this,1.1D));
        this.goalSelector.addGoal(6,new WaterAvoidingRandomStrollGoal(this,1.0D));
        this.goalSelector.addGoal(7,new LookAtPlayerGoal(this, Player.class,6.0F));
        this.goalSelector.addGoal(8,new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9,new TemptGoal(this,1.0D, Ingredient.of(boneItem),true));

    }

    //エンティティのステータスをデフォルトステータス(今回は狼)からいくつか上書きする
    public static AttributeSupplier.Builder createJarijariAttributes(){
        return Wolf.createAttributes().add(Attributes.MAX_HEALTH,20.0D).add(Attributes.MOVEMENT_SPEED,0.0D);
    }

    //スポーンするときの条件を引数にあるパラメータを使って判断し、trueかfalseで返す
    public static boolean canSpawn(EntityType<JarijariEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random){
        return Animal.checkAnimalSpawnRules(entityType,level,spawnType,position,random);
    }

}

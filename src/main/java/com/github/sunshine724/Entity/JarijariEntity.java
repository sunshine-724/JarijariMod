package com.github.sunshine724.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*エンティティの実装*/
public class JarijariEntity extends Wolf {
    Boolean isFloat; //モブを浮かすかどうか
    //各効果音をオーバーライド
    @Nullable SoundEvent hurtSoundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jarijariMod", "entity.custom_mob.hurt"));
    @Nullable SoundEvent deathSoundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jarijariMod", "entity.custom_mob.death"));
    @Nullable SoundEvent livingSoundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jarijariMod", "entity.custom_mob.living"));


    //コンストラクタ
    public JarijariEntity(EntityType<JarijariEntity> type, Level Level) {
        //動物のタイプと警戒レベルを設定
        super(type, Level);
        isFloat = false;
    }

    //コンストラクタ
    public JarijariEntity(Level level,double x,double y,double z){
        //上のコンストラクタでインスタンスを作成し、スポーン時の座標を設定
        this(EntityInit.JARIJARI_ENTITY.get(),level);
        setPos(x,y,z);
        isFloat = false;
    }

    public JarijariEntity(Level level, BlockPos position){
        //レベルを設定し、この座標を読み取る
        this(level, position.getX(),position.getY(),position.getZ());
        isFloat = false;
    }

//    //繁殖メソッド
//    @Nullable
//    @Override
//    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
//        return new JarijariEntity(level,this.blockPosition());
//    }

    //aiによるエンティティの動きを追加
    @Override
    protected void registerGoals() {
        Item gravelItem = Items.GRAVEL; // 誘引対象のアイテム
        //数字が低いほど優先順位が高くなる
        //どの動きを追加したいかはGradle:net.minecraftforge:forge:~~~~/forge~~~~/net/minecraft/world.entity.ai.goalにあるサブクラスを参照
//        if(isFloat){
//            this.setPos(this.getX(),this.getY()+3,this.getZ());
//        }else{
//            this.setPos(this.getX(),this.getY()-3,this.getZ());
//        }
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal(this, true));
    }

    //モブがプレイヤーに誘引されるかどうか(砂利で好感度を上げる)
    public @NotNull InteractionResult mobInteract(@NotNull Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.GRAVEL) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                //食べ物をあげると回復する
                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else {
                //もし殺されたら
                if (item instanceof DyeItem) {
                    DyeItem dyeitem = (DyeItem)item;
                    if (this.isOwnedBy(pPlayer)) {
                        DyeColor dyecolor = dyeitem.getDyeColor();
                        if (dyecolor != this.getCollarColor()) {
                            this.setCollarColor(dyecolor);
                            if (!pPlayer.getAbilities().instabuild) {
                                itemstack.shrink(1);
                            }

                            return InteractionResult.SUCCESS;
                        }
                        return super.mobInteract(pPlayer, pHand);
                    }
                }

                InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                    //座らせたら(テイム状態であり、追尾状態が解除されている状態)
                    isFloat = false;
//                    this.setPos(this.getX(),this.getY()-3,this.getZ());
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    return InteractionResult.SUCCESS;
                } else {
                    //少し浮かす
                    isFloat = true;
//                    this.setPos(this.getX(),this.getY()+3,this.getZ());
                    return interactionresult;
                }
            }
        } else if (itemstack.is(Items.GRAVEL) && !this.isAngry()) {
            //まだテイム状態ではなく、プレイヤーが砂利を上げる際
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                this.level().broadcastEntityEvent(this, (byte)7); //テイム成功のエフェクト

                //もしテイムに成功したら
                this.tame(pPlayer);
                this.navigation.stop();
//                this.setTame(true); // ウルフをテイム状態に設定
                this.setOwnerUUID(pPlayer.getUUID()); // ウルフのオーナーを設定
                this.setTarget((LivingEntity)null);
                this.setOrderedToSit(true);
            } else {
                this.level().broadcastEntityEvent(this, (byte)6); //テイム失敗のエフェクト
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    /*効果音関連*/
    //ダメージを受けた時
    @Override
    public boolean doHurtTarget(@NotNull Entity entityIn) {
        boolean flag = super.doHurtTarget(entityIn);
        if (flag) {
            this.playSound(hurtSoundEvent, 1.0F, 1.0F);
        }
        return flag;
    }

    //死んだ時
    @Override
    public void die(@NotNull DamageSource cause) {
        super.die(cause);
        this.playSound(deathSoundEvent, 1.0F, 1.0F);
    }

    //環境音
    @Override
    public void playAmbientSound() {
        this.playSound(livingSoundEvent, 1.0F, 1.0F);
    }



    //エンティティのステータスをデフォルトステータス(今回は狼)からいくつか上書きする
    public static AttributeSupplier.Builder createJarijariAttributes(){
        //WolfクラスのsetTameメソッドとmobInteractを参照
//        Wolf.createAttributes();
        //Wolfクラスからとってきた
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.30000001192092896).add(Attributes.MAX_HEALTH, 8.0).add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    //スポーンするときの条件を引数にあるパラメータを使って判断し、trueかfalseで返す
    public static boolean canSpawn(EntityType<JarijariEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random){
        return Animal.checkAnimalSpawnRules(entityType,level,spawnType,position,random);
    }
}

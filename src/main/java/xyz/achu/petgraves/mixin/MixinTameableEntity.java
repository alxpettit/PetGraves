package xyz.achu.petgraves.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.achu.petgraves.PetDeathHandler;

@Mixin(TameableEntity.class)
public abstract class MixinTameableEntity extends AnimalEntity {
    private static final PetDeathHandler pet_death_handler = new PetDeathHandler();

    protected MixinTameableEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        //if (this.isTamed()) {
            pet_death_handler.onDeath(this, source);
        //}
    }

    @Shadow
    abstract boolean isTamed();
}

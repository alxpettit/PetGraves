package xyz.achu.petgraves.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.achu.petgraves.PetDeathHandler;

/* WTF is wrong with you, Mojang?
 * This should inherit from TameableEntity.
 * Since it doesn't, I had to do this whole song and dance.
 */
@Mixin(HorseBaseEntity.class)
public abstract class MixinHorseBaseEntity extends AnimalEntity {
    private static final PetDeathHandler petDeathHandler = new PetDeathHandler();

    protected MixinHorseBaseEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    /* Cannot inject this method because it is implemented way back
    in AnimalEntity and not overridden by HorseBaseEntity */
    @Override
    public void onDeath(DamageSource source) {
        //if (this.isTame()) {
            petDeathHandler.onDeath(this, source);
        //}
        super.onDeath(source);
    }

    /* Why is this isTame() here, but isTamed()
     * under TameableEntity? God damn, you people.
     */
    @Shadow
    abstract boolean isTame();

}

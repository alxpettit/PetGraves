package xyz.achu.petgraves;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PetDeathHandler {
    private static final Logger logger = LogManager.getLogger();
    BlockPos pos;
    DamageSource source;
    World world;
    BlockState blockState = PetGraves.GRAVE_BLOCK.getDefaultState();

    public void onDeath(Entity entity, DamageSource new_source) {
        world = entity.getEntityWorld();
        if (!world.isClient()) {
            CompoundTag compoundTag = new CompoundTag();
            entity.saveSelfToTag(compoundTag);
            pos = new BlockPos(entity);

            source = new_source;
            logger.info("How could you let me die? :( : " + pos.getX() + " " + pos.getY() + " " + pos.getZ());

            world.setBlockState(pos, blockState);
        }
    }
}

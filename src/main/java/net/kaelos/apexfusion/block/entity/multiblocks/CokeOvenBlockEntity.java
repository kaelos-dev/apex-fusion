package net.kaelos.apexfusion.block.entity.multiblocks;

import net.kaelos.apexfusion.block.entity.AFBlockEntities;
import net.kaelos.apexfusion.block.entity.base.MultiblockBlockEntity;
import net.kaelos.apexfusion.multiblock.MultiblockManager;
import net.kaelos.apexfusion.multiblock.MultiblockTemplate;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CokeOvenBlockEntity extends MultiblockBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    
    public CokeOvenBlockEntity(BlockPos pos, BlockState state) {
        super(AFBlockEntities.MULTIBLOCKS_ENTITIES.get(MultiblocksList.COKE_OVEN).get(), pos, state, 1);
    }

    @Override
    public MultiblockTemplate getTemplate() {
        return MultiblockManager.get("coke_oven");
    }

    @Override
    public BlockPos getCorePos() {
        return this.worldPosition;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

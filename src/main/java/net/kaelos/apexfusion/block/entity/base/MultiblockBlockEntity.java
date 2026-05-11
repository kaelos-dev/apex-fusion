package net.kaelos.apexfusion.block.entity.base;

import net.kaelos.apexfusion.multiblock.MultiblockTemplate;
import net.kaelos.apexfusion.util.IMultiblocksTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;

public abstract class MultiblockBlockEntity extends BlockEntity implements IMultiblocksTileEntity, GeoBlockEntity {
    private final int renderBox;
    
    public MultiblockBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state, int renderBoxSize) {
        super(entityType, pos, state);
        this.renderBox = renderBoxSize;
    }

    @Override
    public abstract MultiblockTemplate getTemplate();

    @Override
    public abstract BlockPos getCorePos();

    @Override
    public abstract Level getLevel();

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition).inflate(this.renderBox);
    }

    @Override
    public abstract void registerControllers(ControllerRegistrar controllers);

    @Override
    public abstract AnimatableInstanceCache getAnimatableInstanceCache();
}

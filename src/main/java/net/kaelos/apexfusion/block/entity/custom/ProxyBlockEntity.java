package net.kaelos.apexfusion.block.entity.custom;

import net.kaelos.apexfusion.block.entity.AFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ProxyBlockEntity extends BlockEntity {
    private BlockPos corePos = BlockPos.ZERO;
    
    public ProxyBlockEntity(BlockPos pos, BlockState state) {
        super(AFBlockEntities.PROXY_ENTITY.get(), pos, state);
    }

    public void setCorePos(BlockPos pos) {
        this.corePos = pos;
        setChanged();
    }

    public BlockPos getCorePos() {
        return this.corePos;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putLong("core_pos", corePos.asLong());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider provider) {
        super.loadAdditional(tag, provider);
        this.corePos = BlockPos.of(tag.getLong("core_pos"));
    }
}

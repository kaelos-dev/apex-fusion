package net.kaelos.apexfusion.util.lists;

import net.kaelos.apexfusion.block.entity.multiblocks.CokeOvenBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public enum MultiblocksList {
    COKE_OVEN("coke_oven", CokeOvenBlockEntity::new, 1);

    private String name;
    private BlockEntityType.BlockEntitySupplier<?> entityFactory;
    private int searchRadius;

    MultiblocksList(String name, BlockEntityType.BlockEntitySupplier<?> entityFactory, int searchRadius) {
        this.name = name;
        this.entityFactory = entityFactory;
        this.searchRadius = searchRadius;
    }

    public String getName() {
        return name;
    }

    public BlockEntityType.BlockEntitySupplier<?> getEntityFactory() {
        return entityFactory;
    }

    public int getSearchRadius() {
        return searchRadius;
    }
}

package net.kaelos.apexfusion.datagen;

import java.util.Set;

import net.kaelos.apexfusion.block.AFBlocks;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class AFBlockLootTableProvider extends BlockLootSubProvider {
    public AFBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        for (MultiblocksList lists : MultiblocksList.values()) {
            dropSelf(AFBlocks.MULTI_CONSTRUCTIONS_BLOCKS.get(lists).get());
            dropSelf(AFBlocks.MULTI_CORE_BLOCKS.get(lists).get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AFBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

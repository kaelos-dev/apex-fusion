package net.kaelos.apexfusion.datagen;

import net.kaelos.apexfusion.AF;
import net.kaelos.apexfusion.block.AFBlocks;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class AFBlockStateProvider extends BlockStateProvider {
    public AFBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AF.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (MultiblocksList lists : MultiblocksList.values()) {
            blockWithItem(AFBlocks.MULTI_CONSTRUCTIONS_BLOCKS.get(lists), "construction");
            blockWithItem(AFBlocks.MULTI_CORE_BLOCKS.get(lists), "core");
        }
    }

    private void blockWithItem(RegistryObject<Block> registryObject) {
        simpleBlockWithItem(registryObject.get(), cubeAll(registryObject.get()));
    }

    private void blockWithItem(RegistryObject<Block> registryObject, String texturePath) {
        simpleBlockWithItem(registryObject.get(), 
            models().cubeAll(
                BuiltInRegistries.BLOCK.getKey(registryObject.get()).getPath(),
                modLoc("block/" + texturePath + "/" + registryObject.getId().getPath())
            )
        );
    }
}

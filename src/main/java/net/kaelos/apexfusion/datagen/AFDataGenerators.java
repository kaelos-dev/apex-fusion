package net.kaelos.apexfusion.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.kaelos.apexfusion.AF;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AF.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AFDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider>  lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(), 
                List.of(new LootTableProvider.SubProviderEntry(AFBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        
        BlockTagsProvider tagsProvider = new AFBlockTagProvider(output, lookupProvider, exFileHelper);
        generator.addProvider(event.includeServer(), tagsProvider);
        generator.addProvider(event.includeServer(), new AFItemTagProvider(output, lookupProvider, tagsProvider.contentsGetter(), exFileHelper));

        generator.addProvider(event.includeClient(), new AFItemModelProvider(output, exFileHelper));
        generator.addProvider(event.includeClient(), new AFBlockStateProvider(output, exFileHelper));
    }
}

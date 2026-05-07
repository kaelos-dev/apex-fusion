package net.kaelos.apexfusion.item;

import net.kaelos.apexfusion.AF;
import net.kaelos.apexfusion.block.AFBlocks;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AFCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AF.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MULTIBLOCK_COMPONENTS = TABS.register("multiblock_components", () -> 
            CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + AF.MOD_ID + ".multiblock_components"))
            .icon(() -> new ItemStack(AFBlocks.MULTI_CONSTRUCTIONS_BLOCKS.get(MultiblocksList.COKE_OVEN).get()))
            .displayItems((displayItems, output) -> {
                for (MultiblocksList lists : MultiblocksList.values()) {
                    output.accept(AFBlocks.MULTI_CONSTRUCTIONS_BLOCKS.get(lists).get());
                    output.accept(AFBlocks.MULTI_CORE_BLOCKS.get(lists).get());
                }
            }).build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}

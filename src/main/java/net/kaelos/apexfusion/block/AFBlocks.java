package net.kaelos.apexfusion.block;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import net.kaelos.apexfusion.AF;
import net.kaelos.apexfusion.block.multiblocks.ConstructionsBlock;
import net.kaelos.apexfusion.block.multiblocks.CoreBlock;
import net.kaelos.apexfusion.item.AFItems;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AFBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AF.MOD_ID);

    public static final Map<MultiblocksList, RegistryObject<Block>> MULTI_CONSTRUCTIONS_BLOCKS = new EnumMap<>(MultiblocksList.class);
    public static final Map<MultiblocksList, RegistryObject<Block>> MULTI_CORE_BLOCKS = new EnumMap<>(MultiblocksList.class);

    private static void createComponentsForMultiblocks() {
        for (MultiblocksList lists : MultiblocksList.values()) {
            MULTI_CONSTRUCTIONS_BLOCKS.put(lists, registerBlock(lists.getName() + "_bricks", () -> new ConstructionsBlock()));
            MULTI_CORE_BLOCKS.put(lists, registerBlock(lists.getName() + "_core", () -> new CoreBlock()));
        }
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> object = BLOCKS.register(name, block);
        registerBlockItem(name, object);
        return object;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        AFItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        createComponentsForMultiblocks();
    }
}

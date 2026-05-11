package net.kaelos.apexfusion.block.entity;

import java.util.EnumMap;
import java.util.Map;

import net.kaelos.apexfusion.AF;
import net.kaelos.apexfusion.block.AFBlocks;
import net.kaelos.apexfusion.block.entity.custom.ProxyBlockEntity;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AFBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AF.MOD_ID);

    public static final Map<MultiblocksList, RegistryObject<BlockEntityType<?>>> MULTIBLOCKS_ENTITIES = new EnumMap<>(MultiblocksList.class);

    private static void createEntityForCore() {
        for (MultiblocksList lists : MultiblocksList.values()) {
            MULTIBLOCKS_ENTITIES.put(lists, ENTITIES.register(lists.getName() + "_entity", () -> 
                BlockEntityType.Builder.of(
                    lists.getEntityFactory(), 
                    AFBlocks.MULTI_CORE_BLOCKS.get(lists).get()
                ).build(null)
            ));
        }
    }

    public static final RegistryObject<BlockEntityType<ProxyBlockEntity>> PROXY_ENTITY = ENTITIES.register("proxy_entity", () -> 
            BlockEntityType.Builder.of(ProxyBlockEntity::new, AFBlocks.PROXY.get()).build(null));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
        createEntityForCore();
    }
}

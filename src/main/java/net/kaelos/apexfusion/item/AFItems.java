package net.kaelos.apexfusion.item;

import java.util.EnumMap;
import java.util.Map;

import net.kaelos.apexfusion.AF;
import net.kaelos.apexfusion.fluid.AFFluids;
import net.kaelos.apexfusion.util.lists.FluidList;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AF.MOD_ID);

    public static final Map<FluidList, RegistryObject<Item>> FLUID_BUCKET_ITEMS = new EnumMap<>(FluidList.class);

    private static void createFluidBuckets() {
        for (FluidList list : FluidList.values()) {
            FLUID_BUCKET_ITEMS.put(list, ITEMS.register(list.getName() + "_bucket", () -> 
                    new BucketItem(AFFluids.SOURCE_FLUIDS.get(list), 
                        new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))));
        }
    }

    public static final RegistryObject<Item> INSTALLATION_WRENCH = ITEMS.register("installation_wrench", () -> 
            new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> COKING_COAL = ITEMS.register("coking_coal", () -> 
            new Item(new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        createFluidBuckets();
    }
}

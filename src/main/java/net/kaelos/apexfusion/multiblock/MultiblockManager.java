package net.kaelos.apexfusion.multiblock;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import net.kaelos.apexfusion.AF;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

public class MultiblockManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();
    private static Map<ResourceLocation, MultiblockTemplate> TEMPLATES = new HashMap<>();

    public MultiblockManager() {
        super(GSON, "multiblocks");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objects, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, MultiblockTemplate> newTemplates = new HashMap<>();

        objects.forEach((location, element) -> {
            MultiblockTemplate template = GSON.fromJson(element, MultiblockTemplate.class);
            newTemplates.put(location, template);
        });

        TEMPLATES = newTemplates;
    }

    public static MultiblockTemplate get(String name) {
        return TEMPLATES.get(ResourceLocation.fromNamespaceAndPath(AF.MOD_ID, name));
    }
}

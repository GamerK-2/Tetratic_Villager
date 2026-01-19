package net.gamerk2.tetratic_villager.village;

import com.mojang.datafixers.util.Pair;
import net.gamerk2.tetratic_villager.TetraticVillager;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static net.gamerk2.tetratic_villager.TetraticVillager.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TetraticVillagerStructures {
    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(
            Registries.PROCESSOR_LIST, new ResourceLocation("minecraft", "empty"));

    @SubscribeEvent
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        TetraticVillager.LOGGER.info("TetraticVillagerStructures: ServerAboutToStartEvent fired");
        Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).orElse(null);
        Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).orElse(null);

        if (templatePoolRegistry == null) {
            TetraticVillager.LOGGER.error("Template pool registry is null");
            return;
        }
        if (processorListRegistry == null) {
            TetraticVillager.LOGGER.error("Processor list registry is null");
            return;
        }

        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft", "village/plains/houses"), new ResourceLocation(MODID, "plains/plains_tetra_smith_house"), 4);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft", "village/taiga/houses"), new ResourceLocation(MODID, "taiga/taiga_tetra_smith_house"), 4);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft", "village/desert/houses"), new ResourceLocation(MODID, "desert/desert_tetra_smith_house"), 4);
    }

    private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry, Registry<StructureProcessorList> processorListRegistry, ResourceLocation poolRL, ResourceLocation nbtPieceRL, int weight) {

        Holder<StructureProcessorList> emptyProcessorList = processorListRegistry.getHolderOrThrow(EMPTY_PROCESSOR_LIST_KEY);

        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) {
            TetraticVillager.LOGGER.warn("Template pool {} not found. Available pools count: {}", poolRL, templatePoolRegistry.keySet().size());
            // 로그용: 일부 키만 출력해 확인
            int i = 0;
            for (ResourceLocation key : templatePoolRegistry.keySet()) {
                if (i++ > 20) break;
                TetraticVillager.LOGGER.info(" - pool key: {}", key);   
            }
            return;
        }

        TetraticVillager.LOGGER.info("Found pool {}. Existing templates: {}, rawTemplates: {}", poolRL, pool.templates.size(), pool.rawTemplates.size());

        SinglePoolElement piece = SinglePoolElement.legacy(String.valueOf(nbtPieceRL), emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);

        for (int i = 0; i < weight; i++) {
            pool.templates.add(piece);
        }

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, weight));
        pool.rawTemplates = listOfPieceEntries;

        TetraticVillager.LOGGER.info("Added piece {} with weight {} to pool {}", nbtPieceRL, weight, poolRL);
    }
}
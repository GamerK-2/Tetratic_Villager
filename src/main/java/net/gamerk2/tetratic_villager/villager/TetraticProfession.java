package net.gamerk2.tetratic_villager.villager;

import com.google.common.collect.ImmutableSet;
import net.gamerk2.tetratic_villager.TetraticVillager;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import se.mickelus.tetra.blocks.workbench.BasicWorkbenchBlock;


public class TetraticProfession {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, TetraticVillager.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TetraticVillager.MODID);

    public static final RegistryObject<PoiType> WORKBENCH_POI = POI_TYPES.register("workbench_poi", () -> new PoiType(
            ImmutableSet.copyOf(BasicWorkbenchBlock.instance.getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<VillagerProfession> TETRA_SMITH = VILLAGER_PROFESSIONS.register(
            "tetra_smith", () -> new VillagerProfession(
                    "tetra_smith",
                    poiTypeHolder -> poiTypeHolder.get() == WORKBENCH_POI.get(),
                    holder -> holder.get() == WORKBENCH_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_WEAPONSMITH));

    public static void register(IEventBus bus) {
        POI_TYPES.register(bus);
        VILLAGER_PROFESSIONS.register(bus);
    }
}

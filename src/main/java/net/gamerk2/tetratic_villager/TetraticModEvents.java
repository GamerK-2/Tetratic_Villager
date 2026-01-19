package net.gamerk2.tetratic_villager;

import net.gamerk2.tetratic_villager.trades.EmeraldsToItemsTrade;
import net.gamerk2.tetratic_villager.trades.ItemsToEmeraldsTrade;
import net.gamerk2.tetratic_villager.trades.ItemsToItemsTrade;
import net.gamerk2.tetratic_villager.villager.TetraticProfession;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.geode.*;
import se.mickelus.tetra.blocks.rack.RackBlock;
import se.mickelus.tetra.items.cell.ThermalCellItem;
import se.mickelus.tetra.items.forged.MetalScrapItem;
import se.mickelus.tetra.items.modular.impl.ModularDoubleHeadedItem;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = TetraticVillager.MODID)
public class TetraticModEvents {

    @SubscribeEvent
    public static void addTetraTrades(VillagerTradesEvent event) {
        VillagerProfession profession = event.getType();

        // Tetra Smith Trades
        if (TetraticProfession.TETRA_SMITH.get().equals(profession)) {
            add(event, 1, new EmeraldsToItemsTrade(new ItemStack(RackBlock.instance.asItem(), 1), 4, 16, 1));
            add(event, 1, new EmeraldsToItemsTrade(ModularDoubleHeadedItem.setupHammerStack("iron", "spruce"), 11, 4, 3));
            add(event, 2, new EmeraldsToItemsTrade(new ItemStack(PristineEmeraldItem.instance, 1), 15, 4, 2));
            add(event, 2, new ItemsToEmeraldsTrade(MetalScrapItem.instance.get().asItem(), 6, 2, 24, 2));
            add(event, 3, new ItemsToItemsTrade(Items.EMERALD, 16, Items.AMETHYST_SHARD, 3, new ItemStack(PristineAmethystItem.instance.asItem(), 1), 12, 4));
            add(event, 3, new ItemsToItemsTrade(Items.EMERALD, 20, Items.LAPIS_LAZULI, 4, new ItemStack(PristineLapisItem.instance.asItem(), 1), 8, 5));
            add(event, 4, new EmeraldsToItemsTrade(new ItemStack(GeodeItem.instance.asItem(), 1), 8, 16, 3));
            add(event, 4, new ItemsToItemsTrade(Items.EMERALD, 32, Items.DIAMOND, 2, new ItemStack(PristineDiamondItem.instance.asItem(), 1), 6, 9));
            add(event, 5, new ItemsToItemsTrade(MetalScrapItem.instance.get().asItem(), 24, Blocks.MAGMA_BLOCK.asItem(), 6, new ItemStack(ThermalCellItem.instance.get(), 1), 4, 8));
        }
    }

    private static void add(VillagerTradesEvent event, int level, VillagerTrades.ItemListing... listings) {
        ((List) event.getTrades().get(level)).addAll(Arrays.asList(listings));
    }


}

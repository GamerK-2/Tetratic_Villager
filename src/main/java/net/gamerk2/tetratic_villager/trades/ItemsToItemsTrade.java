package net.gamerk2.tetratic_villager.trades;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;

public class ItemsToItemsTrade implements VillagerTrades.ItemListing {
    private final Item item1;
    private final Item item2;
    private final ItemStack sellingItem;
    private final int item1Count;
    private final int item2Count;
    private final int sellingItemCount;
    private final int maxUses;
    private final int xpValue;
    private final float priceMultiplier;

    public ItemsToItemsTrade(Item item1, int item1Count, Item item2, int item2Count, ItemStack sellingItem, int maxUses, int xpValue) {
        this.item1 = item1;
        this.item2 = item2;
        this.sellingItem = sellingItem;
        this.item1Count = item1Count;
        this.item2Count = item2Count;
        this.sellingItemCount = 1;
        this.maxUses = maxUses;
        this.xpValue = xpValue;
        this.priceMultiplier = 0.05F;
    }

    public MerchantOffer getOffer(Entity trader, RandomSource rand) {
        return new MerchantOffer(new ItemStack(item1, this.item1Count), new ItemStack(this.item2, this.item2Count), new ItemStack(this.sellingItem.getItem(), sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
    }
}

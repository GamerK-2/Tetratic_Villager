package net.gamerk2.tetratic_villager.trades;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.Nullable;

public class EmeraldsToItemsTrade implements VillagerTrades.ItemListing {
    private final ItemStack itemToBuy;
    private final int emeraldCount;
    private final int maxUses;
    private final int xpValue;
    private final float priceMultiplier;

    public EmeraldsToItemsTrade(ItemStack itemToBuy, int emeraldCount, int maxUses, int xpValue) {
        this.itemToBuy = itemToBuy;
        this.emeraldCount = emeraldCount;
        this.maxUses = maxUses;
        this.xpValue = xpValue;
        this.priceMultiplier = 0.05F;
    }

    @Override
    public @Nullable MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCount), this.itemToBuy, this.maxUses, this.xpValue, this.priceMultiplier);
    }
}

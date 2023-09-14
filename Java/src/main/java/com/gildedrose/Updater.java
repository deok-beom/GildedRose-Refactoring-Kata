package com.gildedrose;

import static com.gildedrose.enumerate.ItemType.*;

public class Updater {

    public static void update(Item item) {
        if (item.name.equals(AGED_BRIE.getName())) {
            updateAgedBrie(item);
            return;
        }

        if (item.name.equals(SULFURAS.getName())) {
            return;
        }

        if (item.name.equals(BACKSTAGE_PASSES.getName())) {
            updateBackstagePasses(item);
            return;
        }

        updateNormalItem(item);
    }

    private static void updateNormalItem(Item item) {
        --item.sellIn;

        if (item.quality <= 0) {
            return;
        }

        --item.quality;

        if (item.sellIn < 0 && item.quality > 0) {
            --item.quality;
        }
    }

    private static void updateAgedBrie(Item item) {
        --item.sellIn;

        if (item.quality >= 50) {
            return;
        }

        ++item.quality;

        if (item.sellIn < 0 && item.quality < 50) {
            ++item.quality;
        }
    }

    private static void updateBackstagePasses(Item item) {
        --item.sellIn;
        int increase;

        if (item.sellIn >= 10) {
            increase = 1;
        } else if (item.sellIn >= 5) {
            increase = 2;
        } else if (item.sellIn >= 0) {
            increase = 3;
        } else {
            increase = -item.quality;
        }

        item.quality = Math.min(item.quality + increase, 50);
    }
}

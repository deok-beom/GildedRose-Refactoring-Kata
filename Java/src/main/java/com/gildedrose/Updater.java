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
}

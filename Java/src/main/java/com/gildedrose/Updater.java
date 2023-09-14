package com.gildedrose;

import static com.gildedrose.enumerate.ItemType.*;

public class Updater {
    public static int DELTA = 1;

    public static void update(Item item) {
        int delta = DELTA;
        if (item.name.equals(AGED_BRIE.getName())) {
            updateAgedBrie(item, delta);
            return;
        }

        if (item.name.equals(SULFURAS.getName())) {
            return;
        }

        if (item.name.equals(BACKSTAGE_PASSES.getName())) {
            updateBackstagePasses(item);
            return;
        }

        if (item.name.startsWith(CONJURED.getName())) {
            delta *= 2;
        }

        updateItem(item, delta);
    }

    private static void updateAgedBrie(Item item, int increase) {
        --item.sellIn;

        if (item.quality >= 50) {
            return;
        }

        if (item.sellIn < 0) {
            increase *= 2;
        }

        item.quality = Math.min(item.quality + increase, 50);
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

    private static void updateItem(Item item, int decrease) {
        --item.sellIn;

        if (item.quality <= 0) {
            return;
        }

        if (item.sellIn < 0) {
            decrease *= 2;
        }

        item.quality = Math.max(item.quality - decrease, 0);
    }
}

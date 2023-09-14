package com.gildedrose;

public class Updater {

    public static void update(Item item) {
        switch (item.name) {
            default:
                updateNormalItem(item);
        }
    }

    public static void updateNormalItem(Item item) {
        --item.sellIn;

        if (item.quality <= 0) {
            return;
        }

        --item.quality;

        if (item.sellIn < 0 && item.quality > 0) {
            --item.quality;
        }
    }
}

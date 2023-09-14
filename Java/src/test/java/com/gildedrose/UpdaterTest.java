package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdaterTest {

    @Test
    @DisplayName("update_normal_item")
    void 업데이트_일반_아이템() {
        // Given
        int sellIn = 7;
        int quality = 15;
        Item item = new Item("+5 Dexterity Vest", sellIn, quality);

        // When sellIn > 0, Then
        while (sellIn > 0) {
            Updater.update(item);
            assertEquals(--sellIn, item.sellIn);
            assertEquals(--quality, item.quality);
        }

        // When sellIn <= 0, Then
        while (quality > 1) {
            Updater.update(item);
            quality = quality - 2;
            assertEquals(--sellIn, item.sellIn);
            assertEquals(quality, item.quality);
        }

        // When quality 1, Then
        quality = 0;
        Updater.update(item);
        assertEquals(--sellIn, item.sellIn);
        assertEquals(quality, item.quality);

        // When quality 0, Then
        Updater.update(item);
        assertEquals(--sellIn, item.sellIn);
        assertEquals(quality, item.quality);
    }
}

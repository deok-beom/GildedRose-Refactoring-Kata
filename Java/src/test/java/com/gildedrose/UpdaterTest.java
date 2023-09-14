package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gildedrose.enumerate.ItemType.*;
import static com.gildedrose.enumerate.ItemType.BACKSTAGE_PASSES;
import static org.junit.jupiter.api.Assertions.*;

class UpdaterTest {

    @Test
    @DisplayName("Update_normal_item")
    void 업데이트_일반_아이템() {
        // Given
        int sellIn = 7;
        int quality = 15;
        int decrease = Updater.DELTA;
        Item item = new Item("+5 Dexterity Vest", sellIn, quality);

        // When sellIn > 0, Then
        while (sellIn > 0) {
            Updater.update(item);
            quality -= decrease;
            assertEquals(--sellIn, item.sellIn);
            assertEquals(quality, item.quality);
        }

        decrease *= 2;
        // When sellIn <= 0, Then
        while (quality > 1) {
            Updater.update(item);
            quality -= decrease;
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

    @Test
    @DisplayName("Update_aged_brie")
    void 업데이트_오래된_브리치즈() {
        // Given
        int sellIn = 2;
        int quality = 45;
        Item item = new Item(AGED_BRIE.getName(), sellIn, quality);

        // When sellIn > 0 Then
        while (sellIn > 0) {
            Updater.update(item);
            assertEquals(--sellIn, item.sellIn);
            assertEquals(++quality, item.quality);
        }

        // When sellIn < 0 Then
        while (quality < 49) {
            Updater.update(item);
            assertEquals(--sellIn, item.sellIn);
            quality = quality + 2;
            assertEquals(quality, item.quality);
        }

        // When quality = 49 Then
        quality = 50;
        Updater.update(item);
        assertEquals(--sellIn, item.sellIn);
        assertEquals(quality, item.quality);

        // When quality = 50 Then
        Updater.update(item);
        assertEquals(--sellIn, item.sellIn);
        assertEquals(quality, item.quality);
    }

    @Test
    @DisplayName("Update_Sulfuras")
    void 설퍼라스_퀄리티_업데이트() {
        // Given
        int positiveSellIn = 2;
        int zeroSellIn = 0;
        int negativeSellIn = -1;
        int quality = 45;

        Item sulfurasHasPositiveSellIn = new Item(SULFURAS.getName(), positiveSellIn, quality);
        Item sulfurasHasZeroSellIn = new Item(SULFURAS.getName(), zeroSellIn, quality);
        Item sulfurasHasNegativeSellIn = new Item(SULFURAS.getName(), negativeSellIn, quality);

        // When
        Updater.update(sulfurasHasPositiveSellIn);
        Updater.update(sulfurasHasZeroSellIn);
        Updater.update(sulfurasHasNegativeSellIn);

        // Sulfuras has positive sellIn Then
        assertEquals(positiveSellIn, sulfurasHasPositiveSellIn.sellIn);
        assertEquals(quality, sulfurasHasPositiveSellIn.quality);

        // Sulfuras has zero sellIn Then
        assertEquals(zeroSellIn, sulfurasHasZeroSellIn.sellIn);
        assertEquals(quality, sulfurasHasZeroSellIn.quality);

        // Sulfuras has negative sellIn Then
        assertEquals(negativeSellIn, sulfurasHasNegativeSellIn.sellIn);
        assertEquals(quality, sulfurasHasNegativeSellIn.quality);
    }

    @Test
    @DisplayName("Update_Backstage_passes")
    void 백스테이지_입장권_퀄리티_업데이트() {
        // Given
        int sellIn = 12;
        int quality = 27;
        Item pass48 = new Item(BACKSTAGE_PASSES.getName(), sellIn, quality);
        Item pass49 = new Item(BACKSTAGE_PASSES.getName(), sellIn, 28);

        // When sellIn > 10 Then
        while (sellIn > 10) {
            Updater.update(pass48);
            Updater.update(pass49);
            assertEquals(--sellIn, pass48.sellIn);
            assertEquals(++quality, pass48.quality);
        }

        // When 5 < sellIn < 10 Then
        while (sellIn > 5) {
            Updater.update(pass48);
            Updater.update(pass49);
            assertEquals(--sellIn, pass48.sellIn);
            quality = quality + 2;
            assertEquals(quality, pass48.quality);
        }

        // When 0 < sellIn < 5 Then
        while (sellIn > 2) {
            Updater.update(pass48);
            Updater.update(pass49);
            assertEquals(--sellIn, pass48.sellIn);
            quality = quality + 3;
            assertEquals(quality, pass48.quality);
        }

        // and quality = 48, 49 Then
        quality = 50;
        Updater.update(pass48);
        Updater.update(pass49);
        assertEquals(--sellIn, pass48.sellIn);
        assertEquals(quality, pass48.quality);
        assertEquals(quality, pass49.quality);

        // and quality = 50 Then
        Updater.update(pass48);
        Updater.update(pass49);
        assertEquals(--sellIn, pass48.sellIn);
        assertEquals(quality, pass48.quality);
        assertEquals(quality, pass49.quality);

        // When sellIn = 0
        quality = 0;
        Updater.update(pass48);
        Updater.update(pass49);
        assertEquals(--sellIn, pass48.sellIn);
        assertEquals(quality, pass48.quality);
        assertEquals(quality, pass49.quality);

        // When sellIn < 0
        Updater.update(pass48);
        Updater.update(pass49);
        assertEquals(--sellIn, pass48.sellIn);
        assertEquals(quality, pass48.quality);
        assertEquals(quality, pass49.quality);
    }

    @Test
    @DisplayName("Update_conjured_item")
    void 업데이트_마법_아이템() {
        // Given
        int sellIn = 7;
        int quality = 31;
        int decrease = Updater.DELTA * 2;
        Item item = new Item("Conjured Mana Cake", sellIn, quality);

        // When sellIn > 0, Then
        while (sellIn > 0) {
            Updater.update(item);
            quality -= decrease;
            assertEquals(--sellIn, item.sellIn);
            assertEquals(quality, item.quality);
        }

        decrease *= 2;
        // When sellIn <= 0, Then
        while (quality > 1) {
            Updater.update(item);
            quality -= decrease;
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

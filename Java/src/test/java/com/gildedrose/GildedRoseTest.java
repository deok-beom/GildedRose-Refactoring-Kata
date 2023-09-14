package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gildedrose.enumerate.ItemType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("Update_quality_for_normal_item")
    void 일반_아이템_퀄리티_업데이트() {
        // Given
        int sellIn = 7;
        int quality = 15;
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", sellIn, quality)};
        GildedRose app = new GildedRose(items);

        // When sellIn > 0, Then
        while (sellIn > 0) {
            app.updateQuality();
            assertEquals(--sellIn, app.items[0].sellIn);
            assertEquals(--quality, app.items[0].quality);
        }

        // When sellIn <= 0, Then
        while (quality > 1) {
            app.updateQuality();
            quality = quality - 2;
            assertEquals(--sellIn, app.items[0].sellIn);
            assertEquals(quality, app.items[0].quality);
        }

        // When quality 1, Then
        quality = 0;
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);

        // When quality 0, Then
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
    }

    @Test
    @DisplayName("Update_quality_for_Aged_Brie")
    void 오래된_브리치즈_퀄리티_업데이트() {
        // Given
        int sellIn = 2;
        int quality = 45;
        Item[] items = new Item[]{new Item(AGED_BRIE.getName(), sellIn, quality)};
        GildedRose app = new GildedRose(items);

        // When sellIn > 0 Then
        while (sellIn > 0) {
            app.updateQuality();
            assertEquals(--sellIn, app.items[0].sellIn);
            assertEquals(++quality, app.items[0].quality);
        }

        // When sellIn < 0 Then
        while (quality < 49) {
            app.updateQuality();
            assertEquals(--sellIn, app.items[0].sellIn);
            quality = quality + 2;
            assertEquals(quality, app.items[0].quality);
        }

        // When quality = 49 Then
        quality = 50;
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);

        // When quality = 50 Then
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
    }

    @Test
    @DisplayName("Update_quality_Sulfuras")
    void 설퍼라스_퀄리티_업데이트() {
        // Given
        int positiveSellIn = 2;
        int zeroSellIn = 0;
        int negativeSellIn = -1;
        int quality = 45;

        Item sulfurasHasPositiveSellIn = new Item(SULFURAS.getName(), positiveSellIn, quality);
        Item sulfurasHasZeroSellIn = new Item(SULFURAS.getName(), zeroSellIn, quality);
        Item sulfurasHasNegativeSellIn = new Item(SULFURAS.getName(), negativeSellIn, quality);
        Item[] items = new Item[]{sulfurasHasPositiveSellIn, sulfurasHasZeroSellIn, sulfurasHasNegativeSellIn};
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Sulfuras has positive sellIn Then
        assertEquals(positiveSellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);

        // Sulfuras has zero sellIn Then
        assertEquals(zeroSellIn, app.items[1].sellIn);
        assertEquals(quality, app.items[1].quality);

        // Sulfuras has negative sellIn Then
        assertEquals(negativeSellIn, app.items[2].sellIn);
        assertEquals(quality, app.items[2].quality);
    }

    @Test
    @DisplayName("Update_quality_Backstage_passes")
    void 백스테이지_입장권_퀄리티_업데이트() {
        // Given
        int sellIn = 12;
        int quality = 27;
        Item[] items = new Item[]{
            new Item(BACKSTAGE_PASSES.getName(), sellIn, quality),
            new Item(BACKSTAGE_PASSES.getName(), sellIn, 28)
        };
        GildedRose app = new GildedRose(items);

        // When sellIn > 10 Then
        while (sellIn > 10) {
            app.updateQuality();
            assertEquals(--sellIn, app.items[0].sellIn);
            assertEquals(++quality, app.items[0].quality);
        }

        // When 5 < sellIn < 10 Then
        while (sellIn > 5) {
            app.updateQuality();
            assertEquals(--sellIn, app.items[0].sellIn);
            quality = quality + 2;
            assertEquals(quality, app.items[0].quality);
        }

        // When 0 < sellIn < 5 Then
        while (sellIn > 2) {
            app.updateQuality();
            assertEquals(--sellIn, app.items[0].sellIn);
            quality = quality + 3;
            assertEquals(quality, app.items[0].quality);
        }

        // and quality = 48, 49 Then
        quality = 50;
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
        assertEquals(quality, app.items[1].quality);

        // and quality = 50 Then
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
        assertEquals(quality, app.items[1].quality);

        // When sellIn = 0
        quality = 0;
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
        assertEquals(quality, app.items[1].quality);

        // When sellIn < 0
        app.updateQuality();
        assertEquals(--sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
        assertEquals(quality, app.items[1].quality);
    }

}

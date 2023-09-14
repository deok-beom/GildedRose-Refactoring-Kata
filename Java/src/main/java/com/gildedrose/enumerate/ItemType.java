package com.gildedrose.enumerate;

public enum ItemType {
    AGED_BRIE("Aged Brie"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert");

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

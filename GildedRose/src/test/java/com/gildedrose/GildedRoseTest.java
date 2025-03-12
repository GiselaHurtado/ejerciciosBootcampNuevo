package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Nested
    @DisplayName("Artículos Normales")
    class NormalItems {

        @Test
        @DisplayName("La calidad y SellIn se degradan normalmente")
        void testNormalItemDegrades() {
            Item[] items = {new Item("+5 Dexterity Vest", 10, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(9, items[0].sellIn);
            assertEquals(19, items[0].quality);
        }

        @Test
        @DisplayName("La calidad se degrada al doble después de SellIn")
        void testQualityDegradesTwiceAfterSellIn() {
            Item[] items = {new Item("Elixir of the Mongoose", 0, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(-1, items[0].sellIn);
            assertEquals(18, items[0].quality);
        }

        @Test
        @DisplayName("La calidad nunca es negativa")
        void testQualityNeverNegative() {
            Item[] items = {new Item("Generic Item", 5, 0)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(4, items[0].sellIn);
            assertEquals(0, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Aged Brie")
    class AgedBrieItems {

        @Test
        @DisplayName("La calidad aumenta con el tiempo")
        void testAgedBrieQualityIncreases() {
            Item[] items = {new Item("Aged Brie", 2, 0)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(1, items[0].sellIn);
            assertEquals(1, items[0].quality);
        }

        @Test
        @DisplayName("La calidad aumenta el doble después de SellIn")
        void testAgedBrieQualityIncreasesTwiceAfterSellIn() {
            Item[] items = {new Item("Aged Brie", 0, 0)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(-1, items[0].sellIn);
            assertEquals(2, items[0].quality);
        }

        @Test
        @DisplayName("La calidad no supera 50")
        void testAgedBrieQualityNeverExceeds50() {
            Item[] items = {new Item("Aged Brie", 2, 50)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(1, items[0].sellIn);
            assertEquals(50, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Sulfuras")
    class SulfurasItems {

        @Test
        @DisplayName("La calidad y SellIn no cambian")
        void testSulfurasQualityAndSellInUnchanged() {
            Item[] items = {new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(0, items[0].sellIn);
            assertEquals(80, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Backstage passes")
    class BackstagePassesItems {

        @Test
        @DisplayName("La calidad aumenta en 1")
        void testBackstagePassesQualityIncreases1() {
            Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(14, items[0].sellIn);
            assertEquals(21, items[0].quality);
        }

        @Test
        @DisplayName("La calidad aumenta en 2 cuando SellIn < 11")
        void testBackstagePassesQualityIncreases2() {
            Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(9, items[0].sellIn);
            assertEquals(22, items[0].quality);
        }

        @Test
        @DisplayName("La calidad aumenta en 3 cuando SellIn < 6")
        void testBackstagePassesQualityIncreases3() {
            Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(4, items[0].sellIn);
            assertEquals(23, items[0].quality);
        }

        @Test
        @DisplayName("La calidad cae a 0 después del concierto")
        void testBackstagePassesQualityDropsTo0AfterConcert() {
            Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(-1, items[0].sellIn);
            assertEquals(0, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Conjured")
    class ConjuredItems {

        @Test
        @DisplayName("La calidad se degrada el doble")
        void testConjuredQualityDegradesTwice() {
            Item[] items = {new Item("Conjured Mana Cake", 10, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(9, items[0].sellIn);
            assertEquals(18, items[0].quality);
        }

        @Test
        @DisplayName("La calidad se degrada el doble después de SellIn")
        void testConjuredQualityDegradesTwiceAfterSellIn() {
            Item[] items = {new Item("Conjured Mana Cake", 0, 20)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(-1, items[0].sellIn);
            assertEquals(16, items[0].quality);
        }
    }
}

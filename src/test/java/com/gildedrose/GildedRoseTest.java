package com.gildedrose;

import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;


class GildedRoseTest {

  @Test
  @DisplayName("Check that the quality updates correctly before the sellIn.")
  void testQualityUpdateBeforeSellIn() {
    Item[] list;
    list = new Item[] {
      new Item("Aged Brie", 20, 30),
      new Item("Backstage passes to a TAFKAL80ETC concert", 4, 48),
      new Item("Sulfuras, Hand of Ragnaros", 79, 80),
      new Item("Conjured", 50, 23)
    };

    GildedRose app = new GildedRose(list);

    int nbjours = 20;

    // Calcul des valeurs correctes selon la documentation
    int quality_agedBrie = app.items[0].quality + nbjours;
    int quality_backstage = app.items[1].quality;
    int quality_conjured = app.items[3].quality - nbjours;

    for (int i=0; i < nbjours; i++){
      app.updateQuality();
      // Mise à jour de la qualité de backstage dans une variable locale, selon les infos de la doc
      if (app.items[1].sellIn >= 10) {
        quality_backstage += 1;
      } else if (app.items[1].sellIn >= 5) {
        quality_backstage += 2;
      } else if (app.items[1].sellIn >= 0) {
        quality_backstage += 3;
      } else {
        quality_backstage = 0;
      }
    }

    // Vérification des valeurs
    assertThat("Aged brie quality updated correctly.", app.items[0].quality, is(equalTo(quality_agedBrie)));
    assertThat("Backstage quality updated correctly.", app.items[1].quality, is(equalTo(quality_backstage)));
    assertThat("Sulfuras quality updated correctly.", app.items[2].quality, is(equalTo(80)));
    assertThat("Conjured quality updated correctly.", app.items[3].quality, is(equalTo(quality_conjured)));
  }

  @Test
  @DisplayName("Check how the max quality.")
  void testMaxQuality() {
    Item[] list;
    list = new Item[] {
      new Item("Aged Brie", 20, 30),
      new Item("Backstage passes to a TAFKAL80ETC concert", 20, 30),
      new Item("Sulfuras, Hand of Ragnaros", 20, 80),
      new Item("Conjured", 20, 23)
    };

    GildedRose app = new GildedRose(list);
    int nbjours = 20;

    for (int i=0; i < nbjours; i++){
      app.updateQuality();
    }

    // Vérification des valeurs
    assertThat("Aged brie max quality value.", app.items[0].quality, is(equalTo(50)));
    assertThat("Backstage max quality value.", app.items[1].quality, is(equalTo(50)));
    assertThat("Sulfuras max quality value.", app.items[2].quality, is(equalTo(80)));
    // Inutile de tester le max d'un élément Conjured, car sa qualité n'augmente pas.
  }

  @Test
  @DisplayName("Check that the initial quality corresponds to the specifications.")
  void testDefaultQuality() {
    Item[] list;
    list = new Item[] {
      new Item("Aged Brie", 20, 60),
      new Item("Backstage passes to a TAFKAL80ETC concert", 20, 60),
      new Item("Sulfuras, Hand of Ragnaros", -20, -90),
      new Item("Conjured", 20, 60)
    };

    GildedRose app = new GildedRose(list);
    int nbjours = 1;

    for (int i=0; i < nbjours; i++){
      app.updateQuality();
    }

    // Remplacer ces assertThat par des assertThrows.
    assertThat("Aged brie max quality value.", app.items[0].quality, is(not(equalTo(50))));
    assertThat("Backstage max quality value.", app.items[1].quality, is(not(equalTo(50))));
    assertThat("Sulfuras max quality value.", app.items[2].quality, is(not(equalTo(80))));
    assertThat("Conjured quality updated correctly.", app.items[3].quality, is(not(equalTo(48))));
  }

  @Test
  @DisplayName("Check how the quality updates after the sellIn.")
  void testQualityAfterSellIn() {
    Item[] list;
    list = new Item[] {
      new Item("Aged Brie", 0, 30),
      new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
      new Item("Sulfuras, Hand of Ragnaros", -10, 80),
      new Item("Conjured", 0, 50)
    };

    GildedRose app = new GildedRose(list);

    int nbjours = 20;

    int quality_agedBrie = Math.min(app.items[0].quality + ((nbjours-app.items[0].sellIn)*2), 50);
    int quality_backstage = 0;
    int quality_conjured = Math.max(app.items[3].quality - ((nbjours-app.items[3].sellIn)*2), 0);

    for (int i=0; i < nbjours; i++){
      app.updateQuality();
    }

    assertThat("Aged brie quality updated correctly after sellIn.", app.items[0].quality, is(equalTo(quality_agedBrie)));
    assertThat("Backstage quality updated correctly after sellIn.", app.items[1].quality, is(equalTo(quality_backstage)));
    assertThat("Sulfuras quality updated correctly after sellIn.", app.items[2].quality, is(equalTo(80)));
    assertThat("Conjured quality updated correctly after sellIn.", app.items[3].quality, is(equalTo(quality_conjured)));
  }

  @Test
  @DisplayName("Check that Item.toString() works.")
  void testToString() {
    Item i = new Item("Aged Brie", 10, 30);
    assertEquals("Aged Brie, 10, 30", i.toString());
  }
}

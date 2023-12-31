package com.gildedrose;

import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;


class GildedRoseTest {

  @Test
  @DisplayName("Check the initial values.")
  void testInitialValues() throws Exception {

    assertThrows(AssertionError.class, () -> new AgedBrie(20, -30), "Initial value negative.");
    assertThrows(AssertionError.class, () -> new BackstagePasses(4, -48), "Initial value negative.");
    assertThrows(AssertionError.class, () -> new Conjured(50, -23), "Initial value negative.");

    assertThrows(AssertionError.class, () -> new AgedBrie(20, -1), "Initial value negative.");
    assertThrows(AssertionError.class, () -> new BackstagePasses(4, -1), "Initial value negative.");
    assertThrows(AssertionError.class, () -> new Conjured(50, -1), "Initial value negative.");
  }

  @Test
  @DisplayName("Test isBetween static method in Baackstage item.")
  void testIsBetween(){
    assertFalse(BackstagePasses.isBetween(5, 6, 10));
    assertTrue(BackstagePasses.isBetween(6, 6, 10));
    assertTrue(BackstagePasses.isBetween(8, 6, 10));
    assertTrue(BackstagePasses.isBetween(10, 6, 10));
    assertFalse(BackstagePasses.isBetween(11, 6, 10));
  }

  @Test
  @DisplayName("Check that the quality updates correctly before the sellIn.")
  void testQualityUpdateBeforeSellIn() throws Exception {
    Item[] list;
    list = new Item[] {
      new AgedBrie(20, 30),
      new BackstagePasses(4, 48),
      new Sulfuras(),
      new Conjured(50, 23)
    };

    GildedRose app = new GildedRose(list);

    int nbjours = 20;

    // Calcul des valeurs correctes selon la documentation
    int quality_agedBrie = app.items[0].quality + nbjours;
    int quality_backstage = app.items[1].quality;
    int quality_conjured = Math.max(app.items[3].quality - (nbjours*2), 0);

    for (int i=0; i < nbjours; i++){
      app.toNextDay();
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

    list = new Item[] {
      new BackstagePasses(11, 48)
    };
    app = new GildedRose(list);
    nbjours = 1;
    quality_backstage = app.items[0].quality;
    for (int i=0; i < nbjours; i++){
      app.toNextDay();
      // Mise à jour de la qualité de backstage dans une variable locale, selon les infos de la doc
      if (app.items[0].sellIn >= 10) {
        quality_backstage += 1;
      } else if (app.items[0].sellIn >= 5) {
        quality_backstage += 2;
      } else if (app.items[0].sellIn >= 0) {
        quality_backstage += 3;
      } else {
        quality_backstage = 0;
      }
      assertThat("Backstage quality updated correctly.", app.items[0].quality, is(equalTo(quality_backstage)));

    }
  }

  @Test
  @DisplayName("Check how the max quality.")
  void testMaxQuality() throws Exception {
    Item[] list;
    list = new Item[] {
      new AgedBrie( 20, 50),
      new BackstagePasses(20, 30),
      new Sulfuras(),
      new Conjured(20, 23)
    };

    GildedRose app = new GildedRose(list);
    int nbjours = 20;

    for (int i=0; i < nbjours; i++){
      app.toNextDay();
    }

    // Vérification des valeurs
    assertThat("Aged brie max quality value.", app.items[0].quality, is(equalTo(50)));
    assertThat("Backstage max quality value.", app.items[1].quality, is(equalTo(50)));
    assertThat("Sulfuras max quality value.", app.items[2].quality, is(equalTo(80)));
  }

  @Test
  @DisplayName("Check how the quality updates after the sellIn.")
  void testQualityAfterSellIn() throws Exception {
    Item[] list;
    list = new Item[] {
      new AgedBrie(-1, 30),
      new BackstagePasses(-10, 49),
      new Sulfuras(),
      new Conjured(-1, 50)
    };

    GildedRose app = new GildedRose(list);

    int nbjours = 20;

    int quality_agedBrie = Math.min(app.items[0].quality + ((nbjours-app.items[0].sellIn)*2), 50);
    int quality_backstage = 0;
    int quality_conjured = Math.max(app.items[3].quality - ((nbjours-app.items[3].sellIn)*4), 0);

    for (int i=0; i < nbjours; i++){
      app.toNextDay();
    }

    assertThat("Aged brie quality updated correctly after sellIn.", app.items[0].quality, is(equalTo(quality_agedBrie)));
    assertThat("Backstage quality updated correctly after sellIn.", app.items[1].quality, is(equalTo(quality_backstage)));
    assertThat("Sulfuras quality updated correctly after sellIn.", app.items[2].quality, is(equalTo(80)));
    assertThat("Conjured quality updated correctly after sellIn.", app.items[3].quality, is(equalTo(quality_conjured)));

    list = new Item[] {
      new AgedBrie(-1, 30)
    };
    app = new GildedRose(list);
    nbjours = 5;
    quality_agedBrie = Math.min(app.items[0].quality + (nbjours*2), 50);

    for (int i=0; i < nbjours; i++){
      app.toNextDay();
    }
    assertThat("2. Aged brie quality updated correctly after sellIn.", app.items[0].quality, is(equalTo(quality_agedBrie)));

  }

  @Test
  @DisplayName("Check that Item.toString() works.")
  void testToString() throws Exception {
    Item i = new AgedBrie(10, 30);
    assertEquals("Aged Brie, 10, 30", i.toString());
  }
}

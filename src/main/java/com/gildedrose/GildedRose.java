package com.gildedrose;

class GildedRose {
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void toNextDay(){
    for (Item i:this.items) {
      i.updateQuality();
      i.sellIn = Math.max(i.sellIn-1, -1);
    }
  }
}

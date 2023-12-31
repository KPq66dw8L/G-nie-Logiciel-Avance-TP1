package com.gildedrose;

import static org.hamcrest.MatcherAssert.assertThat;

public class BackstagePasses extends Item{
  public BackstagePasses(int sellIn, int quality) throws Exception {
    super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
        try {
          assertThat("Quality must be positive.", quality>-1);
        } catch (AssertionError e){
          throw new AssertionError("Quality must be positive.");
        }
  }
  public static boolean isBetween(int x, int lower, int upper) {
    return lower <= x && x <= upper;
  }

  @Override
  public void updateQuality(){
    if (this.sellIn > -1){
      if (isBetween(sellIn, 6, 10)){
        this.quality = Math.min(this.quality+2, 50);
      } else if (isBetween(sellIn, 0, 5)){
        this.quality = Math.min(this.quality+3, 50);
      } else {
        this.quality = Math.min(this.quality+1, 50);
      }
    } else {
      this.quality = 0;
    }
  }

}

package com.gildedrose;

import static org.hamcrest.MatcherAssert.assertThat;

public class AgedBrie extends Item{
  public AgedBrie(int sellIn, int quality) throws Exception {
    super("Aged Brie", sellIn, quality);
        try {
          assertThat("Quality must be positive.", quality>-1);
        } catch (AssertionError e){
          throw new AssertionError("Quality must be positive.");
        }
  }
  @Override
  public void updateQuality(){
    if (quality < 50){
      if (this.sellIn > -1){
        this.quality ++;
      } else{
        this.quality = Math.min(this.quality+2, 50);
      }
    }
  }
}

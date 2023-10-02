package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class AgedBrie extends Item{
  public AgedBrie(int sellIn, int quality) throws Exception {
    super("Aged Brie", sellIn, quality);
    //TODO
    //    try {
    //      assertThat("Quality must be positive.", quality>=0);
    //    } catch (Exception e){
    //      throw new Exception("Quality must be positive.");
    //    }
  }
  //TODO: corriger mutations
  @Override
  public void updateQuality(){
    if (quality < 50){
      if (this.sellIn >= 0){
        this.quality ++;
      } else{
        this.quality = Math.min(this.quality+2, 50);
      }
    }
  }
}

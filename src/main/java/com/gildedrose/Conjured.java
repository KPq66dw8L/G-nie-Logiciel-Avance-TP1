package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class Conjured extends Item{
  public Conjured(int sellIn, int quality) throws Exception {
    super("Conjured", sellIn, quality);
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
    if (this.sellIn >= 0){
      this.quality = Math.max(this.quality-2, 0);
    } else{
      this.quality = Math.max(this.quality-4, 0);
    }
    if (quality < 0 ){this.quality=0;}
  }
}

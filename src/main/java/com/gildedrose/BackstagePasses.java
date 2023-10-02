package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class BackstagePasses extends Item{
  public BackstagePasses(int sellIn, int quality) throws Exception {
    super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    //TODO
    //    try {
    //      assertThat("Quality must be positive.", quality>=0);
    //    } catch (Exception e){
    //      throw new Exception("Quality must be positive.");
    //    }
  }
  //TODO: corriger mutations
  public static boolean isBetween(int x, int lower, int upper) {
    return lower <= x && x <= upper;
  }

  @Override
  public void updateQuality(){
    if (this.sellIn >= 0){
      if (isBetween(sellIn, 6, 10)){
        this.quality = Math.min(this.quality+2, 50); //TODO: demander si autorisé d'utiliser Math.min/max
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

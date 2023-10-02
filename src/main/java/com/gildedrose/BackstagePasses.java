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

  @Override
  public void updateQuality(){
  }
}

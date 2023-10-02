package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class Sulfuras extends Item{
  public Sulfuras() {
    super("Sulfuras, Hand of Ragnaros", 0, 80);
  }
  @Override
  public void updateQuality() {
    // Nothing to see here.
  }
}

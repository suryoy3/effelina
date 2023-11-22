package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Inner class to represent item details including contracts.
 */
public class ItemDetails {

  private final Item item;
  private final List<Contract> contracts;

  public ItemDetails(Item item, List<Contract> contracts) {
    this.item = new Item(item);
    this.contracts = new ArrayList<>(contracts);
  }

  public Item getItem() {
    return new Item(item);
  }

  public List<Contract> getContracts() {
    return new ArrayList<>(contracts);
  }

}
    

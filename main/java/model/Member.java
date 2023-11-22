package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Creating class Member.
 */
public class Member { 
  private String name;
  private String email;
  private String mophnumber;
  private String memberid;
  private int credits;
  private int creationDate;
  private List<Item> ownedItems;
  private List<Contract> contracts;


  /**
 * constructor.
 */

  public Member(String name, String email, String mophnumber, String memberid) {
    this.name = name;
    this.email = email;
    this.mophnumber = mophnumber;
    this.memberid = memberid;
    this.creationDate = Time.getInstance().getCurrentDay();
    this.credits = 100;
    this.ownedItems = new ArrayList<>();
    this.contracts = new ArrayList<>();
  }

  /**
   * Copy Constructor.
   */

  public Member(Member otherMember) {
    this.name = otherMember.name;
    this.email = otherMember.email;
    this.mophnumber = otherMember.mophnumber;
    this.memberid = otherMember.memberid; // Assuming member ID should be same for the copy
    this.credits = otherMember.credits;
    this.creationDate = otherMember.creationDate;

    this.ownedItems = new ArrayList<>();
    for (Item item : otherMember.ownedItems) {
      this.ownedItems.add(new Item(item)); // Assuming Item has a copy constructor
    }

    this.contracts = new ArrayList<>();
    for (Contract contract : otherMember.contracts) {
      this.contracts.add(new Contract(contract)); // Assuming Contract has a copy constructor
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name; 
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMoPhNumber() {
    return mophnumber;
  }

  public void setMoPhNumber(String mophnumber) {
    this.mophnumber = mophnumber;
  }

  public String getMemberId() {  // Memberid not modified when created
    return memberid;
  }

  /**
   * same here creation date is not modified.
   */
  public int getCreationDate() {
    return creationDate;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int currentcredit) {
    this.credits = currentcredit;
  }

  /**
   * Returns a direct reference to ownedItems.
   */
  public List<Item> getOwnedItems() {
    List<Item> copiedItems = new ArrayList<>();
    for (Item item : this.ownedItems) {
      copiedItems.add(new Item(item)); // Assuming Item has a copy constructor
    }
    return copiedItems;
  }

  /**
   * defensive copy.
   */
  public void setItems(List<Item> items) {
    this.ownedItems = new ArrayList<>();
    for (Item item : items) {
      this.ownedItems.add(new Item(item));
    }
  }

  public int getOwnedItemsCount() {
    return ownedItems.size();
  }

  public void addItem(Item item) {
    this.ownedItems.add(item);
  }

  public void removeItem(Item item) {
    ownedItems.remove(item);
  }

  /**
   * method to get full member info.
   */
  public String getFullInformation() {
    return "name: " + name + "\nEmail: " + email + "\nCurrent Credits " + credits  
      + "\nNumber of Owned Items: " + ownedItems.size() + "\nMember ID: " + memberid;

  }

  /**
   * Decreases the member's credits by the specified amount.
   */
  public void payCredits(long amount) {
    if (amount > this.credits) {
      throw new IllegalArgumentException("Insufficient credits to complete transaction.");
    }
    this.credits -= amount;
  }

  /**
   * Increases the member's credits by the specified amount.
   */
  public void earnCredits(long amount) {
    this.credits += amount;
  }



  /**
   * find item by name in member class.
   */
  public Optional<Item> getItemByName(String itemName) {
    for (Item item : this.ownedItems) {
      if (item.getName().equals(itemName)) {
        return Optional.of(new Item(item)); // Return a copy
      }
    }
    return Optional.empty();
  }

  public boolean hasEnoughCredits(int costPerDay, int duration) {
    int totalCost = costPerDay * duration;
    return this.credits >= totalCost;
  }

  /**
   * Associates a contract with this member.
   *
   * @param contract The contract to associate.
   */
  public void addContract(Contract contract) {
    this.contracts.add(new Contract(contract));
  }

}

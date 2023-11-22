package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Creating a list that holds Member.
 */
public class MemberListManager {
  private final List<Member> memberList;
  private final List<Item> itemList;
  private final List<Contract> contractList;


  /**
   * Constructor.
   */
  public MemberListManager() {
    this.memberList = new ArrayList<>();
    this.itemList = new ArrayList<>();
    this.contractList = new ArrayList<>();
  }

  /**
   * Method to add member to List.
   */
  public boolean addMember(Member member) {
    return memberList.add(member);
  }
  


  /**
    * Delete a memebr using their unique ID.
    */
  public boolean deleteMemberById(String memberId) {
    return memberList.removeIf(member -> member.getMemberId().equals(memberId));
  }


  /**
   * Mehtod to update Member.
   */
  public void updateMemberInfo(String memberId, String newName, String newEmail,
        String newMoPhoNumber) {
    for (Member member : memberList) {
      if (member.getMemberId().equals(memberId)) {
        member.setName(newName);
        member.setEmail(newEmail);
        member.setMoPhNumber(newMoPhoNumber);
        System.out.println("Member with ID: " + memberId + " has been updated.");
        return;
      }
    }
    System.out.println("Member with ID: " + memberId + " not found.");
  }

  /**
   * Method to check if an email already exist in the member list.
   */
  public boolean doesEmailExist(String email) {
    for (Member member : memberList) {
      if (member.getEmail().equalsIgnoreCase(email)) {
        return true;
      }
    }
    return false;
  }

  /**
    * Check if a phone number already exists in the member list.
    */
  public boolean doesPhoneNumberExist(String phoneNumber) {
    for (Member member : memberList) {
      if (member.getMoPhNumber().equals(phoneNumber)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Find a member by their ID (useful for displaying information in the UI).
   */
  public Optional<Member> findMemberById(String memberId) {
    return memberList.stream()
                     .filter(member -> member.getMemberId().equals(memberId))
                     .findFirst();
  }

  
  /**
     * List all members.
     */
  public List<Member> getAllMembers() {
    return new ArrayList<>(memberList);
  }

  /**
   * find memebers by the items name.
   */
  public Optional<Member> findMemberWithItem(String itemName) {
    for (Member member : memberList) { 
      Optional<Item> item = member.getItemByName(itemName);
      if (item.isPresent()) {
        return Optional.of(member);
      }
    }
    return Optional.empty();
  }

  /**
     * Adds a new item to the list and associates it with a member.
     */
  public Item addItem(Member owner, String category, String name,
      String description, int costPerDay) {
    Item newItem = new Item(owner, category, name, description, costPerDay);
    itemList.add(newItem); // Add the new item to your system's item list
    owner.earnCredits(100); // Add 100 credits to the owner

    owner.addItem(newItem); // Add the new item to the owner's list of owned items

    return newItem; // Return the newly created item
  }


  /**
     * Deletes an item by its ID.
     */
  public boolean deleteItem(String itemId) {
    Optional<Item> itemOpt = itemList.stream()
                                       .filter(item -> item.getItemId().equals(itemId))
                                       .findFirst();
  
    if (itemOpt.isPresent()) {
      Item item = itemOpt.get();
  
      memberList.stream()
                    .filter(member -> member.getMemberId().equals(item.getOwner().getMemberId()))
                    .findFirst()
                    .ifPresent(owner -> owner.removeItem(item));
  
      // Now remove the item from the system's list of items
      itemList.remove(item);
      return true;
    }
  
    return false; // Item not found
  }
   

  /**
    * Updates the information of an existing item.
     */
  public void updateItem(String itemId, String category,
      String name, String description, int costPerDay) {
    itemList.stream()
          .filter(item -> item.getItemId().equals(itemId))
          .findFirst()
          .ifPresent(item -> {
            item.setCategory(category);
            item.setName(name);
            item.setDescription(description);
            item.setCostPerDay(costPerDay);
          });
  }

  /**
 * Retrieves item details, including associated contracts.
 */
  public ItemDetails viewItemDetails(String itemId) {
    return itemList.stream()
      .filter(item -> item.getItemId().equals(itemId))
      .map(item -> new ItemDetails(item, item.getContracts()))
      .findFirst()
      .orElse(null);
  }

  /**
   * Method to confirm deletion of Item.
   */
  public Optional<Item> getItemById(String itemId) {
    return itemList.stream()
    .filter(item -> item.getItemId().equals(itemId))
    .findFirst();
  }

  /**
   * Adds a contract to the system.
   */
  public void addContract(Contract contract) {
    contractList.add(contract); // Add the contract to the list

    // Optionally, update the associated item and members
    contract.getItem().addContract(contract);
    contract.getLender().addContract(contract);
    contract.getBorrower().addContract(contract);

  }


}

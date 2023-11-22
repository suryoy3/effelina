package model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID; // For generating unique IDs

/**
 * Represents an item in the system.
 */
public class Item {
  private String itemId;
  private String name;
  private int creationDay;
  private String category;
  private String description;
  private int costPerDay;
  private boolean available;
  private Member owner;
  private List<Contract> contracts;
  private final CategoryValidator categoryValidator = new CategoryValidator();
  private Set<Integer> bookedDays;
  private int timesBorrowed;

  /**
     * Creates a new item with specified details.
     *
     * @param owner The owner of the item.
     * @param category The category of the item.
     * @param name The name of the item.
     * @param description A brief description of the item.
     * @param costPerDay The cost per day for renting the item.
     */
  public Item(Member owner, String category, String name, String description, int costPerDay) {
    this.itemId = UUID.randomUUID().toString().substring(0, 6); // Generate a unique IDS
    this.owner = new Member(owner);
    this.name = name;
    this.category = validateAndSetCategory(category);
    this.description = description;
    this.creationDay = Time.getInstance().getCurrentDay();
    this.costPerDay = costPerDay;
    this.available = true;
    this.contracts = new ArrayList<>();
    this.bookedDays = new HashSet<>();
    this.timesBorrowed = 0;
  }

  /**
   * Copy constructor.
   */
  public Item(Item otherItem) {
    this.itemId = otherItem.itemId;
    this.name = otherItem.name;
    this.creationDay = otherItem.creationDay;
    this.category = otherItem.category;
    this.description = otherItem.description;
    this.costPerDay = otherItem.costPerDay;
    this.available = otherItem.available;
    this.owner = new Member(otherItem.owner); 
    this.contracts = new ArrayList<>(otherItem.contracts);
    this.bookedDays = new HashSet<>(otherItem.bookedDays);
    this.timesBorrowed = otherItem.timesBorrowed;
  }

  // Getters and Setters
  public String getItemId() {
    return itemId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCreationDate() {
    return creationDay;
  }

  public void setCreationDate(int creationDay) {
    this.creationDay = creationDay;
  }

  public void addContract(Contract contract) {
    this.contracts.add(contract);
    this.timesBorrowed++;
  }

  public int getTimesBorrowed() {
    return timesBorrowed;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = validateAndSetCategory(category);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  /**
   * Method to check if item is available.
   */
  public boolean isAvailable(int currentDay, int duration) {
    for (int day = currentDay; day < currentDay + duration; day++) {
      if (bookedDays.contains(day)) {
        return false; // The item is already booked on one of the days
      }
    }
    return true; // The item is available for the entire duration
  }

  /**
   * Method to book the item.
   */
  public void bookItem(int startDay, int duration) {
    for (int day = startDay; day < startDay + duration; day++) {
      bookedDays.add(day);
    }
  }

  /**
     * Marks the item as unavailable for the specified duration starting from a given day.
     *
     * @param startDay The day from which the item becomes unavailable.
     * @param duration The number of days for which the item will be unavailable.
     */
  public void markUnavailable(int startDay, int duration) {
    for (int day = startDay; day < startDay + duration; day++) {
      bookedDays.add(day);
    }
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public Member getOwner() {
    return new Member(this.owner);
  }

  public void setOwner(Member owner) {
    this.owner = new Member(owner);
  }

  /**
   * Returns a reference to the list of contracts.
   */
  public List<Contract> getContracts() {
    List<Contract> contractsCopy = new ArrayList<>();
    for (Contract contract : this.contracts) {
      contractsCopy.add(new Contract(contract));
    }
    return contractsCopy;
  }

  public void setContracts(List<Contract> contracts) {
    this.contracts = new ArrayList<>(contracts);
  }

  // ... other methods ...

  /**
   * Validates the category.
   */
  private String validateAndSetCategory(String category) {
    if (categoryValidator.isValidCategory(category)) {
      return category;
    } else {
      throw new IllegalArgumentException("Invalid category: " + category);
    }
  }

}

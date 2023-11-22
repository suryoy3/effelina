package model;

/**
 * creating class called Contract.
 */

public class Contract {
  private int startDate;
  private int endDate;
  private Item item;
  private Member lender;
  private Member borrower;
  private long totalPrice;

  /**
 * Constructor for the Contract class.
 * This constructor initializes a new contract with the given parameters.
 */

  public Contract(int startDate, int endDate, Item item, Member lender, Member borrower) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.item = new Item(item);
    this.lender = new Member(lender);
    this.borrower = new Member(borrower);
    this.totalPrice = calculateTotalPrice();
    contractTransaction();
  }
  
  /**
   * Copy constructor.
   */
  public Contract(Contract otherContract) {
    this.startDate = otherContract.startDate;
    this.endDate = otherContract.endDate;

    // Assuming Item and Member classes have copy constructors
    this.item = new Item(otherContract.item); 
    this.lender = new Member(otherContract.lender);
    this.borrower = new Member(otherContract.borrower);

    this.totalPrice = otherContract.totalPrice;
  }


  /**
   * Calculates the total price based on the duration and item's cost per day.
   * Assumes endDate is inclusive.
   * 
   */
  public long calculateTotalPrice() {
    long duration = endDate - startDate + 1;
    return duration * item.getCostPerDay();
  }

  
  
  /**
   * Handles the transaction of credits between lender and borrower,
   * and associates the contract with the item.
   */

  private void contractTransaction() {
    if (borrower.getCredits() < totalPrice) {
      throw new IllegalArgumentException("Insufficient credits.");
    }

    lender.earnCredits(totalPrice);
    borrower.payCredits(totalPrice);
    item.getContracts().add(this); // Associate the contract with the item
  }

  public int getStartDate() {
    return startDate;
  }

  public int getEndDate() {
    return endDate;
  }

  public Item getItem() {
    return new Item(this.item);
  }

  public Member getLender() {
    return new Member(this.borrower);
  }

  public Member getBorrower() {
    return new Member(this.borrower);
  }

}
package view;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;
import model.Time;

/**
 * class menu.
 */
public class Menu {
  private Scanner scanner;

  public Menu() {
    this.scanner = new Scanner(System.in, Charset.forName("UTF-8"));
  }

  /**
   * display main menu.
   */
  public void displayMainMenu() {
    System.out.println("=== Main Menu ===");
    System.out.println("1. Member Menu");
    System.out.println("2. Item Menu");
    System.out.println("3. Contract Menu");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * member Menu.
   */
  public void displayMemberMenu() {
    System.out.println("---- Member Options ----");
    System.out.println("1. Create New Member");
    System.out.println("2. Delete Member");
    System.out.println("3. Change Member Information");
    System.out.println("4. View Specific Member Info");
    System.out.println("5. List All Members (Simple)");
    System.out.println("6. List All Members (Verbose)");
    System.out.println("7. Back to Main Menu");
    System.out.print("Please choose an option (1-7): ");

  }

  public void showInvalidOptionMessage() {
    System.out.println("Invalid option. Try again.");
  }
  
  public void showMemberNotFoundMessage() {
    System.out.println("Member not found.");
  }

  public String promptForName() {
    System.out.print("Enter member's name: ");
    return scanner.nextLine();
  }

  public String promptForEmail() {
    System.out.print("Enter member email: ");
    return scanner.nextLine();
  } 

  public String promptForMoPhNumber() {
    System.out.print("Enter member mobile phone number: ");
    return scanner.nextLine();
  }

  public int promptForMemberId() {
    System.out.print("Enter member's ID: ");
    return scanner.nextInt();
  }

  public String displayEmailExistsError() {
    System.out.println("Email already exists. Please enter a unique email.");
    return scanner.nextLine();
  }

  public void displayPhoneNumberExistsError() {
    System.out.println("Phone Number already exists. Please enter a unique phone number.");
   
  }

  /**
   * Method to display when a member has been added.
   */
  public void displayNewMemberAdded(String memberId, List<Member> allMembers) {
    displayMessage("New member added with ID: " + memberId);
    displayMessage("Current member list: ");
    for (Member member : allMembers) {
      displaySimpleMemberInfo(member);
    }
  }



  public void showGeneratedMemberId(String id) {
    System.out.println("New member created with ID: " + id);
  }

  public void showSuccessMessage() {
    System.out.println("Operation successful!");
  }

  public void showSuccessMessage(String message) {
    System.out.println(message);
  }

  public void showDeleteSuccessMessage() {
    System.out.println("Member deleted successfully!");
  }


  public String promptForNewEmail() {
    System.out.print("Enter new email: ");
    return scanner.nextLine();
  }

  public String promptForNewPhoneNumber() {
    System.out.print("Enter new phone number: ");
    return scanner.nextLine();
  }

  /**
   * Display member info Verbose.
   */
  public void displayMemberInfoVerbose(Member member) {
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    // Include any other member details you wish to display
  }

  /**
 * Method to display item details when selecting verbose.
 */
  public void displayItemDetails(Item item) {
    System.out.println("Item Name: " + item.getName());
    System.out.println("Item Category: " + item.getCategory());
    // Include other item details as needed
  }

  /**
   * Method to display contract details.
   */
  public void displayContractDetailsVerbose(Contract contract) {
    System.out.println("Contract for item: " + contract.getItem().getName());
    System.out.println("Lent to: " + contract.getBorrower().getName());
    System.out.println("From Day: " + contract.getStartDate()
        + " to Day: " + contract.getEndDate());
    // Include other contract details as needed
  }

  /**
   * display get member info simple.
   */
  public void displaySimpleMemberInfo(Member member) {
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Current Credits: " + member.getCredits());
    // I'm assuming here that Member has a method like `getOwnedItemsCount()`. 
    // If not, you'd retrieve the number of owned items however it's stored in the Member class.
    System.out.println("Number of Owned Items: " + member.getOwnedItemsCount());
    System.out.println("----------------------------------");  // To separate members visually
  }


  /**
 * Display member info.
 */
  public void displayMemberInfo(Member member) {
    System.out.println("======= Member Information =======");
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone number: " + member.getMoPhNumber());
    System.out.println("Current Credits: " + member.getCredits());
    System.out.println("Number of Owned Items: " + member.getOwnedItems().size());
    System.out.println("ID: " + member.getMemberId());
    System.out.println("=================================");
  }


  /**
   * display item menu.
   */
  public void displayItemMenu() {
    System.out.println("\nItem Menu:");
    System.out.println("1. Add New Item");
    System.out.println("2. Delete Item");
    System.out.println("3. Change item information");
    System.out.println("4. View item and contracts");
    System.out.println("5. Return to Main Menu");
    System.out.print("Enter your choice: ");
  }

  public String promptForItemCategory() {
    System.out.print("Enter item's category (Tool, Vehicle, Game, Toy, Sport, Other): ");
    return scanner.nextLine();
  }

  public String promptForItemName() {
    System.out.print("Enter the new item's name: ");
    return scanner.nextLine();
  }

  public String promptForExistingItemNameOrId() {
    System.out.print("Enter the item's name or ID: ");
    return scanner.nextLine();
  }

  public String promptForItemId() {
    System.out.print("Enter the item ID: ");
    return scanner.nextLine();
  }

  public String promptForItemDescription() {
    System.out.print("Enter a short description for the item: ");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter the cost per day for an item.
   */

  public int promptForItemCostPerDay() {
    System.out.print("Enter item's cost per day: ");
    int costPerDay = scanner.nextInt();
    scanner.nextLine();
    return costPerDay;
  }


  public void showItemNotFoundMessage() {
    System.out.println("Item not found.");
  }

  public void showItemRemovedMessage() {
    System.out.println("Item successfully removed!");
  }

  public void showErrorMessage(String message) {
    System.out.println(message);
  }

  public void showErrorRemovingItemMessage() {
    System.out.println("Error in removing item. Try again.");
  }

  public String promptForNewCategory() {
    System.out.println("Enter new category (Tool, Vehicle, Game, Toy, Sport, Other): ");
    return scanner.nextLine();
  }

  public String promptForNewName() {
    System.out.println("Enter new name: ");
    return scanner.nextLine();
  }

  public String promptForNewDescription() {
    System.out.println("Enter new description: ");
    return scanner.nextLine();
  }

  public int promptForNewCostPerDay() {
    System.out.println("Enter new cost per day: ");
    return Integer.parseInt(scanner.nextLine());
  }

  public void showItemUpdatedMessage() {
    System.out.println("Item information updated successfully.");
  }

  public void showErrorRetrievingItemMessage() {
    System.out.println("Error retrieving item details. Please try again.");
  }

  /**
 * Displaying contract details.
 */
  public void displayItemInfo(Item item, Time time) {
    System.out.println("Item Details:");
    System.out.println("Name: " + item.getName());
    System.out.println("Category: " + item.getCategory());
    System.out.println("Description: " + item.getDescription());
    System.out.println("Cost Per Day: " + item.getCostPerDay());
    System.out.println("Owner ID: " + item.getOwner().getMemberId());
    System.out.println("Creation Date: " + item.getCreationDate());

    String availability = item.isAvailable(time.getCurrentDay(), 1) ? "Available" : "Unavailable";
    System.out.println("Availability: " + availability);

    // Display contract details if the item is currently unavailable
    if (!availability.equals("Available")) {
      displayItemContractDetails(item, time.getCurrentDay());
    }
  }

  private void displayItemContractDetails(Item item, int currentDay) {
    for (Contract contract : item.getContracts()) {
      if (isContractActive(contract, currentDay)) {
        Member lender = contract.getLender();
        Member borrower = contract.getBorrower();

        System.out.println("Contract Details:");
        System.out.println("Start Date: " + contract.getStartDate());
        System.out.println("End Date: " + contract.getEndDate());
        System.out.println("Lender ID: " + lender.getMemberId());
        System.out.println("Lender Name: " + lender.getName());
        System.out.println("Lender Email: " + lender.getEmail());
        System.out.println("Lender Phone: " + lender.getMoPhNumber());
        System.out.println("Borrower ID: " + borrower.getMemberId());
        System.out.println("Borrower Name: " + borrower.getName());
        System.out.println("Borrower Email: " + borrower.getEmail());
        System.out.println("Borrower Phone: " + borrower.getMoPhNumber());
        // Additional details if needed
        break; // Assuming you want to display only the active contract
      } 
    }
  }

  private boolean isContractActive(Contract contract, int currentDay) {
    return currentDay >= contract.getStartDate() && currentDay <= contract.getEndDate();
  }


  public void showNoContractsMessage() {
    System.out.println("No contracts associated with this item.");
  }



  /**
   * display yhe menu for contract.
   */
  public void displayContractMenu() {
    System.out.println("\nContract Menu:");
    System.out.println("1. Create New Contract");
    System.out.println("2. Return to Main Menu");
    System.out.print("Enter your choice: ");
  }


  /**
 * get input method.
 */

  public int getInput() {
    int choice = -1;
    try {
      choice = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Please enter a valid number.");
    }
    return choice;
  }

  public String getInputString(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim(); // Trim the input here
  }


  public void displayInvalidOptionMessage() {
    System.out.println("Invalid option. Please try again.");
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }


  /**
     * Prompts the user for the duration of the contract in days.
     */
  public int promptForContractDuration() {
    System.out.print("Enter contract duration in days: ");
    while (!scanner.hasNextInt()) {
      System.out.println("Please enter a valid number.");
      scanner.next(); // to consume the incorrect input
      System.out.print("Enter contract duration in days: ");
    }
    int duration = scanner.nextInt();
    scanner.nextLine(); // Clear the newline left in the buffer
    return duration;
  }

  public int promptForStartDayOffset() {
    System.out.print("Enter start Day: ");
    return Integer.parseInt(scanner.nextLine());
  }

  public int promptForEndDayOffset() {
    System.out.print("Enter end day: ");
    return Integer.parseInt(scanner.nextLine());
  }
}

  


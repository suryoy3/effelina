package controller;

import java.util.List;
import java.util.Optional;
import model.Contract;
import model.Item;
import model.Member;
import model.MemberIdGenerator;
import model.MemberListManager;
import model.Time;
import view.Menu;


/**
 * Represent the user interface in the console.
 */
public class ControllerUi {
  private Menu view;
  private MemberListManager memberListManager;
  private MemberIdGenerator memberIdGenerator;
  private Time time;
  private boolean isRunning;

  /**
   * Manages the member data.
   */
  public ControllerUi() {
    this.view = new Menu();
    this.memberListManager = new MemberListManager();
    this.memberIdGenerator = new MemberIdGenerator();
    this.time = Time.getInstance();
    this.isRunning = true;
  }

  private void initializePredefinedData() {
    // Create Members
    Member member1 = new Member("M1", "M1@gmail.com", "12345", "H1");
    Member member2 = new Member("M2", "M2@gmail.com", "54321", "H2");
    Member member3 = new Member("M3", "M3@gmail.com", "98765", "H3");

    // Add Members to MemberListManager
    memberListManager.addMember(member1);
    memberListManager.addMember(member2);
    memberListManager.addMember(member3);

    // Create Items and Add to MemberListManager
    memberListManager.addItem(member1, "Vehicle", "Audi", "Fast car", 50);
    memberListManager.addItem(member1, "Sport", "Football", "Round ball", 10);
    memberListManager.addItem(member2, "Tool", "Spike", "New spike", 20);
   

    // Assign Credits to Members
    member1.setCredits(500);
    member2.setCredits(100);
    member3.setCredits(100);

  }

  /**
   * A flag indicating the running state of the application's main loop.
   */
  public void run() {
    initializePredefinedData();
    while (isRunning) {
      view.displayMainMenu();
      int choice = view.getInput();
      handleMainMenuOption(choice);
    }
  }

  private void handleMainMenuOption(int choice) {
    switch (choice) {
      case 1:
        handleMemberMenu();
        break;
      case 2:
        handleItemMenu();
        break;
      case 3:
        handleContractMenu();
        break;
      case 4:
        isRunning = false;
        break;
      default:
        view.showInvalidOptionMessage();
    }  // Added closing brace
  }

  private void handleMemberMenu() {
    view.displayMemberMenu();  // Fixed typo
    int choice = view.getInput();
    switch (choice) {
      case 1:
        addMember();
        break;
      case 2:
        deleteMemberId();
        break;
      case 3:
        changeMemberInformation();
        break;
      case 4:
        getFullInformation();
        break;
      case 5:
        listAllMembersSimple();
        break; 
      case 6:
        listAllMembersVerbose();
        break;
      case 7:
        break;
      default:
        view.showInvalidOptionMessage();
        break;
    }
  }

  private void addMember() {
    String name = view.promptForName();
    String email = view.promptForEmail();
    String mophnumber = view.promptForMoPhNumber();
    String memberId = memberIdGenerator.generateRandomId(6);

    // Check for uniqueness of email and phone number
    if (memberListManager.doesEmailExist(email)) {
      view.displayEmailExistsError();  // Use Menu's method to display error
      return;  // Exit the method
    }

    if (memberListManager.doesPhoneNumberExist(mophnumber)) {
      view.displayPhoneNumberExistsError();  // Use Menu's method to display error
      return;  // Exit the method
    }

    Member member = new Member(name, email, mophnumber, memberId);
    memberListManager.addMember(member);
    view.showSuccessMessage("Operation successful! New member added with ID: " + memberId);
  }


  /**
   * delete member.
   */
  public void deleteMemberId() {
    String memberId = view.getInputString("Enter Member ID to delete: ").trim();

    boolean isDeleted = memberListManager.deleteMemberById(memberId);
    if (isDeleted) {
      view.displayMessage("Member with ID " + memberId + " has been deleted.");
    } else {
      view.displayMessage("Member with ID " + memberId + " not found.");
    }
  }


  private void changeMemberInformation() {
    String memberId = view.getInputString("Enter Member ID to change information: ");

    Optional<Member> memberOpt = memberListManager.findMemberById(memberId);
    if (!memberOpt.isPresent()) {
      view.showMemberNotFoundMessage();
      return;
    }

    Member member = memberOpt.get();

    // Prompt for new details
    String newName = view.promptForNewName();
    String newEmail = view.promptForNewEmail();
    String newPhoneNumber = view.promptForNewPhoneNumber();

    // Update member information
    member.setName(newName);
    member.setEmail(newEmail);
    member.setMoPhNumber(newPhoneNumber);

    // Show success message
    view.showSuccessMessage("Member information updated successfully.");
  }
  


  private void getFullInformation() {
    String memberId = view.getInputString("Enter member's ID for detailed information: ");

    Optional<Member> optMember = memberListManager.findMemberById(memberId);

    if (optMember.isPresent()) {
      Member member = optMember.get();
      view.displayMemberInfo(member);

      // Display each item's information
      List<Item> items = member.getOwnedItems(); // Assuming getItems() returns a list of items
      if (items.isEmpty()) {
        view.displayMessage("This member has no items.");
      } else {
        for (Item item : items) {
          // Format and display item information, including the ItemID
          String itemInfo = String.format("Item ID: %s, Name: %s, Description: %s, Cost: %d",
                                                item.getItemId(), item.getName(), 
                                                item.getDescription(), item.getCostPerDay());
          view.displayMessage(itemInfo);
        }
      }
    } else {
      view.showMemberNotFoundMessage();
    }
  }

  private void listAllMembersSimple() {
    List<Member> allMembers = memberListManager.getAllMembers();
    if (allMembers.isEmpty()) {
      view.displayMessage("No members have been created yet.");
      return;
    }

    for (Member member : allMembers) {
      view.displaySimpleMemberInfo(member);
    }
  }

  private void listAllMembersVerbose() {
    List<Member> allMembers = memberListManager.getAllMembers();
    if (allMembers.isEmpty()) {
      view.displayMessage("No members have been created yet.");
      return;
    }

    for (Member member : allMembers) {
      view.displayMemberInfoVerbose(member);

      List<Item> ownedItems = member.getOwnedItems();
      if (ownedItems.isEmpty()) {
        view.displayMessage("No items owned by this member.");
      } else {
        for (Item item : ownedItems) {
          view.displayItemDetails(item);

          List<Contract> contracts = item.getContracts();
          if (contracts.isEmpty()) {
            view.displayMessage("No contracts for this item.");
          } else {
            for (Contract contract : contracts) {
              view.displayContractDetailsVerbose(contract);
            }
          }
        }
      }
    }
  }


  private void handleItemMenu() {
    view.displayItemMenu();
    int choice = view.getInput();
    switch (choice) {
      case 1:
        createNewItem();
        break;
      case 2:
        deleteItem();
        break;
      case 3:
        changeItemInformation();
        break;
      case 4:
        viewItemAndContracts();
        break;
      case 5:
        return;
      default:
        view.showInvalidOptionMessage();
    }
  }

  private void createNewItem() {
    String memberId = view.getInputString("Enter member's ID to assign item to: ");
    Optional<Member> optMember = memberListManager.findMemberById(memberId);
    if (optMember.isPresent()) {
      Member member = optMember.get();
      String category = view.promptForItemCategory();
      String name = view.promptForItemName(); // Ensure this method asks for item's name
      String description = view.promptForItemDescription();
      int costPerDay = view.promptForItemCostPerDay();

  
      Item newItem = memberListManager.addItem(member, category, name, description, costPerDay);

      if (newItem != null) {
        // Display the unique ID of the newly created item
        view.showSuccessMessage("Item successfully added with ID: " + newItem.getItemId());
      } else {
        view.showErrorMessage("Failed to create item.");
      }
    }
  }

  private void deleteItem() {
    String itemId = view.promptForItemId(); // Ask for the item ID directly

    boolean isItemDeleted = memberListManager.deleteItem(itemId);
    if (isItemDeleted) {
      view.showSuccessMessage("Item successfully deleted.");
    } else {
      view.showItemNotFoundMessage();
    }
  }


  private void changeItemInformation() {
    String itemId = view.promptForItemId(); // Prompt the user for the item ID

    Optional<Item> itemToChange = memberListManager.getItemById(itemId);
    if (itemToChange.isPresent()) {
      // Prompt for new details and update the item
      String newCategory = view.promptForNewCategory();
      String newName = view.promptForNewName();
      String newDescription = view.promptForNewDescription();
      int newCostPerDay = view.promptForNewCostPerDay();

      memberListManager.updateItem(itemId, newCategory, newName, newDescription, newCostPerDay);
      view.showSuccessMessage("Item information updated successfully.");
    } else {
      view.showItemNotFoundMessage();
    }
  }

  
    
  private void viewItemAndContracts() {
    String itemId = view.promptForItemId(); // Prompt the user for the item ID

    Optional<Item> itemOpt = memberListManager.getItemById(itemId);
    if (!itemOpt.isPresent()) {
      view.showItemNotFoundMessage();
      return;
    }

    Item item = itemOpt.get();
    int currentDay = time.getCurrentDay(); // Get the current day

    // Display item information
    view.displayItemInfo(item, currentDay);  // Adjusted to pass currentDay

    // Check and display contract details if there are any
    List<Contract> contracts = item.getContracts();
    if (contracts.isEmpty()) {
      view.showNoContractsMessage();
    } else {
      for (Contract contract : contracts) {
        if (isContractActive(contract, currentDay)) {
          Member lender = memberListManager.findMemberById(contract.getLenderId()).orElse(null);
          Member borrower = memberListManager.findMemberById(contract.getBorrowerId()).orElse(null);
          if (lender != null && borrower != null) {
            view.displayContractInfo(contract, lender, borrower);
          }
        }
      }
    }
  }

  private boolean isContractActive(Contract contract, int currentDay) {
    return currentDay >= contract.getStartDate() && currentDay <= contract.getEndDate();
  }

    


  private void handleContractMenu() {
    view.displayContractMenu();
    int choice = view.getInput();
    switch (choice) {
      case 1:
        createNewContract();
        break;
      case 2:
        return;
      default:
        view.showInvalidOptionMessage();
        break;
    }
  }

  private void createNewContract() {
    String lenderId = view.getInputString("Enter lender's ID: ");
    String borrowerId = view.getInputString("Enter borrower's ID: ");
    String itemId = view.getInputString("Enter item's ID: ");

    int currentDay = time.getCurrentDay(); // Use Time class to get the current day

    int startDayOffset = view.promptForStartDayOffset(); // Get the offset for the start day
    int endDayOffset = view.promptForEndDayOffset(); // Get the offset for the end day

    int startDay = currentDay + startDayOffset; // Calculate the actual start day
    int endDay = currentDay + endDayOffset; // Calculate the actual end day

    if (endDay < startDay) {
      view.showErrorMessage("End day cannot be before start day.");
      return;
    }

    Optional<Member> lenderOpt = memberListManager.findMemberById(lenderId);
    Optional<Member> borrowerOpt = memberListManager.findMemberById(borrowerId);
    Optional<Item> itemOpt = memberListManager.getItemById(itemId);

    if (!lenderOpt.isPresent() || !borrowerOpt.isPresent() || !itemOpt.isPresent()) {
      view.showErrorMessage("Invalid lender, borrower, or item ID.");
      return;
    }

    Member lender = lenderOpt.get();
    Member borrower = borrowerOpt.get();
    Item item = itemOpt.get();

    int duration = endDay - startDay + 1;

    if (!borrower.hasEnoughCredits(item.getCostPerDay(), duration)
        || !item.isAvailable(startDay, duration)) {
      view.showErrorMessage("Contract cannot be created." 
          + "Insufficient credits or item not available.");
      return;
    }

    Contract newContract = new Contract(startDay, endDay, item, lender, borrower);
    memberListManager.addContract(newContract);

    item.bookItem(startDay, duration);

    view.showSuccessMessage("Contract created successfully.");
  }
}
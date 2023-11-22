package model;

import java.util.Arrays;
import java.util.List;

/**
 * created categoryValidator class.
 */
public class CategoryValidator {
    
  private final List<String> validCategories  = Arrays.asList(
        "Tool", "Vehicle", "Game", "Toy", "Sport", "Other"
    );
    
  public boolean isValidCategory(String category) {
    return validCategories.stream()
    .anyMatch(validCat -> validCat.equalsIgnoreCase(category));
  }
}

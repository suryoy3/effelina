package model;

import java.util.Random;

/**
 * Generates random alphanumeric IDs using a mix of upper and lower case letters and digits.
 */
public class MemberIdGenerator {
  private final char[] alphanum = (
    "abcdefghijklmnopqrstuvwxyz"
    + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
    + "0123456789"
  ).toCharArray();
  private final Random random = new Random();

  /**
 * Generates a random alphanumeric string of specified length.
 *
 * @param length The length of the generated ID.
 * @return A random alphanumeric string.
 */
  public String generateRandomId(int length) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < length; i++) {
      result.append(alphanum[random.nextInt(alphanum.length)]);
    }
    return result.toString();
  }
}

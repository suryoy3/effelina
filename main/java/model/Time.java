package model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Time class.
 */
public class Time {
  private static Time instance = null;
  private int currentDay;

  // Logger instance for the Time class
  private static final Logger logger = Logger.getLogger(Time.class.getName());

  // Private constructor for singleton pattern
  public Time() {
    this.currentDay = 0;
  }

  /**
   * Public method to get the single instance of Time.
   */
  public static Time getInstance() {
    if (instance == null) {
      instance = new Time();
    }
    return instance;
  }

  /**
   *  Method to get the current day.
   */
  public int getCurrentDay() {
    return currentDay;
  }

  /**
   * Method to advance the day and log this action.
   */

  public void advanceDay() {
    currentDay++;
    logger.log(Level.INFO, "Day advanced to: " + currentDay);
  }


}

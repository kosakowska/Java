//Calculate average

import java.util.*;
public class CalculateAverage {
  public static double find_average(int[] array){
    return Arrays.stream(array).average().orElse(Double.NaN);
  }
}

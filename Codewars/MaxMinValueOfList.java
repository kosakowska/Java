//Find Maximum and Minimum Values of a List

import java.util.*;

public class MaxMinValueOfList {

  public int min(int[] list) {
    Arrays.sort(list);
    return list[0];
  }
  
  public int max(int[] list) {
    Arrays.sort(list);
    return list[list.length-1];
  }
}

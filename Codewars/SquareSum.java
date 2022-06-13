//Square(n) Sum

import java.util.*;
public class SquareSum
 {
  public static int squareSum(int[] n)
  { 
   return Arrays.stream(n).map(x -> x*x).sum();
  }
 }

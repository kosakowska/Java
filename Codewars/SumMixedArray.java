//Sum Mixed Array

import java.util.*;

public class MixedSum {

  public int sum(List<?> mixed) {
    int sum=0;
    for(Object s:mixed)
      {
      sum+=Integer.parseInt(s.toString());
    }
    
    return sum;
  }
}

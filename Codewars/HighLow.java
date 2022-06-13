//Highest and Lowest

import java.util.*;
public class HighLow {
  public static String highAndLow(String numbers) {
    String[] temp=numbers.split(" ");
    String max=new String();
    String min=new String();
    max=temp[0];
    min=temp[0];
    for(int i=0;i<temp.length;i++)
      {
      if(Integer.parseInt(temp[i])>Integer.parseInt(max)) max=temp[i];
      if(Integer.parseInt(temp[i])<Integer.parseInt(min)) min=temp[i];
    }
    return max+" "+min;
    
  }
}

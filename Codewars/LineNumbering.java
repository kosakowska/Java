//Testing 1-2-3

import java.util.*;

public class LineNumbering {
    public static List<String> number(List<String> lines) {
        for(int i=0;i<lines.size();i++)
          {
          lines.set(i,i+1+": "+lines.get(i));
        }
      return lines;
    }
}

//A Needle in the Haystack

import org.apache.commons.lang3.ArrayUtils;
public class NeedleInHaystack {
  public static String findNeedle(Object[] haystack) {
    int idx=ArrayUtils.indexOf(haystack, "needle");
    return "found the needle at position "+idx;
  }
}

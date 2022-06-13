//All Star Code Challenge #18

public class AllStarCode {
  public static int strCount(String str, char letter) {
    int ans=0;
    for(char i:str.toCharArray())
      {
      if(i==letter) ans++;
    }
    return ans;
  }
}

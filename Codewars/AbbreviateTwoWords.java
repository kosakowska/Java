//Abbreviate a Two Word Name

public class AbbreviateTwoWords {

  public static String abbrevName(String name) {
    String ans=new String();
    ans=Character.toString(Character.toUpperCase(name.charAt(0)));
    ans+=".";
    ans+=Character.toString(Character.toUpperCase(name.charAt(name.indexOf(" ")+1)));
    return ans;
  }
}

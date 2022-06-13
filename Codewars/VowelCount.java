//Vowel Count

public class VowelCount {

  public static int getCount(String str) {
    int vowelsCount = 0;
    for(char i:str.toCharArray())
      {
      if(i=='a'||i=='e'||i=='i'||i=='o'||i=='u') vowelsCount++;
    }
    return vowelsCount;
  }

}

//Shortest Word

public class Word {
    public static int findShort(String s) {
      String [] tab = s.split(" ");
      int min=tab[0].length();
      for(int i=0;i<tab.length;i++)
        {
        if(tab[i].length()<min) min=tab[i].length();
      }
      return min;
    }
}

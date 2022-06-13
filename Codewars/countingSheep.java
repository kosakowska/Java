//If you can't sleep, just count sheep!!

class Kata {
  public static String countingSheep(int num) {
    String a=new String();
    for(int i=1;i<=num;i++)
      {
      a+=(i+" sheep...");
    }
    return a;
  }
}

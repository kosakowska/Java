//Beginner - Lost Without a Map

public class LostWithoutMap {
  public static int[] map(int[] arr) {
  int [] anArray = new int[arr.length];
  for(int i=0;i<arr.length;i++)
    {
    anArray[i]=(arr[i]*2);
  }
    return anArray;
  }
}

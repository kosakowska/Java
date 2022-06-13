//Beginner - Reduce but Grow

public class ReduceButGrow{

  public static int grow(int[] x){
    int ans=1;
    for(int i:x)
    {
    ans*=i;
  }
    return ans;
  
  }

}

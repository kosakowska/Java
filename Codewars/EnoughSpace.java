//Will there be enough space?

public class EnoughSpace {
  public static int enough(int cap, int on, int wait){
  return on+wait>cap ? on+wait-cap : 0;
  }
}

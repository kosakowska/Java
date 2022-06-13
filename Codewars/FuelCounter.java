//Will you make it?

public class FuelCounter {
  
  public static boolean zeroFuel(double distanceToPump, double mpg, double fuelLeft) {
    
    return mpg*fuelLeft>=distanceToPump ? true: false;
  }
  
}

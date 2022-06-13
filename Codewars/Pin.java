//Regex validate PIN code

public class Pin {

  public static boolean validatePin(String pin) {
  
    return (pin.length()==4||pin.length()==6)&&pin.matches("[0-9]+") ? true:false;
  }

}

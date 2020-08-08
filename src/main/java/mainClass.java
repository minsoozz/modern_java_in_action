import Chapter11.Person;

public class mainClass {

  public static void main(String args[]) {
  }

  public static String getCarInsureanceName(Person person) {
    return person.getCar().getInsurance().getName();
  }
}

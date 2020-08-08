import Chapter11.Car;
import Chapter11.Insurance;
import Chapter11.Person;
import java.util.Optional;
import org.junit.Test;

public class chapter11 {

  Insurance insurance = new Insurance();
  Person person = new Person();
  Car car = new Car();

  @Test
  public void testMain() {
    Person person = new Person();
    String str = getCarUnsuranceName(person);
    System.out.println("str = " + str);
  }

  /* 보수적인 자세로 NPE 줄이기 */
  public String getCarUnsuranceName(Person person) {
    if (person != null) {
      Car car = person.getCar();
      if (car != null) {
        Insurance insurance = car.getInsurance();
        if (insurance != null) {
          return insurance.getName();
        }
      }
    }
    return "Unknown";
  }

  /*
   * 에러의 근원이다.
   * 코드를 어지럽힌다.
   * 아무 의마가 없다.
   * 자바 철학에 위배된다.
   * 형식 시스템에 구멍을 만든다.
   *
   * */

  /* Optional 적용 패턴 */
  @Test
  public void optionalTestMain() {
    Car car = new Car();

    Optional<Car> optionalCar = Optional.empty(); /* 빈 Optional 객체를 얻을 수 있다 */

    Optional<Car> optionalCar1 = Optional.of(car); /* null 이 아닌 값으로 Optional 만들기 */

    Optional<Car> optionalCar2 = Optional.ofNullable(car); /* null 값으로 Optional 만들기 */
  }

  /* 맵으로 Optional 의 값 추출하고 변환하기 */
  @Test
  public void mapTest() {
    Insurance insurance = new Insurance();

    String name = null;
    if (insurance != null) {
      name = insurance.getName();
    }

    Optional<Insurance> optionalInsurance = Optional
        .ofNullable(insurance); /* null 값으로 Optional 만들기 */
    Optional<String> optionalName = optionalInsurance.map(Insurance::getName);
    /* Optional 객체를 최대 요소의 개수가 한 개 이하인 데이터 컬렉션으로 생각할 수 있다 */
  }

  @Test
  public void errorMapTest() {
    Optional<Person> optionalPerson = Optional.of(person);
    Optional<String> name = optionalPerson.map(Person::getCar)
        .map(Car::getInsurance)
        .map(Insurance::getName);

    /* 여기서 flatMap 복습
     *  함수를 인수로 받아서 다른 스트림을 반환하는 메서드 */
  }

  @Test
  public void successMapTest() {
    person.setOptinalCar(Optional.ofNullable(car));
    car.setOptionalInsurance(Optional.ofNullable(insurance));
    insurance.setName("insu");
//    person.setOptinalCar(null);

    Optional<Person> optionalPerson = Optional.ofNullable(person);
    String str = getCarInsuranceName(optionalPerson);
    System.out.println("str = " + str);
  }

  public String getCarInsuranceName(Optional<Person> person) {
    return person.flatMap(Person::getOptinalCar)
        .flatMap(Car::getOptionalInsurance)
        .map(Insurance::getName)
        .orElse("Unknown");/*
    Optional<Car> optionalCar = person.flatMap(Person::getOptinalCar);
    Optional<Insurance> optionalInsurance = optionalCar.flatMap(Car::getOptionalInsurance);
    Optional<String> optional = optionalInsurance.map(Insurance::getName);
    return optional.orElse("un");*/
  }

  @Test
  public void filterTest(){
    Optional<Person> person = null;
    String str = getCarInsuranceName(person,11);
    System.out.println("str = " + str);
  }

  /* 필터로 특정값 거르기 */
  public String getCarInsuranceName(Optional<Person> person, int minAge){
    return person.filter(p -> p.getAge() >= minAge)
        .flatMap(Person::getOptinalCar)
        .flatMap(Car::getOptionalInsurance)
        .map(Insurance::getName)
        .orElse("Unnown");
  }

  @Test
  public void stringToIntTest(){
    stringToInt("str");
  }

  public static Optional<Integer> stringToInt(String s) {
    try{
      return Optional.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      return Optional.empty(); // 예외 발생시 빈 Optional 반환
    }
  }
}

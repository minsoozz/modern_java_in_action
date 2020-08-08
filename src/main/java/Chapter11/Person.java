package Chapter11;

import java.util.Optional;

public class Person {

  private Car car;
  private Optional<Car> optinalCar;

  private int age;

  public Car getCar() {
    return car;
  }

  public void setOptinalCar(Optional<Car> optCar) {
    this.optinalCar = optCar;
  }

  public Optional<Car> getOptinalCar() {
    return optinalCar;
  }

  public int getAge() {
    return age;
  }
}

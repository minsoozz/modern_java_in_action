package Chapter11;

import java.util.Optional;

public class Car {

  private Insurance insurance;
  private Optional<Insurance> optionalInsurance;

  public Insurance getInsurance() {
    return insurance;
  }

  public Optional<Insurance> getOptionalInsurance() {
    return optionalInsurance;
  }

  public void setOptionalInsurance(Optional<Insurance> optInsurance) {
    this.optionalInsurance = optInsurance;
  }

}

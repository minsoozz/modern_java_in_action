package Chapter11;

public class Insurance {
  private String name;
  private String optionalName;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
    this.optionalName = name;
  }
}

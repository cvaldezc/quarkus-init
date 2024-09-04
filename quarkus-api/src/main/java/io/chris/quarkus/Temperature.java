package io.chris.quarkus;

public class Temperature {

  private String city;
  private int min;
  private int max;

  public Temperature() {
    
  }

  public Temperature(String city, int min, int max) {
    this.city = city;
    this.min = min;
    this.max = max;
  }

  public String getCity() {
    return this.city;
  }

  public int getMin() {
    return this.min;
  }

  public int getMax() {
    return this.max;
  } 
}

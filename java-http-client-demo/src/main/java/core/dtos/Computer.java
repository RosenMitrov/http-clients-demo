package core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Computer {
    private String id;
    private String brand;
    private String model;
    private int year;
    private double price;

    public Computer() {
    }

    public Computer(String id,
                    String brand,
                    String model,
                    int year,
                    double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Computer setId(String id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Computer setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Computer setModel(String model) {
        this.model = model;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Computer setYear(int year) {
        this.year = year;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Computer setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}

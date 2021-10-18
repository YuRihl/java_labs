import java.awt.print.Printable;

public class Car {
    private String Model;
    private Long Price;
    private Long MaxSpeed;

    @Override
    public String toString() {
        return "Car{" +
                "Model='" + Model + '\'' +
                ", Price=" + Price +
                ", MaxSpeed=" + MaxSpeed +
                '}';
    }

    public Car(String model, Long price, Long maxSpeed) {
        Model = model;
        Price = price;
        MaxSpeed = maxSpeed;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public Long getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(Long maxSpeed) {
        MaxSpeed = maxSpeed;
    }
}

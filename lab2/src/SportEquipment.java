public class SportEquipment {
    public SportEquipment(String size, String material, String brand, double price, int cottonPercentage) {
        this.size = size;
        this.material = material;
        this.brand = brand;
        this.price = price;
        this.cottonPercentage = cottonPercentage;
    }

    private String size;
    private String material;
    private String brand;
    private double price;
    private int cottonPercentage;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCottonPercentage() {
        return cottonPercentage;
    }

    public void setCottonPercentage(int cottonPercentage) {
        this.cottonPercentage = cottonPercentage;
    }
}

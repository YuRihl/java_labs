public class Sneakers extends SportEquipment{
    private int soleHeight;

    public Sneakers(String size, String material, String brand, double price, int cottonPercentage, int soleHeight) {
        super(size, material, brand, price, cottonPercentage);
        this.soleHeight = soleHeight;
    }
}

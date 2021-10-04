public class Shorts extends SportEquipment{
    private String shortsType;
    private String shorts_Print;

    public Shorts(String size, String material, String brand, double price, int cottonPercentage, String shortsType, String shorts_Print) {
        super(size, material, brand, price, cottonPercentage);
        this.shortsType = shortsType;
        this.shorts_Print = shorts_Print;
    }
}

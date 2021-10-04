public class TrackSuit extends SportEquipment{
    private String suitType;

    public TrackSuit(String size, String material, String brand, double price, int cottonPercentage, String suitType) {
        super(size, material, brand, price, cottonPercentage);
        this.suitType = suitType;
    }
}

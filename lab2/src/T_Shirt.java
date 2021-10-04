public class T_Shirt extends SportEquipment{
    private String t_shirtType;
    private String t_shirtPrint;

    public T_Shirt(String size, String material, String brand, double price, int cottonPercentage, String t_shirtType, String t_shirtPrint) {
        super(size, material, brand, price, cottonPercentage);
        this.t_shirtType = t_shirtType;
        this.t_shirtPrint = t_shirtPrint;
    }
}

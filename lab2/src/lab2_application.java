import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class lab2_application {
    public static void main(String[] args) {
        ArrayList<SportEquipment> sportEquipmentArray = new ArrayList<>();

        SportEquipment first = new Shorts("L", "Cotton", "Nike", 10.3, 60, "Football", "someImage.png");
        SportEquipment second = new Sneakers("XL", "Cotton", "Puma", 40.5, 21, 1);
        SportEquipment third = new TrackSuit("L", "Cotton", "New Balance", 25, 76, "Casual");
        SportEquipment fourth = new T_Shirt("M", "Cotton", "Adidas", 6.7, 70, "Football", "someImage.png");
        SportEquipment fifth = new Sneakers("L", "Cotton", "Nike", 59, 20, 2);


        sportEquipmentArray.add(first);
        sportEquipmentArray.add(second);
        sportEquipmentArray.add(third);
        sportEquipmentArray.add(fourth);
        sportEquipmentArray.add(fifth);

        //Anonymous comparator
        Comparator<SportEquipment> cottonPercentageComparator = new Comparator<SportEquipment>() {
            @Override
            public int compare(SportEquipment first, SportEquipment second) {
                return first.getCottonPercentage() - second.getCottonPercentage();
            }
        };

        // Lambda function initialized in Comparator interface
        Comparator<SportEquipment> lambdaCottonPercentageDescendingComparator = (SportEquipment firstEquipment, SportEquipment secondEquipment) -> {
            return second.getCottonPercentage() - first.getCottonPercentage();
        };

        Comparator<SportEquipment> priceComparator = new SportEquipmentManager.priceComparator();
        Collections.sort(sportEquipmentArray, priceComparator);
        for(SportEquipment element : sportEquipmentArray){
            System.out.println(element.getPrice());
        }
        System.out.println();

        SportEquipmentManager manager = new SportEquipmentManager(sportEquipmentArray);
        Comparator<SportEquipment> priceDescendingComparator = manager.new priceDescendingComparator();
        Collections.sort(sportEquipmentArray, priceDescendingComparator);
        for(SportEquipment element : sportEquipmentArray){
            System.out.println(element.getPrice());
        }
        System.out.println();

        Collections.sort(sportEquipmentArray, cottonPercentageComparator);
        for(SportEquipment element : sportEquipmentArray){
            System.out.println(element.getCottonPercentage());
        }
        System.out.println();

        Collections.sort(sportEquipmentArray, lambdaCottonPercentageDescendingComparator);
        for(SportEquipment element : sportEquipmentArray){
            System.out.println(element.getCottonPercentage());
        }
        System.out.println();

        ArrayList<SportEquipment> foundByBrandList = new ArrayList<SportEquipment>();
        foundByBrandList = manager.findByBrand("Nike");
        for(SportEquipment element : foundByBrandList){
            System.out.println(element.getBrand() + " " + element.getPrice());
        }
    }
}
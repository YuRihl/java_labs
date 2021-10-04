import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class SportEquipmentManager {

    public SportEquipmentManager(ArrayList<SportEquipment> sportEquipment) {
        this.sportEquipment = sportEquipment;
    }

    private ArrayList<SportEquipment> sportEquipment;

    // static inner comparator
    public static class priceComparator implements Comparator<SportEquipment> {
        @Override
        public int compare(SportEquipment first, SportEquipment second) {
            return (int) (first.getPrice() - second.getPrice());
        }
    }

    // inner comparator
    public class priceDescendingComparator implements Comparator<SportEquipment>{
        @Override
        public int compare(SportEquipment first, SportEquipment second){
            return (int) (second.getPrice() - first.getPrice());
        }
    }

    public ArrayList<SportEquipment> findByBrand(String brand){
        ArrayList<SportEquipment> finalSportEquipment = new ArrayList<SportEquipment>();
        for(SportEquipment element : sportEquipment){
            if(element.getBrand()==brand){
                finalSportEquipment.add(element);
            }
        }
        return finalSportEquipment;
    }

    public void sortSportEquipmentList(boolean isDescending){
        if(isDescending){
            Comparator<SportEquipment> priceDescCompare = new priceDescendingComparator();
            Collections.sort(sportEquipment, priceDescCompare);
        }
        else{
            Comparator<SportEquipment> priceCompare = new priceComparator();
            Collections.sort(sportEquipment, priceCompare);
        }
    }
}

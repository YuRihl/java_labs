import java.io.FileReader;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class lab3 {
    public static ArrayList<Car> carCommonList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        HashMap<Long, ArrayList<Car>> carMap = new HashMap<>();

        JSONArray carSetArray = (JSONArray) new JSONParser().parse(new FileReader("src/labdata.json"));

        Iterator<ArrayList<Car>> carSetIterator = carSetArray.iterator();

        while(carSetIterator.hasNext()){
            JSONArray carSet = (JSONArray) carSetIterator.next();
            Iterator carIterator = carSet.iterator();
            ArrayList<Car> instanceCarList = new ArrayList<>();         //creating temp set of parsed cars

            while(carIterator.hasNext()){
                JSONObject car = (JSONObject) carIterator.next();
                String carModel = (String) car.get("model");
                Long carPrice = (Long) car.get("price");
                Long carMaxSpeed = (Long) car.get("maxSpeed");
                instanceCarList.add(new Car(carModel, carPrice, carMaxSpeed));
            }
            carMap.put(instanceCarList.get(0).getMaxSpeed(), instanceCarList);
        }

        System.out.print("Enter the number of car printed: ");
        Scanner userInput = new Scanner(System.in);
        int numOfCarsPrinted = userInput.nextInt(); userInput.nextLine();

        System.out.println("Car list after filter: ");
        for(Map.Entry<Long, ArrayList<Car>> element: carMap.entrySet()){
            for(int i=0; i < numOfCarsPrinted; i++){
                System.out.println(element.getValue().get(i).toString());
            }
        }

        System.out.print("Enter the number of cars deleted: ");
        int numOfCarsDeleted = userInput.nextInt(); userInput.nextLine();

        System.out.println("Enter the car models you want to delete: ");
        HashSet<String> deletedCars = new HashSet<>();
        while(userInput.hasNextLine() && numOfCarsDeleted > 0){
            deletedCars.add(userInput.nextLine());
            numOfCarsDeleted--;
        }

        Iterator<ArrayList<Car>> carArrayIterator = carMap.values().iterator();
        while(carArrayIterator.hasNext()){
            Iterator<Car> carsIterator = carArrayIterator.next().iterator();
            while(carsIterator.hasNext()){
                Car currentCar = carsIterator.next();
                for(String carModel: deletedCars){
                    if(currentCar.getModel().equals(carModel)){
                        carsIterator.remove();
                    }
                }
            }
        }


        System.out.println("Car list after some cars deleted: \n" + carMap);

        System.out.println("Car list after reading two files and sorting common list: ");
        commonCollectionTask("src/labdata_1.json", "src/labdata_2.json");
        System.out.println(carCommonList);

        System.out.print("Enter the minimal and maximal price: ");
        int minPrice = userInput.nextInt();
        int maxPrice = userInput.nextInt();
        System.out.print("The number of cars int selected range: " + findCarsByPrice(carCommonList, minPrice, maxPrice));

        userInput.close();
        }

        public static void commonCollectionTask(String filePath1, String filePath2) throws Exception{
            JSONArray carSet1 = (JSONArray) new JSONParser().parse(new FileReader(filePath1));
            JSONArray carSet2 = (JSONArray) new JSONParser().parse(new FileReader(filePath2));

            Iterator<JSONObject> carIterator1 = carSet1.iterator();
            Iterator<JSONObject> carIterator2 = carSet2.iterator();

            while(carIterator1.hasNext()){
                JSONObject carInstance1 = carIterator1.next();
                String carModel1 = (String) carInstance1.get("model");
                Long carPrice1 = (Long) carInstance1.get("price");
                Long carMaxSpeed1 = (Long) carInstance1.get("maxSpeed");

                carCommonList.add(new Car(carModel1, carPrice1, carMaxSpeed1));
            }

            while(carIterator2.hasNext()){
                JSONObject carInstance2 = carIterator2.next();
                String carModel2 = (String) carInstance2.get("model");
                Long carPrice2 = (Long) carInstance2.get("price");
                Long carMaxSpeed2 = (Long) carInstance2.get("maxSpeed");

                carCommonList.add(new Car(carModel2, carPrice2, carMaxSpeed2));
            }

            Comparator<Car> carModelComparator = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return o2.getModel().compareTo(o1.getModel());
                }
            };
            carCommonList.sort(carModelComparator);
        }

        public static int findCarsByPrice(ArrayList<Car> carList, int min, int max){
            Iterator<Car> carIterator = carList.iterator();
            int carsInRange = 0;
            while(carIterator.hasNext()){
                Long currentCarPrice = carIterator.next().getPrice();
                if(currentCarPrice > min && currentCarPrice < max){
                    carsInRange++;
                }
            }
            return carsInRange;
        }

}

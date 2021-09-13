import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class seriesCounter {
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        double sumOfSeries = 0; BigDecimal preciseSumOfSeries = new BigDecimal(0);
        int n = 0; double x = 0;

        System.out.print("Enter the N: ");
        if(userInput.hasNextInt()) {
            n = userInput.nextInt();
        }
        else{
            System.out.println("The number type you typed is incorrect, try again");
        }

        System.out.print("Enter the X: ");
        if(userInput.hasNextDouble()) {
            x = userInput.nextDouble();
        }
        else{
            System.out.println("The number type you typed is incorrect, try again");
        }

        if(n < 15){
            sumOfSeries = impreciseSeriesSum(n, x);
            System.out.println(sumOfSeries);
        }
        else{
            preciseSumOfSeries = preciseSeriesSum(n, x);
            System.out.println(preciseSumOfSeries);
        }
        userInput.close();

    }
    public static double impreciseSeriesSum(int n, double x){
        double seriesSum = 0;
        for(int i = 0; i <= n; i++){
            seriesSum += Math.pow(-1, i)*(Math.pow(x, 2*i)/getFactorial(2*i));
        }
        return seriesSum;
    }

    public static BigDecimal preciseSeriesSum(int n, double x){
        BigDecimal seriesSum = new BigDecimal("0.0");
        for(int i = 0; i <= n; i++){
            seriesSum = seriesSum.add(BigDecimal.valueOf(Math.pow(-1, i)).multiply(BigDecimal.valueOf(x).pow(2*i).divide(BigDecimal.valueOf(getFactorial(2*i)), n, RoundingMode.HALF_UP)));
        }
        return seriesSum;
    }

    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}

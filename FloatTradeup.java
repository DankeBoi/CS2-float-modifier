import java.util.*;

/* Float Tradeup Calculator for CS:GO items
 * By DankeBoi
 * Last updated: October 19, 2022
 * 
 * Uses the float calculation formula to turn 10 float inputs into 1 output.
 * Takes the average float of the 10 items and compresses it into a specific float range of the output.
 * 
 * Documentation of the tradeup formula can be found in the link below.
 * https://blog.csgofloat.com/analysis-of-float-value-and-paint-seed-distribution-in-cs-go/
 * 
 */

public class FloatTradeup {

    static List<Double> floatList = new ArrayList<>();

    public static void main(String[] args) {
        
        GetParameters();

    }

    public static void GetParameters() {

        // Creates a scanner, asks for integer, and closes
        Scanner console = new Scanner(System.in);

        System.out.print("Type the minimum float value possible: ");
        double floatMin = console.nextDouble();

        System.out.print("Type the maximum float value possible: ");
        double floatMax = console.nextDouble();

        System.out.println("Type the float values for the tradeup contract (10 float values):");

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%02d: ", i);
            floatList.add(console.nextDouble());
        }

        console.close();
        floatAverage(floatMin,floatMax);
        
    }

    public static void floatAverage(double floatMin, double floatMax) {

        double totalFloat = 0.0;

        for (int i = 1; i <= 10; i++) {
            totalFloat += floatList.get(i-1);
        }
        
        printFinal(floatModify(totalFloat / 10.0,floatMin,floatMax));

    }

    public static double floatModify(double unmodifiedFloat,double min_float, double max_float) { // 
        double modifiedfloat = unmodifiedFloat * (max_float - min_float) + min_float;
        return modifiedfloat;
    }

    public static void printFinal(double final_float) {
        System.out.println("Float Value: " + final_float);
    }

}

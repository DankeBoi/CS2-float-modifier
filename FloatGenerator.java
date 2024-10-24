import java.util.*;

/* Float Generator for CS:GO items
 * By DankeBoi
 * Last updated: October 19, 2022
 * 
 * Original float generation algorithm made by Valve Corporation, recreated from scratch
 * Feel free to modify the code to be able to harvest the floats into a graph.
 * 
 * Generates floats for a specific float range for any item.
 * Displays stats in the end to show probabilities for a specific float range.
 * 
 * Documentation of values such as probability and missing float gaps can be found in the link below.
 * https://blog.csgofloat.com/analysis-of-float-value-and-paint-seed-distribution-in-cs-go/
 * 
 */

public class FloatGenerator {

    static List<String> wearList = new ArrayList<>();
    static int FNCount;
    static int MWCount;
    static int FTCount;
    static int WWCount;
    static int BSCount;
    static int itemCount;

    public static void main(String[] args) {
        
        wearList.add("Factory New");
        wearList.add("Minimal Wear");
        wearList.add("Field-Tested");
        wearList.add("Well-Worn");
        wearList.add("Battle-Scarred");
        GetParameters();
        printStats();

    }
    public static void GetParameters() {

        // Creates a scanner, asks for integer, and closes
        Scanner console = new Scanner(System.in);

        System.out.print("Type how many float values you want to generate: ");
        itemCount = console.nextInt();

        System.out.print("Type the minimum float value possible: ");
        double floatMin = console.nextDouble();

        System.out.print("Type the maximum float value possible: ");
        double floatMax = console.nextDouble();

        console.close();
        floatGenerate(itemCount,floatMin,floatMax);
    }
    public static void floatGenerate(int times, double floatMin, double floatMax) {

        Random randomNum = new Random();
        int wear_roll;
        double min;
        double max;
        String wearName;

        for (int i = 1; i <= times; i++) {

            wear_roll = randomNum.nextInt(100); // 1 to 100 generator basically, minus 1

            if (wear_roll < 3) { // FN is 3% chance
                min = floatModify(0.0,floatMin,floatMax);;
                max = floatModify(0.07,floatMin,floatMax);;
            } else if (wear_roll < 27) { // MW is 24% chance 
                min = floatModify(0.08,floatMin,floatMax);;
                max = floatModify(0.15,floatMin,floatMax);
            } else if (wear_roll < 60) { // FT is 33% chance
                min = floatModify(0.16,floatMin,floatMax);
                max = floatModify(0.38,floatMin,floatMax);
            } else if (wear_roll < 84) { // WW is 24% chance
                min = floatModify(0.39,floatMin,floatMax);
                max = floatModify(0.45,floatMin,floatMax);
            } else { // wear_roll < 100  // BS is 16% chance
                min = floatModify(0.46,floatMin,floatMax);
                max = floatModify(1.0,floatMin,floatMax);
            }

            double generatedFloat = Math.random();
            double completeFloat = floatModify(generatedFloat,min,max);

            if (completeFloat < 0.07) {
                wearName = wearList.get(0);
                FNCount++;
            } else if (completeFloat < 0.15) { 
                wearName = wearList.get(1);
                MWCount++;
            } else if (completeFloat < 0.38) {
                wearName = wearList.get(2);
                FTCount++;
            } else if (completeFloat < 0.45) {
                wearName = wearList.get(3);
                WWCount++;
            } else {
                wearName = wearList.get(4);
                BSCount++;
            }

            printFinal(wearName,completeFloat);
        }
    }
    public static double floatModify(double unmodifiedFloat,double min_float, double max_float) { // 
        double modifiedfloat = unmodifiedFloat * (max_float - min_float) + min_float;
        return modifiedfloat;
    }
    public static void printFinal(String wearName, double final_float) {
        System.out.println("Float Value: " + final_float + " (" + wearName + ")");
    }
    public static void printStats() {

        double FNChance = 100.0 * FNCount / itemCount;
        double MWChance = 100.0 * MWCount / itemCount;
        double FTChance = 100.0 * FTCount / itemCount;
        double WWChance = 100.0 * WWCount / itemCount;
        double BSChance = 100.0 * BSCount / itemCount;

        System.out.println("\nFN: " + FNCount + " (" + FNChance + "%)");
        System.out.println("\nMW: " + MWCount + " (" + MWChance + "%)");
        System.out.println("\nFT: " + FTCount + " (" + FTChance + "%)");
        System.out.println("\nWW: " + WWCount + " (" + WWChance + "%)");
        System.out.println("\nBS: " + BSCount + " (" + BSChance + "%)");
    }
}

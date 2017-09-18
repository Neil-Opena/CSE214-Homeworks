import java.util.*;
public class ComplexityAnalysis {

    public static boolean sumPairs(int[] arr, int key) {
        ArrayList<Value> pairs = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == key) {
                    Value temp = new Value(arr[i], arr[j]);
                    pairs.add(temp);
                }
            }
        }
        if (pairs.size() > 0) {
            for (int i = 0; i < pairs.size(); i++) {
                System.out.println(pairs.get(i).showPairs());
            }
            return true;
        }
        return false;
    }

    public static boolean sumTriplets(int[] arr, int key) {
        ArrayList<Value> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[i] + arr[j] + arr[k] == key) {
                        Value temp = new Value(arr[i], arr[j], arr[k]);
                        triplets.add(temp);
                    }
                }
            }
        }
        if (triplets.size() > 0) {
            for (int i = 0; i < triplets.size(); i++) {
                System.out.println(triplets.get(i).showTriplets());
            }
            return true;
        }
        return false;
    }
}

class Value{
    private int num1;
    private int num2;
    private int num3;

    public Value(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    public Value(int num1, int num2, int num3){
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public String showPairs(){
        return(num1 + " + " + num2 + " = " + (num1 + num2));
    }

    public String showTriplets(){
        return(num1 + " + " + num2 + " + " + num3 + " = " + (num1 + num2 + num3));
    }
}


public class Test {

    public static void main(String[] args){

        //This class is used for testing purposes

        //ComplexityAnalysis (pairs, triplets)
        int[] arr = {11, 15, 6, 8, 9, 10};
        int key = 25;

        System.out.println("Pairs");
        System.out.println(ComplexityAnalysis.pairProgram(arr, key));
        System.out.println("--------------------------------------");
        System.out.println("Triplets");
        System.out.println(ComplexityAnalysis.tripletsProgram(arr, key));



    }
}

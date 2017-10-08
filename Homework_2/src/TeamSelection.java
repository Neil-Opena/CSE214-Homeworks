import java.io.*;
public class TeamSelection {
    public static void main(String[] args){
        //read input from file;
        File file = new File("in2.txt");
        if(!file.exists()){
            System.out.println("File does not exist. Please place a file named \"in2.txt\" in the " +
                    "same directory as the TeamSelection class, and try again.");
            System.exit(0);
        }
        System.out.println("File exists. Good job!");
    }

}

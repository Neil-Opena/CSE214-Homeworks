import java.util.*;
import java.io.*;
public class FriendList {
   public static void main(String[] args){
      try{
          File file = new File("in1.txt");
          Scanner input = new Scanner(file);

          int numTests = Integer.parseInt(input.nextLine());
          System.out.println(numTests);

          for(int i = 0; i < numTests; i++){
              FriendListTestCase case1 = new FriendListTestCase(input.nextLine(),input.nextLine(),input.nextLine());
              System.out.println(case1.getN() + " " + case1.getK());
              case1.printNames();
              case1.printMutualFriends();
          }
      }catch(FileNotFoundException e){
          System.out.println("File does not exist. Please place a file named \"in3.txt\" in the " +
                  "same directory as the src directory, and try again.");
      }catch(NullPointerException e){
          e.printStackTrace();
          System.out.println("Some error occurred...");
      }catch(Exception e){
          System.out.println("File is not formatted correctly. Please format it such that the first line " +
                  "contains the number of test cases. Next line consists of two space separated integers N and K. " +
                  "Next line consists of N strings. Next line " +
                  "consists of integers representing the number of mutual friends.");
      }
   }

}
class FriendListTestCase{
   private int N;
   private int K;
   private String[] names;
   private int[] mutualFriends;

   public FriendListTestCase(String friends, String namesString, String mutualFriendsString){
        String[] friendsInts = friends.split(" ");
        N = Integer.parseInt(friendsInts[0]);
        K = Integer.parseInt(friendsInts[1]);

        names = namesString.split(" ");

        String[] mutualFriendsList = mutualFriendsString.split(" ");
        mutualFriends = new int[mutualFriendsList.length];
        for(int i = 0; i < mutualFriendsList.length; i++){
            mutualFriends[i] = Integer.parseInt(mutualFriendsList[i]);
        }

   }

   public int getN(){
       return this.N;
   }

   public int getK(){
       return this.K;
   }

   public String[] getNames(){
       return this.names;
   }

   public int[] getMutualFriends(){
       return this.mutualFriends;
   }

   public void printNames(){
       for(String name : names){
           System.out.print(name + " ");
       }
       System.out.println();
   }

   public void printMutualFriends(){
       for(int mutuals : mutualFriends){
           System.out.print(mutuals + " ");
       }
       System.out.println();
   }
}
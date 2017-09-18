import java.util.*;
public class Test {

    public static void main(String[] args){

        //This class is used for testing purposes

        //ComplexityAnalysis
        int[] arr = {11, 15, 6, 8, 9, 10};
        int key = 25;


        System.out.println("Pairs: " + ComplexityAnalysis.sumPairs(arr, key));
        System.out.println("Triplets: " + ComplexityAnalysis.sumTriplets(arr, key));

        System.out.println("\n========================================\n");

        //CreditCard
        CreditCard a = new CreditCard("1234","Neil","Bank",300,500.00);
        System.out.println(a);
        System.out.println(a.payment(500,10));
        System.out.println(a.chargeIt(300));
        System.out.println("\n========================================\n");

        //Cards
        String[] cardsForPlayer1 = {"S4", "D8", "C4", "D3", "D5", "DJ", "S3", "D4", "DA", "SJ", "D7", "H10", "D6" };
        Card[] cards = new Card[13];
        for(int i = 0; i < cardsForPlayer1.length; i++){
            Card mCard = new Card(cardsForPlayer1[i]);
            cards[i] = mCard;
        }
        int id = 1;
        Player player = new Player(id);
        player.setCards(cards);
        player.printCards();
        player.sortCards();
        player.printCards();
        System.out.println("\n========================================\n");

        //Car
        Location myCurrentLocation = new Location(354, 538);
        Location myDestination = new Location(108, 25);

        Car myCar = new Car();
        GPS myGPS = new GPS();

        myGPS.setCurrentLocation(myCurrentLocation);
        myGPS.setDestinationLocation(myDestination);
        myCar.setGPS(myGPS);

        myCar.setCurrentSpeed(35.0);

        System.out.println("Distance: " + ((GPS)myCar.getGPS()).getCalculatedDistance());
        System.out.println("Arrival: " + ((GPS)myCar.getGPS()).getArrivalTime());
    }

}

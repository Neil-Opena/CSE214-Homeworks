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

        System.out.println("========================================");

        //CreditCard
        CreditCard a = new CreditCard("1234","Neil","Bank",300,500.00);
        System.out.println(a);

        System.out.println("========================================");

        //Cards
        cardSortingTest();
    }

    public static void cardSortingTest(){
        String[] cardsForPlayer1 = {
                                        "S4", "D8", "C4", "D3", "D5", "DJ", "S3", "D4", "DA", "SJ",
                                        "D7", "H10", "D6"
                                    };
        Card[] cards = new Card[13];
        for(int i = 0; i < cardsForPlayer1.length; i++){
           Card mCard = new Card(cardsForPlayer1[i]);
           cards[i] = mCard;
        }

        int id = 1;
        Player player = new Player(id);
        player.setCards(cards);
        player.printCards();

        System.out.println();

        player.sortCards();


        player.printCards();
    }


}

public class Player {

    private Card[] cards;
    private int id;

    public Player(int id){
        this.id = id;
    }

    public void setCards(Card[] cards){
        this.cards = cards;
    }

    public void printCards(){
        for(int i = 0; i < cards.length; i++) {
            System.out.print(cards[i] + " ");
        }
        System.out.println();
    }

    public void sortCards(){
        //insertion sort
        for(int i = 1; i < cards.length; i++){
            Card tempCard = cards[i];
            int j = i;
            while((j - 1 >= 0) && (cards[j - 1].getCArdValue() < tempCard.getCArdValue())){
                //shift
                cards[j] = cards[j - 1];
                cards[j - 1] = tempCard;
                j--;
            }
        }
    }

}



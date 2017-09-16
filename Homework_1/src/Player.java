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
        for(int i = 0; i < cards.length; i++){
            System.out.print(cards[i] + " ");
        }
    }

    public void sortCards(){
        //selection sort
        for(int i = 0; i < cards.length; i++){
            int max = cards[i].getCArdValue();
            int maxIndex =  i;
            for(int j = i + 1; j < cards.length; j++){
                if(cards[j].getCArdValue() > max){
                   max = cards[j].getCArdValue();
                   maxIndex = j;
                }
            }
            Card temp = cards[i];
            cards[i] = cards[maxIndex];
            cards[maxIndex] = temp;
        }
    }
}



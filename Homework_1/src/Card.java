public class Card {
    private String card;
    private int cardValue;

    //S = 400, H = 300, D = 200, C = 100
    //A = 14, K = 13, Q = 12, J = 11 ...

    public Card(String card){
        this.card = card;
        char suite = card.charAt(0);
        String val = card.substring(1);
        suite = Character.toUpperCase(suite);
        switch(suite){
            case('S') : cardValue += 400;
                        break;
            case('H') : cardValue += 300;
                        break;
            case('D') : cardValue += 200;
                        break;
            case('C') : cardValue += 100;
                        break;
        }

        switch(val){
            case("A") : cardValue += 14;
                        break;
            case("K") : cardValue += 13;
                        break;
            case("Q") : cardValue += 12;
                        break;
            case("J") : cardValue += 11;
                        break;
            default : cardValue += Integer.parseInt(val);
        }
    }

    public int getCArdValue(){
        return this.cardValue;
    }

    @Override
    public String toString(){
        return card;
    }
}

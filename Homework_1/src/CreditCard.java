public class CreditCard {
    private String creditCardNumber;
    private String cardHolderName;
    private String bank;
    private int limit;
    private double balance;

    public CreditCard(String creditCardNumber, String cardHolderName, String bank, int limit, double balance){
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.bank = bank;
        this.limit = limit;
        this.balance = balance;
    }

    public String getCreditCardNumber(){return creditCardNumber;}
    public String getCardHolderName() {return cardHolderName;}
    public String getBank() {return bank;}
    public int getLimit() {return limit;}
    public double getBalance(){return balance;}

    @Override
    public String toString(){
        return "CreditCard [creditCardNumber=" + creditCardNumber
                + ", cardHolderName=" + cardHolderName + ", bank=" + bank
                + ", limit=" + limit + ", balance=" + balance + "]";
    }

}

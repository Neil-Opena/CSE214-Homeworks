import java.nio.channels.IllegalChannelGroupException;
import java.util.*;
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

    //chargeIt takes in a price as the parameter
    //Returns -1 if the transaction fails
    //Returns balance if the transaction went through
    public double chargeIt(double price) throws IllegalArgumentException{
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if(balance + price > limit){
            return -1;
        }
        balance += price;
        return balance;
    }

    public double payment(double amount, Date day) throws IllegalArgumentException{
        if(amount > balance){
            throw new IllegalArgumentException("Amount exceeds Balance");
        }
        boolean hasFee = false;
        if(day.getDay() > 15){
            hasFee = true;
        }
        if(hasFee){
            double fee = 0.05 * balance;
            balance += fee;
            balance -= amount;
        }
        balance -= amount;
        return balance;
    }


}

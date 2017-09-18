Neil Opena 110878452
Homework #1 = Complexity Analysis and Abstract Data Types

(I created a Test class with a main method to test all of the assignments. Feel free to look at the class to observe how I tested the methods)

1.) Complexity Analysis
Call the static methods sumPairs or sumTriplets from my ComplexityAnalysis class.
	-Parameters: an array cotaining integers, an integer as the key
	-Precondition: array not empty
	-Postcondition: returns a boolean if a pair / triplet is found
			displays all pairs/triplets 
	-Throws: IllegalArgumentException - array is empty

To call: ComplexityAnalysis.sumPairs(arr, key);
	 ComplexityAnalysis.sumTriplets(arr, key);

2.) CreditCard class
Create a credit card object and simply call the methods

chargeIt(double price)
	-Parameter: a double as the price
	-Precondition: price is not negative
	-Postcondition: Returns -1, if the transaction fails (purchase makes the balance exceed the limit), or returns the balance itself if the transaction is successful
	-Throws: IllegalArgumentException - price is negative

payment(double amount, int day)
	-Parameter: a double as the amount of the payment, an int as the day of when the payment is done
	-Precondition: amount cannot exceed the balance, day has to be in the range [1,31]
	-Postcondition: Returns the balance after the payment is done. A 5% fee is added to the balance if the day > 15
	-Throws: IllegalArgumentException - amount exceeds balance , day does not exist

3.) CardSorting
I replicated the test program given in the Homework page. My program essentially does the same thing. 

4.)NavigationTest
I also replicated the sample test program. It runs the same as that of the sample test program. 

getCalculatedDistance()
	-Parameter: none
	-Precondition: currentLocation and destinationLocation is set
	-Postcondition: returns the distance between the two points
	-Throws: NullPointerException - locations weren't set

getArrivalTime()
	-Parameter: none
	-Precondition: currentLocation and destinationLocation is set
	-Postcondition: returns the arrival time (used v = s/t formula)
	-Throws: NullPointerException - locations weren't set




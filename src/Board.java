/**
 *  This class represents the dice on the board. It allows the user to
 *  reroll certain dice, and it calculates the score for a given category.
 *  It keeps two lists - one list of the five dice, and 
 *  one that keeps track of how many of each die value are showing. 
 *  For instance, if the dice are {1, 1, 4, 1, 4} then the second array 
 *  would have 3 in the slot for (1) and 2 in the slot for (4). The easiest
 *  way to do this might be to have a seven-element array, and ignore index
 *  0. (I'll put in a -1 so I remember it doesn't count.) This would then look 
 *  like {-1, 3, 0, 0, 2, 0, 0}. - such a secondary array will make it MUCH 
 *  easier to find things like four of a kind, a bunch of 3's, or a full house.
 */
public class Board {

	// TODO: decide which private member variables Board needs and declare them here.
	//          HINT: See the note above!
	private int[] diceArray;
	private int[] valueArray;
	
	/**
	 * constructor - set up initial values for the board.
	 */
	// TODO: write the Board's constructor
	public Board ()
	{
		diceArray = new int[]{0, 0, 0, 0, 0};
		valueArray = new int[]{-1,0,0,0,0,0,0};
	}
	
	/**
	 * toString - creates a string depicting the dice on the board.
	 * @return a string depicting the dice.
	 * I suggest that you also put letters to label which die is which:
	 * +---+---+---+---+---+
	 * | A | B | C | D | E |
	 * +---+---+---+---+---+
	 * | 1 | 1 | 4 | 1 | 4 |
	 * +---+---+---+---+---+
	 */
	// TODO: write the Board's toString method
	
	/**
	 * rollSelectedDice - takes a string and goes letter by letter; if a
	 * letter is in the range A-E, it randomizes the corresponding die.
	 * e.g., if the string is "ABE," then you would roll dice 0, 1, and 4.
	 * To roll all the dice, send it "ABCDE."
	 * I suggest you call updateFrequencyList at the end of this method.
	 * 
	 * @param - a string to parse. (e.g., "CE")
	 * no return value - only internal variables are changed.
	 */
	public void rollSelectedDice(String stringToParse)
	{
		// I'm starting this one for you, since we haven't been splitting strings yet.
		for (int i=0; i<stringToParse.length(); i++)
		{
			String letter = stringToParse.substring(i, i+1);
			// TODO: write code here to look at "letter" and based on it, 
			//       decide whether to re-roll one of the dice.
		}
	
	}
	
	/**
	 * recalculates the frequency list - how many 1s are showing on the dice? 
	 *    How many 2s are showing? 3s? 4s? 5s? 6s?
	 * postcondition: the frequency list now reflects the current state of the dice.
	 * No return value - only internal variables are changed.
	 */
	public void updateFrequencyList()
	{
		// ---------------------------
		// TODO: write your code here.
		
		// ---------------------------
	}
	
	
	/**
	 * getScoreForCategory - considers the current set of dice and calculates
	 * what score would be awarded if the player picks the given category.
	 * Although some simple categories can be handled here, I'd suggest you write a
	 * sub-method for each of the complicated ones (or just for each of all of them) to
	 * perform this calculation, rather than a single, 100+ line method! 
	 * @param - category. This is an integer. However, rather than putting code numbers 
	 *   in your code, it will be more readable if you use well-named constants.
	 *   I would refer you to the public final variables in the ScoreCard class - it
	 *   will be easier to read than the code numbers, themselves. (See the example below.)
	 * @return how many points should be awarded, based on the dice and the
	 *   category selected.
	 *   For example: 
	 *   Consider the dice as {1,1,4,1,4}; then 
	 *    •this would correspond to the frequencies list: {0, 3, 0, 0, 2, 0}  
	 *    •getScoreForCategory(ScoreCard.ONES) would return 3.
	 *    •getScoreForCategory(ScoreCard.TWOS) would return 0.
	 *    •getScoreForCategory(ScoreCard.FOURS) would return 8.
	 *    •getScoreForCategory(ScoreCard.THREE_OF_A_KIND) would return 11.
	 *    •getScoreForCategory(ScoreCard.FULL_HOUSE) would return 25.
	 *    •getScoreForCategory(ScoreCard.LARGE_STRAIGHT) would return 0.
	 */
	// TODO: Write Board's getScoreForCategory method.
	
	/**
	 * gets the list of die frequencies calculated in the updateFrequencies() method.
	 * @return the list of frequencies.
	 */
	public int[] getFrequencies()
	{
		int [] result = null; // I just put this in here to compile - you can replace it.
		// ---------------------------
		// TODO: write your code here.
		
		// ---------------------------
		return result;
	}
	
	/**
	 * sets the 5 dice to specific values - this should be used for debugging purposes only.
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 */
	public void debugSetDice(int a, int b, int c, int d, int e)
	{
		// ---------------------------
		diceArray [0] = a;
		diceArray [1] = b;
		diceArray [2] = c;
		diceArray [3] = d;
		diceArray [4] = e;
		// ---------------------------		
	}
	
	public int[] debugGetDice()
	{
		// temporary, so this compiles
		// ---------------------------
		return diceArray;
		// ---------------------------

	}
}

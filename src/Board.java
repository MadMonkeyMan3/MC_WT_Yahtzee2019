import java.util.function.ToDoubleBiFunction;

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

	private int[] diceArray;
	private int[] valueArray;
	
	/**
	 * constructor - set up initial values for the board.
	 */
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
	public String toString()
	{
		String result = "";
		result = result + "+---+---+---+---+---+ \n| A | B | C | D | E | \n+---+---+---+---+---+\n";
		for (int i = 0; i < 5; i++)
		{
			result = result + "| " + diceArray[i] + " ";
		}
		result = result + "|\n+---+---+---+---+---+ \n";
		return result;
	}


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

			if(letter.equals("A") || letter.equals("a"))
			{
				diceArray[0] = (int) (Math.random()*6 + 1);
			}
			if(letter.equals("B") || letter.equals("b"))
			{
				diceArray[1] = (int) (Math.random()*6 + 1);
			}
			if(letter.equals("C") || letter.equals("c"))
			{
				diceArray[2] = (int) (Math.random()*6 + 1);
			}
			if(letter.equals("D") || letter.equals("d"))
			{
				diceArray[3] = (int) (Math.random()*6 + 1);
			}
			if(letter.equals("E") || letter.equals("e"))
			{
				diceArray[4] = (int) (Math.random()*6 + 1);
			}
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
		for(int i = 1; i < valueArray.length; i++)
        {
            for(int j = 0; j < diceArray.length; i++)
            {
                if(diceArray[j] == i)
                {
                    valueArray[i]++;
                }
            }
        }
		
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
	public int getScoreForCategory(int category)
	{
		int result = 0;
		if(category >= ScoreCard.ONES && category <= ScoreCard.SIXES)
		{
			return(valueArray[category+1] * (category+1));
		}
		else if(category == ScoreCard.THREE_OF_A_KIND || category == ScoreCard.FOUR_OF_A_KIND)
		{
			for(int i = 1; i <=6; i++)
			{
				if(valueArray[i] == 3 || valueArray[i] == 4)
				{
					return(i * valueArray[i]);
				}
			}
		}
		else if(category == ScoreCard.FULL_HOUSE && isFullHouse())
		{
			return(25);
		}
		else if(category == ScoreCard.SMALL_STRAIGHT && isStraight() >= 4)
		{
			return(30);
		}
		else if(category == ScoreCard.LARGE_STRAIGHT && isStraight() >= 5)
		{
			return(40);
		}
		else if(category == ScoreCard.YAHTZEE)
		{
			for(int i = 0; i < valueArray.length; i++)
			{
				if(valueArray[i] == 5)
				{
					return(50);
				}
			}
		}
		else if(category == ScoreCard.CHANCE)
		{
			for(int i = 0; i < diceArray.length; i++)
			{
				result = result + diceArray[i];
			}
			return result;
		}
		else if(category < -1 || category > 12)
		{
			return -1;
		}
		return 0;
	}

	public int isStraight()
	{
		int straight = 0;
		for(int i = 0; i < valueArray.length; i++)
		{
			if(valueArray[i] > 0)
			{
				straight++;
			}
			else
			{
				straight = 0;
			}
		}
		return straight;
	}

	public boolean isFullHouse()
	{
		boolean isThree = false;
		boolean isTwo = false;
		for(int i = 0; i < valueArray.length; i++)
		{
			if(valueArray[i] == 3)
			{
				isThree = true;
			}
			else if(valueArray[i] == 2)
			{
				isTwo = true;
			}
		}
		if(isTwo && isThree)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * gets the list of die frequencies calculated in the updateFrequencies() method.
	 * @return the list of frequencies.
	 */
	public int[] getFrequencies()
	{
		return valueArray;
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

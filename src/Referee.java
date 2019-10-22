import java.util.Arrays;
import java.util.Scanner;
/**
 * The Referee class is in charge of keeping track of the Board (the dice)
 * and one or two ScoreCards, depending on how many players there are. It
 * handles the interactions with the player, asking the player what to do,
 * determining whether the moves are legal, displaying the board and scorecard,
 * and updating the board and scorecard.
 *
 */
public class Referee {
	private Scanner keyReader = new Scanner(System.in);
	private ScoreCard[] myScoreCards; // one for each player
	private Board theBoard;
	private ScoreCard ScoreCard1 ;
	private ScoreCard ScoreCard2 ;
	private String player0Name, player1Name, Score;

	/**
	 * constructor - set up the Referee class
	 */
	public Referee()
	{
		//--------------------
		// TODO: insert your code here.
		theBoard = new Board();
		myScoreCards = new ScoreCard[2];
		//--------------------
	}

	/**
	 * roll all the dice at once, as in the start of a new turn.
	 */
	public void rollAllDice()
	{
		// I've written this one for you. ;^)
		rollDice("ABCDE");
	}

	/**
	 * roll some dice, as indicated
	 * @param diceToRoll - a string that indicates which dice to roll. For instance,
	 * "ADE" would mean to roll die A, die D and die E, without changing die B or
	 * die C.
	 */
	public void rollDice(String diceToRoll)
	{
		// I've written this one for you, too.
		theBoard.rollSelectedDice(diceToRoll);
	}

	/**
	 * playGame - the main game loop for the Referee.
	 */
	public void playGame()
	{
		System.out.println("Welcome to Yahtzee! Please refer to these categories when scoring, \n 'ones' 'twos' 'threes' 'fours' 'fives' 'sixes' '3 of a kind' '4 of a kind' 'full house' 'sm straight' 'lg straight' \n 'chance' (this will add up all your dice and dosen't have a list of requirements ) and of course, 'yahtzee'  ") ;// placeholder code.
		// maybe some instructions right here
		System.out.print("Player 1, what is your name? ");
		player0Name = keyReader.nextLine();
		System.out.print("Player 2, what is your name? ");
		player1Name = keyReader.nextLine();
		myScoreCards = new ScoreCard[]{new ScoreCard(), new ScoreCard()};
		boolean gameisplaying = true;
		int turn = 0;
		String currentName = player0Name;

		while(gameisplaying)
		{
			rollAllDice();
			int rounds = 0;
			System.out.println("It is "+currentName+"'s turn.");
			while(rounds < 3)
			{
				rounds++;
				System.out.println("Your Roll was:\n"+theBoard.toString());
				System.out.println("Do you want to Roll? (y/n)");
				String answer = keyReader.nextLine();
				
				if(answer.equalsIgnoreCase("n"))
				{
					rounds = 3;
					break;
				}
				if(answer.equalsIgnoreCase("y"))
				{
					System.out.println("Which dice do you want to roll? (Pick any: A/B/C/D/E)");
					rollDice(keyReader.nextLine());
				}

			}

			if(turn == 0)
			{
				turn = 1;
				currentName = player1Name;
			}
			else if(turn == 1)
			{
				turn = 0;
				currentName = player0Name;
			}
		}

	}

	/**
	 * displayBoardAndDice - does a s.o.p. to the screen, showing
	 * the score card for the current player, the dice, and ABCDE
	 * labels for the dice.
	 */
	public void displayBoardAndDice()
	{
		//--------------------
		// TODO: insert your code here.

		//--------------------
	}

	//-------------------------------
	// Code for debugging/testing only:

	public void debugSetDice(int[] inDice)
	{
		theBoard.debugSetDice(inDice[0], inDice[1], inDice[2], inDice[3], inDice[4]);
		theBoard.updateFrequencyList();
	}

	public int[] debugGetDice()
	{
		return theBoard.debugGetDice();
	}

	public int[] debugGetTotals()
	{
		return theBoard.getFrequencies();
	}
}

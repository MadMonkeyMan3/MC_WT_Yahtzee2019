import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
		System.out.println("Welcome to Yahtzee! Please refer to these categories when scoring, \n 'ONES' 'TWOS' 'THREES' " +
				"'FOURS' 'FIVES' 'SIXES' 'THREE OF A KIND' 'FOUR OF A KIND' 'FULL HOUSE' 'SMALL STRAIGHT'" +
				" 'LARGE STRAIGHT' \n 'CHANCE' (this will add up all your dice and dosen't have a list of requirements )" +
				" and of course, 'YAHTZEE'  ") ;// placeholder code.
		// maybe some instructions right here
		System.out.print("Player 1, what is your name? ");
		player0Name = keyReader.nextLine();
		System.out.print("Player 2, what is your name? ");
		player1Name = keyReader.nextLine();
		myScoreCards = new ScoreCard[]{new ScoreCard(), new ScoreCard()};
		boolean gameisplaying = true;
		int turn = 0;
		String currentName = player0Name;
		int round = 1;

		while(gameisplaying)
		{
			rollAllDice();
			int rolls = 0;
			System.out.println("It is "+currentName+"'s turn.");
			while(rolls < 2)
			{
				rolls++;
				System.out.println("Your Roll was:\n"+theBoard.toString());
				String answer;
				do {
					System.out.println("Do you want to Roll? (y/n)");
					answer = keyReader.nextLine();
					if (answer.equalsIgnoreCase("n")) {
						rolls = 3;
						break;
					}
					if (answer.equalsIgnoreCase("y")) {
						System.out.println("Which dice do you want to roll? (Pick any: A/B/C/D/E)");
						rollDice(keyReader.nextLine());
					}
				}
				while(!answer.equalsIgnoreCase("y")&&!answer.equalsIgnoreCase("n"));
			}

			System.out.println("Your Roll is:");
			System.out.println(theBoard);
			System.out.println("Your Score is:");
			System.out.println(myScoreCards[turn]);
			System.out.println("Which score should this be?");

			while(true)
			{
				try{
					scoreRolls(turn);
					break;
				}
				catch(NullPointerException e) {
					System.out.println("Please input a correct place to put your score.");
				}
			}

			System.out.println("Your Score is now:");
			System.out.println(myScoreCards[turn]);

			if(round == 13 && turn == 1)
			{
				gameisplaying = false;
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
				round++;
			}
		}
		System.out.println(player0Name+"'s score is:\n"+myScoreCards[0].toString());
		System.out.println(player1Name+"'s score is:\n"+myScoreCards[1].toString());
		if (myScoreCards[0].getTotal() > myScoreCards[1].getTotal())
		{
			System.out.println(player0Name+" wins!");
		}
		else if (myScoreCards[1].getTotal() > myScoreCards[0].getTotal())
		{
			System.out.println(player1Name+" wins!");
		}
		else
		{
			System.out.println("It's a tie!");
		}
	}

	public void scoreRolls(int turn)
	{
		//I just figured this out from StackOverflow lol.
		Map<String, Integer> scores = new HashMap<>();
		scores.put("ONES", ScoreCard.ONES);
		scores.put("TWOS", ScoreCard.TWOS);
		scores.put("THREES", ScoreCard.THREES);
		scores.put("FOURS", ScoreCard.FOURS);
		scores.put("FIVES", ScoreCard.FIVES);
		scores.put("SIXES", ScoreCard.SIXES);
		scores.put("SMALL STRAIGHT", ScoreCard.SMALL_STRAIGHT);
		scores.put("LARGE STRAIGHT", ScoreCard.LARGE_STRAIGHT);
		scores.put("FULL HOUSE", ScoreCard.FULL_HOUSE);
		scores.put("THREE OF A KIND", ScoreCard.THREE_OF_A_KIND);
		scores.put("FOUR OF A KIND", ScoreCard.FOUR_OF_A_KIND);
		scores.put("CHANCE", ScoreCard.CHANCE);
		scores.put("YAHTZEE", ScoreCard.YAHTZEE);

		while(true)
		{
			Score = keyReader.nextLine();
			if(myScoreCards[turn].categoryIsEmpty(scores.get(Score.toUpperCase())))
			{
				int calcScore = theBoard.getScoreForCategory(scores.get(Score.toUpperCase()));
				if (scores.get(Score.toUpperCase()) == ScoreCard.YAHTZEE && myScoreCards[turn].getScoreForCategory(ScoreCard.YAHTZEE) > 0 && calcScore != 0)
				{
					calcScore = myScoreCards[turn].getScoreForCategory(ScoreCard.YAHTZEE) + 100;
				}
				myScoreCards[turn].setScoreForCategory(calcScore, scores.get(Score.toUpperCase()));
				break;
			}
			else
			{
				System.out.println("That category is full, please pick another one.");
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

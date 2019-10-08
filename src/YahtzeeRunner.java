
public class YahtzeeRunner {

	public static void main(String[] args) {

		boolean testingMode = true;
		if (testingMode)
		{
			System.out.println("Start tests");
			// enter test code here.
			Referee ref = new Referee();
			int[] testArray = {0,0,0,0,0};
			ref.debugSetDice(testArray);
			System.out.println("End tests");
		}
		else
		{
			Referee ref = new Referee();
			System.out.println("Start game");
			ref.playGame();
			System.out.println("End game");
		}
	}

}

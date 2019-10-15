
public class YahtzeeRunner {

	public static void main(String[] args) {

		boolean testingMode = false;
		if (testingMode)
		{
			System.out.println("Start tests");
			// enter test code here.
			Referee ref = new Referee();
			int[] testArray = {4,3,3,4,1};
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

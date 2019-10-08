
public class YahtzeeRunner {

	public static void main(String[] args) {

		boolean testingMode = true;
		if (testingMode)
		{
			System.out.println("Start tests");
			// enter test code here.
			Referee ref = new Referee();
			int[] testArray = {4,3,3,4,1};
			ref.debugSetDice(testArray);
			testArray = ref.debugGetDice();
			System.out.println(""+testArray[0] + " " + testArray[1] + " " + testArray[2] + " " + testArray[3] + " " + testArray[4]);
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

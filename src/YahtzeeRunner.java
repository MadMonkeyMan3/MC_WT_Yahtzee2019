
public class YahtzeeRunner {

	public static void main(String[] args) {

		boolean testingMode = true;
		if (testingMode)
		{
			System.out.println("Start tests");
			// enter test code here.
			System.out.println("End tests");
			System.out.println("Does this work");
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

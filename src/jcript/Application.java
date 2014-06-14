package jcript;

public class Application {

	private static void debugSetups(Settings setups) {
		setups.setInputFile("/home/andrey/Papka/input.txt");
		setups.setOutputFile("/home/andrey/Papka/output.txt");
	}


	public static void main(String[] args) {
		Settings setups = new Settings();

		// ...
		debugSetups(setups);

		CriptFactory cripterCreator = new CriptFactory();

		try {
			Cripter cripter = cripterCreator.createCript(setups);
			cripter.criptFile();

		} catch (InvalideCriptAlgorithmException e) {
			System.out.println("Caught " + e);
			return;
		} finally {
			Wishmaster finaler = new Wishmaster(setups);
			finaler.start();
		}
	}
}

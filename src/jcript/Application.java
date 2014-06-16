package jcript;

public class Application {

	private static void debugSetups(Settings setups) {

		setups.setCriptAlgorithm("fna");
		setups.setCriptKey(0x6000 + 0x300 + 0x40 + 0x4);

		boolean revFlag = false;
		if (revFlag) {
			setups.setReverse(true);

			setups.setInputFile("/home/andrey/Papka/output.txt");
			setups.setOutputFile("/home/andrey/Papka/input1.txt");
		} else {
			setups.setReverse(false);

			setups.setInputFile("/home/andrey/Papka/input.txt");
			setups.setOutputFile("/home/andrey/Papka/output.txt");
		}
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
			System.err.println("[!] Error: Invalide algorithm `" + setups.getCriptAlgorithm() + "`.");
			return;
		} finally {
			Wishmaster finaler = new Wishmaster(setups);
			finaler.start();
		}
	}
}

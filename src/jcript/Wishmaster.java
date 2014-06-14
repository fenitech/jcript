package jcript;

import java.io.IOException;

public class Wishmaster {
	public Wishmaster(Settings setups) {
		this.setups = setups;
	}

	public void start() {
		try {
			setups.getInputFile().iFStream.close();
		} catch(IOException e) {
			System.err.println("[!] Error: Error by closing input file.");
		}
		try {
			setups.getOutputFile().oFStream.close();
		} catch(IOException e) {
			System.err.println("[!] Error: Error by closing output file.");
		}
	}

	private Settings setups;
}

package jcript;

import java.io.IOException;

public class Cripter {

	public Cripter(Settings setups) {
		this.setups = setups;
	}

	public void criptFile() {
		try {
			while(this.endFlag != true) {
				setPreCriptBuf();
				cript.doCripting();
				writeCript();
			}
		} catch(java.io.IOException e) {
			System.err.println("[!] Error: Error by writing/reading file.");
		}
	}


	private void setPreCriptBuf() throws IOException {
		java.io.InputStream f = this.setups.getInputFile().iFStream;
		byte[] bit = new byte[1];
		boolean flag = true;
		int i = 0;

		while (i < setups.getCriptBufSize() && flag) {
			flag = (f.read(bit, 0, 1) <= f.available());
			buf.setBit(i, bit[0]);
			i++;
		}

		this.endFlag = !flag;
		if (i < setups.getCriptBufSize()) {
			setups.setCriptBufSize(i);
		}
	}

	private void writeCript() throws IOException {
		java.io.OutputStream f = setups.getOutputFile().oFStream;

		for (int i = 0; i < setups.getCriptBufSize(); i++) {
			f.write(buf.getBit(i));
		}
	}


	// ..
	public void setBuf(CriptBuf buf) {
		this.buf = buf;
	}

	public void setCriptAlgorithm(ICriptAlgorithm alg) {
		this.cript = alg;
	}

	private boolean endFlag = false;

	private CriptBuf buf;
	private ICriptAlgorithm cript;
	private Settings setups;
}

package jcript;

public class CriptBuf {

	public CriptBuf(int bufSize) {
		this.buf = new byte[bufSize];
	}


	public void setBit(int n, byte val) {
		try {
			this.buf[n] = val;
		} catch(IndexOutOfBoundsException e) {
			System.err.println("[!] Error: Index `setBit()` out of the bound.");
		}
	}

	public byte getBit(int n) {
		byte b = 0;

		try {
			b = this.buf[n];
		} catch(IndexOutOfBoundsException e) {
			System.err.println("[!] Error: Index `getBit()` out of the bound.");
		}

		return b;
	}


	private byte[] buf;
}

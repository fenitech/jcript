package jcript;

public class Cript_FFA extends ICriptAlgorithm {
	
	final int CRIPT_BLOCK_SIZE = 4;
	
	public Cript_FFA(Settings setups) {super(setups);}


	@Override
	public void doCripting() {
		byte temp;
		
		for (int i = 0; i < setups.getCriptBufSize(); i += CRIPT_BLOCK_SIZE) {
			if (i + CRIPT_BLOCK_SIZE <= setups.getCriptBufSize()) {
				for (int j = 0; j < CRIPT_BLOCK_SIZE / 2; j++) {
					temp = buf.getBit(i + j);
					buf.setBit(i + j,
					           reverseBit(buf.getBit(i + CRIPT_BLOCK_SIZE - j - 1)));

					buf.setBit(i + CRIPT_BLOCK_SIZE - j - 1,
					           reverseBit(temp));
				}
			} else {
				for (int j = 0; i + j < setups.getCriptBufSize(); j++) {
					buf.setBit(i + j, reverseBit(buf.getBit(i + j)));
				}
			}
		}
	}


	private byte reverseBit(byte bit) {
		return (byte) ((bit & ~0x44) | ((bit & 0x4) << 0x4) | ((bit & 0x40) >> 0x4));
	}
}

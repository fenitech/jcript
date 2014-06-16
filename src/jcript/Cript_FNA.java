package jcript;

public class Cript_FNA extends ICriptAlgorithm {
// 6 3 4 4
	private int key_first_mask;
	private int key_second_mask;
	private int key_mask_space;
	private int key_first_punch;
	private int key_second_punch;

	public Cript_FNA(Settings setups) {
		super(setups);

		key_first_mask   = (int) Math.pow(2, (int) log_2(setups.getCriptKey()        % 0x10));
		key_second_mask  = (int) Math.pow(2, (int) log_2(setups.getCriptKey() / 0x10 % 0x10));

		key_mask_space = (int) log_2(setups.getCriptKey() / 0x10 % 0x10 * 0x10)
		               - (int) log_2(setups.getCriptKey() % 0x10);

		key_first_punch  = setups.getCriptKey() / 0x1000 % 0x10;
		key_second_punch = setups.getCriptKey() / 0x100  % 0x10;

		if (setups.isReverse()) {
			int temp = key_first_punch;
			key_first_punch = key_second_punch;
			key_second_punch = temp;
		}
	}


	@Override
	public void doCripting() {

		punchWith(key_first_punch);
		punchWith(key_second_punch);
	}


	private void punchWith(int key_punch) {
		this.tempBuf = new byte[key_punch][key_punch];

		for (int i = 0; i < setups.getCriptBufSize(); i += key_punch * key_punch) {
			if (i + key_punch * key_punch < setups.getCriptBufSize()) {
				punchBlock(i, key_punch);
			} else {
				punchBlock(i, (int) Math.sqrt(setups.getCriptBufSize() - i));
				criptLastBlockFrom(setups.getCriptBufSize() - i -
				    (int) Math.pow((int) Math.sqrt(setups.getCriptBufSize() - i), 2));
			}
		}
	}

	private void punchBlock(final int from, final int key_punch) {
		int i;

		for (i = 0; i < key_punch * key_punch; i++) {
			tempBuf[i % key_punch][i / key_punch] = buf.getBit(i + from);
		}
		for (i = 0; i < key_punch * key_punch; i++) {
			buf.setBit(i + from, reverseBit(tempBuf[i / key_punch][i % key_punch]));
		}
	}

	private void criptLastBlockFrom(int from) {
		for (int i = from; i < setups.getCriptBufSize(); i++) {
			buf.setBit(i, reverseBit(buf.getBit(i)));
		}
	}

	private byte reverseBit(byte bit) {
		return (byte) ( (bit & ~(key_second_mask * 0x10 + key_first_mask))
		             | ((bit & key_first_mask) << key_mask_space)
		             | ((bit & key_second_mask * 0x10) >>> key_mask_space) );
	}

	// Логарифм по основанию 2.
	private double log_2(double x) {
		return Math.log(x) / Math.log(2);
	}

	private byte[][] tempBuf;
}

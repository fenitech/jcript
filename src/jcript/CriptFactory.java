package jcript;

public class CriptFactory {

	public CriptFactory() {}


	public Cripter createCript(Settings setups) throws InvalideCriptAlgorithmException {
		Cripter cripter = new Cripter(setups);

		ICriptAlgorithm alg;

		if (setups.getCriptAlgorithm() == "ffa") {
			alg = new Cript_FFA(setups);
		} else {
			throw new InvalideCriptAlgorithmException();
		}

		CriptBuf buf = new CriptBuf(setups.getCriptBufSize());
		alg.setBuf(buf);

		cripter.setBuf(buf);
		cripter.setCriptAlgorithm(alg);

		return cripter;
	}
}

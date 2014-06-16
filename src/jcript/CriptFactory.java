package jcript;

public class CriptFactory {

	public CriptFactory() {}


	public Cripter createCript(Settings setups) throws InvalideCriptAlgorithmException {
		Cripter cripter = new Cripter(setups);

		ICriptAlgorithm alg;

		String algName = setups.getCriptAlgorithm();
		if        (algName == "ffa") {
			alg = new Cript_FFA(setups);
		} else if (algName == "fna") {
			alg = new Cript_FNA(setups);
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

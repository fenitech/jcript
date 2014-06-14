package jcript;

public abstract class ICriptAlgorithm {
	public ICriptAlgorithm(Settings setups) {
		this.setups = setups;
	}


	public abstract void doCripting();


	public void setBuf(CriptBuf buf) {
		this.buf = buf;
	}


	protected CriptBuf buf;
	protected Settings setups;
}

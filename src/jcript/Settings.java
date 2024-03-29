package jcript;

import java.io.FileNotFoundException;

public class Settings {

	public Settings() {
		this.remove         = false;
		this.reverse        = false;
		this.criptAlgorithm = "ffa";
		this.criptBufSize   = 1024 * 1024;
		this.criptKey = 0;

		this.inputFile = new InputFileStruct();
		this.outputFile = new OutputFileStruct();
	}


	public void setRemove(boolean val) {
		this.remove = val;
	}
	
	public void setReverse(boolean val) {
		this.reverse = val;
	}
	
	public void setCriptAlgorithm(String algName) {
		this.criptAlgorithm = algName;
	}
	
	public void setCriptBufSize(int val) {
		this.criptBufSize = val;
	}

	public void setCriptKey(int key) {
		if        (key          % 0x10 == 0) {
			key -= key          % 0x10 + 0x4;
		} else if (key / 0x10   % 0x10 == 0) {
			key -= key / 0x10   % 0x10 + 0x4;
		} else if (key / 0x100  % 0x10 == 0) {
			key -= key / 0x100  % 0x10 + 0xf;
		} else if (key / 0x1000 % 0x10 == 0) {
			key -= key / 0x1000 % 0x10 + 0x8;
		}

		this.criptKey = key;
	}


	public void setInputFile(String name) {
		try {
			this.inputFile.iFDescript = new java.io.File(name);
			this.inputFile.iFStream = new java.io.FileInputStream(this.inputFile.iFDescript);
		} catch(FileNotFoundException e) {
			System.out.println("[!] Error: Input file not found.");
		}
	}

	public void setOutputFile(String name) {
		try {
			this.outputFile.oFDescript = new java.io.File(name);
			this.outputFile.oFStream = new java.io.FileOutputStream(this.outputFile.oFDescript);
		} catch(FileNotFoundException e) {
			System.out.println("[!] Error: Output file not found.");
		}
	}


	public boolean isRemove() {
		return this.remove;
	}
	
	public boolean isReverse() {
		return this.reverse;
	}
	
	public String getCriptAlgorithm() {
		return this.criptAlgorithm;
	}
	
	public int getCriptBufSize() {
		return this.criptBufSize;
	}

	public int getCriptKey() {
		return this.criptKey;
	}


	public InputFileStruct getInputFile() {
		return this.inputFile;
	}

	public OutputFileStruct getOutputFile() {
		return this.outputFile;
	}


	private boolean remove;
	private boolean reverse;
	private String criptAlgorithm;
	private int criptBufSize;
	private int criptKey;
	
	private InputFileStruct inputFile;
	private OutputFileStruct outputFile;


	class InputFileStruct {
		public java.io.File iFDescript;
		public java.io.InputStream iFStream;
	}

	class OutputFileStruct {
		public java.io.File oFDescript;
		public java.io.OutputStream oFStream;
	}
}

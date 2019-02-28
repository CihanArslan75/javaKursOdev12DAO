package com.cihan.odev12.model;

public enum BedenListesi {
	XXSMALL(32),
	XSMALL(34),
	SMALL(36),   
	MEDIUM(38),     
	LARGE(40),  
	XLARGE(42), 
	XXLARGE(44);
	
	private final int bedenNo;   // 32,34,36,38,40,42,44

	BedenListesi(int bedenNo) {
	    this.bedenNo = bedenNo;
	}
	
	public int getBedenNo() {
		return  this.bedenNo;
	}
}

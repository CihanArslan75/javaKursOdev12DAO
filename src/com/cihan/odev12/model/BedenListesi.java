package com.cihan.odev12.model;

public enum BedenListesi {
	XSMALL(34),
	SMALL(36),   
	MEDIUM(38),     
	LARGE(40),  
	XLARGE(42); 
	
	private final int bedenNo;   // 34,36,38,40,42

	BedenListesi(int bedenNo) {
	    this.bedenNo = bedenNo;
	}
	
	public int getBedenNo() {
		return  this.bedenNo;
	}

}

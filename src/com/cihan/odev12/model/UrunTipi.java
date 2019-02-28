package com.cihan.odev12.model;

import java.io.Serializable;

public class UrunTipi implements Serializable{
	private static final long serialVersionUID = 8108618787654718788L;
	private  Integer id;
	private String urunTipi;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrunTipi() {
		return urunTipi;
	}
	public void setUrunTipi(String urunTipi) {
		this.urunTipi = urunTipi;
	}
	
}

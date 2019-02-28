package com.cihan.odev12.model;

import java.io.Serializable;
import java.util.Iterator;

public class UrunStok implements Serializable{
	private static final long serialVersionUID = -798779737461615977L;
	private Integer id;
	private Integer urunId;
	private Integer adet;
	private Integer birimFiyati;
	private BedenListesi urunBeden;
	private int urunBedenNo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUrunId() {
		return urunId;
	}
	public void setUrunId(Integer urunId) {
		this.urunId = urunId;
	}
	public Integer getAdet() {
		return adet;
	}
	public void setAdet(Integer adet) {
		this.adet = adet;
	}
	public Integer getBirimFiyati() {
		return birimFiyati;
	}
	public void setBirimFiyati(Integer birimFiyati) {
		this.birimFiyati = birimFiyati;
	}
	public BedenListesi getUrunBeden() {
		BedenListesi be = null;
		for (BedenListesi b :urunBeden.values() )
		{
			 if(b.getBedenNo()==this.urunBedenNo)
			 {
				 be=b;
				 break;
			 }
		} 
	    
	 return be;		
	}
	
	public void setUrunBeden(BedenListesi urunBeden) {
		this.urunBeden = urunBeden;
	}
	public int getUrunBedenNo() {
		return urunBedenNo;
	}
	public void setUrunBedenNo(int urunBedenNo) {
		this.urunBedenNo = urunBedenNo;
	}

	

}

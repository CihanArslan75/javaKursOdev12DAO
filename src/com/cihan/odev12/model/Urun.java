package com.cihan.odev12.model;

import java.io.Serializable;
import java.util.Date;

public class Urun implements Serializable{
	private static final long serialVersionUID = 290621658115976492L;
	private Integer id;
	private String urunadi;
	private String urunrenk;
	private Date uretimtarihi;
	private String urunmarka;
	private Integer  urunid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrunadi() {
		return urunadi;
	}
	public void setUrunadi(String urunadi) {
		this.urunadi = urunadi;
	}
	public String getUrunrenk() {
		return urunrenk;
	}
	public void setUrunrenk(String urunrenk) {
		this.urunrenk = urunrenk;
	}
	public Date getUretimtarihi() {
		return uretimtarihi;
	}
	public void setUretimtarihi(Date uretimtarihi) {
		this.uretimtarihi = uretimtarihi;
	}
	public String getUrunmarka() {
		return urunmarka;
	}
	public void setUrunmarka(String urunmarka) {
		this.urunmarka = urunmarka;
	}
	public Integer getUrunid() {
		return urunid;
	}
	public void setUrunid(Integer urunid) {
		this.urunid = urunid;
	}
}

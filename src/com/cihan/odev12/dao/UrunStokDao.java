package com.cihan.odev12.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cihan.odev12.model.UrunStok;
import com.cihan.odev12.runner.Runner;

public class UrunStokDao {
		
	public List<UrunStok> getUrunStok(Integer urunId) throws SQLException {
		List<UrunStok> urunStokListe = new ArrayList<UrunStok>();
		ConnectionManagerPool mng = ConnectionManagerPool.getInstance();
		Connection conn= mng.getConnection();
		Statement stmt=conn.createStatement();
		
		String sql="select urunid, adet, birimfiyati, id, urunbedenno from urunstok "
				+ "where urunid="+urunId+" and deleteuser is null order by id";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			UrunStok urunStok = new UrunStok();
			urunStok.setId(rs.getInt("id"));
			urunStok.setUrunId(rs.getInt("urunid"));
			urunStok.setAdet(rs.getInt("adet"));
			urunStok.setBirimFiyati(rs.getInt("birimfiyati"));
			urunStok.setUrunBedenNo(rs.getInt("urunbedenno"));
			urunStokListe.add(urunStok);
			
		}
		  if(!conn.isValid(1000))
		//if(conn!=null)
		 {
              conn.close();
              mng.used[mng.counter]=false;
		  }
		  if(stmt!=null)
			  stmt.close();
		  if(rs!=null)
			  rs.close();
		return urunStokListe;
	}
	
	public List<UrunStok> getUrunStokId(Integer Id) throws SQLException {
		List<UrunStok> urunStokListe = new ArrayList<UrunStok>();
		ConnectionManagerPool mng = ConnectionManagerPool.getInstance();
		Connection conn= mng.getConnection();
		Statement stmt=conn.createStatement();
		
		String sql="select urunid, adet, birimfiyati, id, urunbedenno from urunstok "
				+ "where id="+Id;
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
			UrunStok urunStok = new UrunStok();
			urunStok.setId(rs.getInt("id"));
			urunStok.setUrunId(rs.getInt("urunid"));
			urunStok.setAdet(rs.getInt("adet"));
			urunStok.setBirimFiyati(rs.getInt("birimfiyati"));
			urunStok.setUrunBedenNo(rs.getInt("urunbedenno"));
			urunStokListe.add(urunStok);
			
		}
		  if(!conn.isValid(1000))
		//if(conn!=null)
		 {
              conn.close();
              mng.used[mng.counter]=false;
		  }
		  if(stmt!=null)
			  stmt.close();
		  if(rs!=null)
			  rs.close();
		return urunStokListe;
	}
	
	public String urunStokUpdate(List<Integer> temp)  throws SQLException {
		    String mesaj;
		    ConnectionManagerPool mng = ConnectionManagerPool.getInstance();
		    Connection conn= mng.getConnection();
			Statement stmt = conn.createStatement();
			String timeStamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(Calendar.getInstance().getTime());
			String sql = "Update  urunstok "
					   + "set adet="+temp.get(1)+" , birimfiyati="+temp.get(2)+"  ,updateuser=" +Runner.user +"  ,updatedate='"+timeStamp+"'"
					   + "where id="+temp.get(0) ;
			int count = stmt.executeUpdate(sql);
			if(count >0) {
				mesaj=temp.get(0) +" Nolu Ürünün Stogu Değiştirildi";
			}
			else {
				mesaj="Hata Oluştu";
			}

		  if(!conn.isValid(1000))
				//if(conn!=null)
		  {
		    conn.close();
		    mng.used[mng.counter]=false;
				  }
		  if(stmt!=null)
			  stmt.close();
		  return mesaj;
	
	}
	
	public String urunStokDelete(Integer Id)  throws SQLException {
	    String mesaj;
	    ConnectionManagerPool mng = ConnectionManagerPool.getInstance();
	    Connection conn= mng.getConnection();
		Statement stmt = conn.createStatement();
		String timeStamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(Calendar.getInstance().getTime());
		String sql = "Update  urunstok "
				   + "set deleteuser=" +Runner.user +"  ,deletedate='"+timeStamp+"'"
				   + "where id="+Id ;
		int count = stmt.executeUpdate(sql);
		if(count >0) {
			mesaj=Id +" Nolu Ürünün Stogu Silindi ";
		}
		else {
			mesaj="Hata Oluştu";
		}

	  if(!conn.isValid(1000))
			//if(conn!=null)
	  {
	    conn.close();
	    mng.used[mng.counter]=false;
			  }
	  if(stmt!=null)
		  stmt.close();
	  return mesaj;

}
	
}

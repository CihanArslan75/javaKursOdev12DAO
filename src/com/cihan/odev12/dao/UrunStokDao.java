package com.cihan.odev12.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cihan.odev12.model.BedenListesi;
import com.cihan.odev12.model.UrunStok;

public class UrunStokDao {
	
	public List<UrunStok> getUrunStok(Integer urunId) throws SQLException {
		List<UrunStok> urunStokListe = new ArrayList<UrunStok>();
		ConnectionManagerPool mng = ConnectionManagerPool.getInstance();
		Connection conn= mng.getConnection();
		Statement stmt=conn.createStatement();
		
		String sql="select urunid, adet, birimfiyati, id, urunbedenno from urunstok where urunid="+urunId+" order by id";
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

}

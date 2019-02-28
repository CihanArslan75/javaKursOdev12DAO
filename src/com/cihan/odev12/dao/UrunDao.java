package com.cihan.odev12.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cihan.odev12.model.Urun;

import sun.security.jca.GetInstance;

import com.cihan.odev12.dao.ConnectionManagerPool;

public class UrunDao {
	
	public Urun getUrun(Integer id) throws SQLException{
		Urun urun=new Urun();
		ConnectionManagerPool mng=ConnectionManagerPool.getInstance();
		Connection conn=mng.getConnection();
		Statement stmt= conn.createStatement();
		String sql ="select urunadi, urunrenk, uretimtarihi, urunmarka, id, urunid from urun where id="+id;
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			urun.setId(rs.getInt("id"));
			urun.setUrunadi(rs.getString("urunadi"));
			urun.setUrunrenk(rs.getString("urunrenk"));
			urun.setUretimtarihi(rs.getDate("uretimtarihi"));
			urun.setUrunmarka(rs.getString("urunmarka"));
			urun.setUrunid(rs.getInt("urunid"));
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
		return urun;
	}
	
	
	public List<Urun>  getUrunList() throws SQLException{
		ConnectionManagerPool mng= ConnectionManagerPool.getInstance();
		Connection conn=mng.getConnection();
		Statement stmt = conn.createStatement();
		String sql="select urunadi, urunrenk, uretimtarihi, urunmarka, id, uruntipiid from urun order by id";
		ResultSet rs = stmt.executeQuery(sql);
		List<Urun> urunListe=new ArrayList<Urun>();
	
		while(rs.next()) {
		   Urun urun = new Urun();
		   urun.setId(rs.getInt("id"));
		   urun.setUrunadi(rs.getString("urunadi"));
		   urun.setUrunrenk(rs.getString("urunrenk"));
		   urun.setUretimtarihi(rs.getDate("uretimtarihi"));
		   urun.setUrunmarka(rs.getString("urunmarka"));
		   urun.setUrunid(rs.getInt("uruntipiid"));
		   urunListe.add(urun);
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
		return urunListe;
	}

}

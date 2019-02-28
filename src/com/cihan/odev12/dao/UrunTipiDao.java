package com.cihan.odev12.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UrunTipiDao {
	
	public String getUrunTipi(Integer id) throws SQLException{
		String urunTipiAdi = null;
		
		ConnectionManagerPool mng = ConnectionManagerPool.getInstance();
		Connection conn =mng.getConnection();
		Statement stmt = conn.createStatement();
		String sql="select uruntipiadi from uruntipi where id="+id;
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
			urunTipiAdi =rs.getString("uruntipiadi");
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
		return urunTipiAdi;
	}

}

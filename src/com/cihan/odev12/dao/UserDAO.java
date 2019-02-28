package com.cihan.odev12.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cihan.odev12.model.User;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class UserDAO {

	public User getUserForName(String userName) throws SQLException {
		ConnectionManagerPool mng= ConnectionManagerPool.getInstance();
		Connection conn= mng.getConnection();
		Statement stmt = conn.createStatement();
		String sql ="select id,username,password from usr where username ='" + userName + "'";
		ResultSet rs= stmt.executeQuery(sql);
		User usr = null ;
		if(rs.next()) {
			usr = new User();	
			usr.setId(rs.getInt("id"));
			usr.setUsername(rs.getString("username"));
			usr.setPassword(rs.getString("password"));
		}
		 if(!conn.isValid(1000))
		  {
              conn.close();
              mng.used[mng.counter]=false;
		  }
		  if(stmt!=null)
			  stmt.close();
		  if(rs!=null)
			  rs.close();
		return usr;
	}

}

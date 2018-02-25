package com.neuedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.bean.RowMap;

public class JdbcUtil {
	static final String driver="com.mysql.jdbc.Driver";
	static final String url="jdbc:mysql://192.168.226.188:3306/test2?useUnicode=true&characterEncoding=utf8";
	static final String user="root";
	static final String password="123456";
	
	public static Connection getconnection(){
		Connection con = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con,PreparedStatement pre,ResultSet rs){
			try {
				if(rs!=null)
				rs.close();
				if(pre!=null)
					pre.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static int executeUpdate(String sql,Object...params){
		int result=0;
		Connection con =getconnection();
		PreparedStatement pre = null;
		try {
			pre = con.prepareStatement(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					pre.setObject(i+1, params[i]);
				}
			}
			result=pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, pre, null);
		}
		return result;
	}
	public static<T> List<T> executeQuery(String sql,RowMap<T> rowMap,Object...params){
		List<T> list = new ArrayList<>();
		Connection con =getconnection();
		PreparedStatement pre= null;
		ResultSet rs=null;
		try {
			pre = con.prepareStatement(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					pre.setObject(i+1, params[i]);
				}
			}
			rs=pre.executeQuery();
			while(rs.next()){
				T t =rowMap.rowmapping(rs);
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, pre, rs);
		}
		return list;
		}
}

package com.neuedu.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.util.JdbcUtil;

public class StudentDao {
	
	public List<Student> getStudents(){
		return JdbcUtil.executeQuery("select * from student", new RowMap<Student>() {

			@Override
			public Student rowmapping(ResultSet rs) {
				// TODO Auto-generated method stub
				Student student = new Student();
				try {
					student.setId(rs.getInt("id"));
					student.setName(rs.getString("name"));
					student.setBId(rs.getInt("b_id"));
					student.setGender(rs.getInt("gender"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return student;
			}
		}, null);		
		
		
		/*
		List<Student> students=new ArrayList<>();
		Connection con = JdbcUtil.getconnection();
		PreparedStatement pre =null;
		ResultSet rs =null;
		try {
			//创建命令窗口
			pre = con.prepareStatement("select * from student");
			//执行sql语句
			rs = pre.executeQuery();
			while(rs.next()){
				Student student =new Student();
				student.setId(rs.getInt("id"));
				student.setBId(rs.getInt("b_id"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getInt("gender"));
				students.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				JdbcUtil.close(con, pre, rs);
		}
		return students;
		
	*/}
	public int add(Student student){
		int result = 0;
		/*Connection con =JdbcUtil.getconnection();
		PreparedStatement pre =null;
		try {
			//创建命令窗口
			pre = con.prepareStatement("insert into student (name,b_id,gender) values(?,?,?)");
			//注入参数
			pre.setString(1, student.getName());
			pre.setInt(2, student.getBId());
			pre.setInt(3, student.getGender());
			//执行sql语句
			result=pre.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally{
				JdbcUtil.close(con, pre, null);
		}*/
		result= JdbcUtil.executeUpdate("insert into student (name,b_id,gender) values(?,?,?)", student.getName(),student.getBId(),student.getGender());
		return result;
	}
	public int update(Student student){
		int result= JdbcUtil.executeUpdate("update student set name=?,b_id=?,gender=? where id=?", student.getName(),student.getBId(),student.getGender(),student.getId());
		/*int result = 0;
		Connection con = JdbcUtil.getconnection();
		PreparedStatement pre =null;
		try {
			//创建命令窗口
			pre = con.prepareStatement("update student set name=?, b_id=?, gender=? where id=?");
			//注入参数
			pre.setString(1, student.getName());
			pre.setInt(2, student.getBId());
			pre.setInt(3, student.getGender());
			pre.setInt(4, student.getId());
			//执行sql语句
			result=pre.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally{
				JdbcUtil.close(con, pre, null);
		}*/
		return result;
	
	}
	public int del(int id){
		int result=JdbcUtil.executeUpdate("delete from student where id=?", id);
		return result;
	}
}

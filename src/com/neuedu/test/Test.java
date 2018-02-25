package com.neuedu.test;

import java.util.List;

import com.neuedu.bean.Student;
import com.neuedu.bean.StudentDao;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentDao dao=new StudentDao();
		List<Student> students =dao.getStudents();
		for (Student student : students) {
			System.out.println(student);
		}
/*		Student student= new Student();
		student.setName("½ªÃÏÎ°");
		student.setBId(1);
		student.setGender(1);
		int result=dao.add(student);
		System.out.println(result);
		*/
		/*Student student= new Student();
		student.setName("ºîÒÕ½İ");
		student.setBId(1);
		student.setGender(1);
		student.setId(5);
		int result=dao.update(student);
		System.out.println(result);*/
	/*	Student student= new Student();
		int result=dao.del(9);
		System.out.println(result);*/
	}

}

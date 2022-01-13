package study.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import study.jdbc.ClientEx2;

public class createTable {

	public static void main(String[] args) throws SQLException {
		
		Connection con = ClientEx2.makeConnection();
		
//		String sql = "create table emp(empno int primary key, ename varchar(20) , salary int)";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//	
//		pstmt.execute();
//		System.out.println("Table Created....!!!");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter empno , emp name ,salary:");
		String input = sc.next();
		String []values = input.split(",");
		
		String str = "insert into emp values(?,?,?)";
		PreparedStatement pst = con.prepareStatement(str);
		
		pst.setInt(1, Integer.parseInt(values[0]));
		pst.setString(2, values[1]);
		pst.setInt(3, Integer.parseInt(values[2]));
		pst.executeUpdate();
		System.out.println("Inserted Successfully.....");
		
	}
}

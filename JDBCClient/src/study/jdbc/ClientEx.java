package study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClientEx {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//load the Driver class so that its static block gets executed
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String jdbc_url = "jdbc:mysql://localhost:3306/advjava";
		String uname = "root";
		String pw = "root";
		
		Connection con = DriverManager.getConnection(jdbc_url, uname, pw);
		
		System.out.println("Connection to Database is successfull "+con);
		
		Statement stmt = con.createStatement();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter id ,cost , name of book seperated by comm :");
		String input = sc.nextLine();
		
		String []value = input.split(",");
		
		String sql = "insert into book values("+value[0]+","+value[1]+",' "+value[2]+"')";
		
		stmt.executeUpdate(sql);		
	}
}

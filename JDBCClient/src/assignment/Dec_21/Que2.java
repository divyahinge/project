package assignment.Dec_21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Que2 {

	public static void main(String[] args) {
		
		Connection connect = connectToServer(); 
		
		searchRecord(connect);
	}
	
	private static void searchRecord(Connection con) {
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Statement stmt = con.createStatement();
			System.out.println("Enter product id to see record :");
			int id = sc.nextInt();
			
			String search = "select * from product where product_id = ' "+id+" '";
			ResultSet rs = stmt.executeQuery(search);
			
			while(rs.next())
			{
				int id1 = rs.getInt(1);
				String name = rs.getString(2);
				int cost = rs.getInt(3);
				String desc = rs.getString(4);
				String date = rs.getString(5);
				
				System.out.println(id1+" "+name+" "+cost+" "+desc+" "+date);
				
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
	}

	private static Connection connectToServer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("the driver class is not found in the classpath");
		}
		
		String url = "jdbc:mysql://localhost:3306/advjava";
		String uname = "root";
		String pw = "root";
		
		Connection conn = null;
		try {
		   conn = DriverManager.getConnection(url, uname, pw);
		} catch (SQLException e) {
			System.out.println("could not counnect to DB "+e);
		}
		return conn;
	}
}

/* Write a Java Client that connects to the database and asks the user to enter a product id and show the details of
   that product id on console .( select query with where clause )*/

package assignment.Dec_21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Que4 {

public static void main(String[] args) {
		
		Connection con = connectToServer();
		showRecord(con);
	}
	
	private static void showRecord(Connection con) {
       
		try {
			Scanner sc = new Scanner(System.in);
			Statement stmt = con.createStatement();
			String str = "select product_name , product_expiry_date from product";
			
			ResultSet res = stmt.executeQuery(str);
			
			while(res.next())
			{
				String name = res.getString("product_name");
				String date = res.getString("product_expiry_date");
				
				System.out.println(name+"   "+date);
			}
			
			res.close();
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

/* Write a java client that shows all products in the table with following info
		a. Product name  Expiry Date*/

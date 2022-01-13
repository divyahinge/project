package assignment.Dec_21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Que5 {
 
	public static void main(String[] args) {
		Connection connect = connectToServer();
		
		updateRecord(connect);
	}

	private static void updateRecord(Connection connect) {
		
		Scanner sc = new Scanner(System.in);
		try {
			Statement stm = connect.createStatement();
			
			System.out.println("Enter Product id");
			int id = sc.nextInt();
			System.out.println("Enter new Description");
			String newdesc = sc.next();
			
			String str = "update product set product_desc = '"+newdesc+"' where product_id =" +id;
			stm.executeUpdate(str);
			
			System.out.println("Updated Successfully..");
			
			stm.close();
			connect.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
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

/*Write a java client that accepts a product id from the user and also enters a new product 
 * description ---update the table so that desc column is modified for that product.
 */

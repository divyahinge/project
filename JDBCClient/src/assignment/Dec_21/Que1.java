package assignment.Dec_21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Que1 {

	public static void main(String[] args) {
		
		Connection connect = connectToServer(); 
		insertRecord(connect);

	}

	private static void insertRecord(Connection connect) {
		while(true) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Which op u want to perform . Press-1 to insert and Press-2 to quit:");
		int input = sc.nextInt();
		
		if(input == 1)
		{
			try {
				Statement stmt = connect.createStatement();
				
				System.out.println("Enter product_id, product_name, product_cost, product_desc, product_expiry_date seperated by comm");
				String entry = sc.next();
				
				String []str = entry.split(",");
				String sql = "insert into product values("+str[0]+",'"+str[1]+"',"+str[2]+",'"+str[3]+"','"+str[4]+"')";
				
				stmt.executeUpdate(sql);	
				
				stmt.close();
				connect.close();
				
			} catch (SQLException e) {
				System.out.println("Unable to insert record into Product Table");
			}
			
		}
		if(input ==2)
		{
			break;
		}
		  
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




/*Create the table from mysql client-------------
Product table 
Columns ---product_id, product_name, product_cost, product_desc, product_expiry_date

Write a Java Client that connects to the database and inserts records into product table in a loop, till user says  "yes"*/
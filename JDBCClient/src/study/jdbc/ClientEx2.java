package study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientEx2 {

	public static void main(String[] args) {
		
		Connection connect = makeConnection();
		showAllRecords(connect);
	}

	private static void showAllRecords(Connection connect) {
		try {
			Statement stmt = connect.createStatement();
			String sql = "select * from book";
			ResultSet rs = stmt.executeQuery(sql);
			
			//processing the result set
			while(rs.next())
			{
				int id = rs.getInt(1);
				int cost= rs.getInt("book_cost");
				String name = rs.getString(3);
				
				
				System.out.println(id+" "+name+" "+cost);	
			}	
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
	}

	public static Connection makeConnection() {
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("the driver class is not found in the classpath");
		}
		
		String jdbc_url = "jdbc:mysql://localhost:3306/advjava";
		String uname = "root";
		String pw = "root";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(jdbc_url, uname, pw);
			
		} catch (SQLException e) {
			System.out.println("could not counnect to DB "+e);
		}
		return con;
	}
}

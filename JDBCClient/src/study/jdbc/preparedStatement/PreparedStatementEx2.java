package study.jdbc.preparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementEx2 {

	public static void main(String[] args) throws SQLException {
		
		Connection con = connectToServer();
		System.out.println("Connection to DB is successfull....");
		
		String sql = "select * from book where book_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, 2); //book_id=2
		ResultSet res = pstmt.executeQuery();
		
//		while(res.next())
//		{
//			int id = res.getInt(1);
//			int cost = res.getInt(2);
//			String name = res.getString(3);
//			
//			System.out.println(id+" "+cost+" "+name);
//		}
//		
		if(res.next())
			System.out.println(res.getInt(1)+" "+res.getInt(2)+" "+res.getString(3));
	}

	private static Connection connectToServer() {
		
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

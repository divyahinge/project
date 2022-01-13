package study.jdbc.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import study.jdbc.ClientEx2;

public class PreparedStatementExam1 {

	public static void main(String[] args) throws SQLException {
		
		Connection con = ClientEx2.makeConnection();
		
		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("Enter id , cost , book name comma seperated:");
//		String input = sc.next();
//		String []values = input.split(",");
//		
		String sql = "insert into book values(?,?,?)";
		
		//pstmt  is TIED UP with sql 
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//the 1 is the position of the  ? = parameterIndex
//		pstmt.setInt(1, Integer.parseInt(values[0]));
//		pstmt.setInt(2, Integer.parseInt(values[1]));
//		pstmt.setString(3, values[2]);
		
		while(true) {
			System.out.println("enter id");
			int id = sc.nextInt();
			
			System.out.println("enter cost");
			int cost = sc.nextInt();
					
			System.out.println("enter name");
			String name = sc.next();
		
			pstmt.setInt(1, id);
			pstmt.setInt(2, cost);
			pstmt.setString(3, name);
			
			
			pstmt.executeUpdate(); //DONT pass anything 
			}
	
	}
}

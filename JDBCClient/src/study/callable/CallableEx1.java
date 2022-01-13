package study.callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import study.jdbc.ClientEx2;

public class CallableEx1 {

	public static void main(String[] args) throws SQLException {
		Connection con = ClientEx2.makeConnection();
	
		String str = "{call pro7(?,?,?)}";
		CallableStatement cstmt = con.prepareCall(str);
		
		cstmt.registerOutParameter(3, Types.INTEGER);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter num1 :");
		int num1 = sc.nextInt();
		
		System.out.println("Enter num2 :");
		int num2 = sc.nextInt();
		
		cstmt.setInt(1, num1);
		cstmt.setInt(2, num2);
		
	    cstmt.execute();
	    
	    int sum = cstmt.getInt(3);
	    System.out.println("Sum = "+sum);
		
	}
}

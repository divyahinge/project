package study.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import study.jdbc.ClientEx2;

public class TransactionEx2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection con = ClientEx2.makeConnection();
		
		try {
			transaction(con,sc);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private static void transaction(Connection con, Scanner sc) throws SQLException {
		System.out.println("Enter acc number to withdraw(from)");
	    int acNo1 = sc.nextInt();
	    
	    System.out.println("Enter amount");
	    int amount = sc.nextInt();
	    
	    System.out.println("Enter acc number to deposit(to)");
	    int acNo2 = sc.nextInt(); 
	    
	    String sql = "update account set balance = ? where acc_no = ?";
	    
	    try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			con.setAutoCommit(false);
			
			//withdraw
			pstmt.setInt(1, amount);
			pstmt.setInt(2, acNo1);
			pstmt.executeUpdate();
			System.out.println("Withdraw Successfull.......");
			
			//deposit;
			pstmt.setInt(1, amount);
			pstmt.setInt(2, acNo2);
			
			int howmanyrowsupdated = pstmt.executeUpdate();
			
			if(howmanyrowsupdated == 0) {
				throw new SQLException();
			}
		
			System.out.println("Deposit Successfull.......");
			System.out.println("commiting");
			con.commit();
					
		} catch (SQLException e) {
			System.out.println("rolling back"+e);
			con.rollback();
		}
	    
	}
}

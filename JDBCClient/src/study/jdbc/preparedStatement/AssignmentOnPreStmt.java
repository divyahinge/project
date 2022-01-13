package study.jdbc.preparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import study.jdbc.ClientEx2;

public class AssignmentOnPreStmt {

	public static void main(String[] args) {
		
		Connection connect = ClientEx2.makeConnection();
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("0 - Exit \n 1 - Insert New Record \n 2 - Search Record(by id) \n 3 - Delete Record(by id)"
					+ "\n 4 - Show Record \n 5 - Update Record");
			System.out.println("Enter your choice:");
			int ch = sc.nextInt();
			
			if(ch ==0)
				break;
			switch (ch) {
			case 0:
				   break;
			case 1:
				insertRecord(connect);
				break;
				
			case 2:
				searchRecord(connect);
				break;
			
			case 3:
				deleteRecord(connect);
				break;
				
			case 4:
				showRecord(connect);
				break;
				
			case 5:
				updateRecord(connect);
				break;
				
			default:
				System.out.println("Enter valid choice..");
				break;
			}
		}

	}
	
private static void updateRecord(Connection con) {
		
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter Product id");
			int id = sc.nextInt();
			System.out.println("Enter new Description");
			String newdesc = sc.next();
			
			String str = "update product set product_desc = ? where product_id = ?" ;
			
			PreparedStatement pstmt = con.prepareStatement(str);
			
			pstmt.setString(1, newdesc);
			pstmt.setInt(2, id);
			
			pstmt.executeUpdate(str);
			
			System.out.println("Updated Successfully..");
			} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}

	
	private static void showRecord(Connection con) {
	       
		try {
			Scanner sc = new Scanner(System.in);
		
			String str = "select product_name , product_expiry_date from product";
			PreparedStatement pstmt = con.prepareStatement(str);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next())
			{
				String name = res.getString("product_name");
				String date = res.getString("product_expiry_date");
				
				System.out.println(name+"   "+date);
			}
						
		} catch (SQLException e) {
			System.out.println(e);
		}
				
	}
	private static void deleteRecord(Connection con) {
	       
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter product id to delete record :");
			int id = sc.nextInt();
			
			String delete = "delete from product where product_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, id);
		    pstmt.executeUpdate();
			
			System.out.println("Deleted Record Successfully");
						
		} catch (SQLException e) {
			System.out.println(e);
		}
				
	}

	private static void searchRecord(Connection con) {
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter product id to see record :");
			int id = sc.nextInt();
			
			String search = "select * from product where product_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(search);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int id1 = rs.getInt(1);
				String name = rs.getString(2);
				int cost = rs.getInt(3);
				String desc = rs.getString(4);
				String date = rs.getString(5);
				
				System.out.println(id1+" "+name+" "+cost+" "+desc+" "+date);
				
			}
		
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
	}
	
	private static void insertRecord(Connection connect) {
		while(true) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Which op u want to perform . Press-1 to insert and Press-2 to quit:");
		int input = sc.nextInt();
		
		if(input == 1)
		{
			try {
				
				System.out.println("Enter product_id, product_name, product_cost, product_desc, product_expiry_date seperated by comm");
				String entry = sc.next();
				
				String []str = entry.split(",");
				//String sql = "insert into product values("+str[0]+",'"+str[1]+"',"+str[2]+",'"+str[3]+"','"+str[4]+"')";
				
				String sql = "insert into product values(?,?,?,?,?)";
				
				PreparedStatement pstmt = connect.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(str[0]));
				pstmt.setString(2, str[1]);
				pstmt.setInt(3, Integer.parseInt(str[2]));
				pstmt.setString(4, str[3]);
				pstmt.setString(5,str[4]);
				
				pstmt.executeUpdate(sql);	
			
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

}

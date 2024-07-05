package JdbcPgm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class MainClassPgm {
	Registeration regi=new Registeration();
	Scanner s1=new Scanner(System.in);
	void getData()
	{
		System.out.println("Enter your Registeration Number ");
		regi.setRegisterationNumber(s1.nextInt());
		System.out.println("Enter the Name ");
		regi.setName(s1.next());
		System.out.println("Enter your Department ");
		regi.setDept(s1.next());
		System.out.println("Enter your Mobile Number ");
		regi.setMobileNo(s1.nextLong());
	}
	void insertData()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3308/jdbclbj30","root","");
			PreparedStatement preparedState=con.prepareStatement("insert into registeration (regis_no,name,dept,mobileNo) values(?,?,?,?)");
			preparedState.setInt(1, regi.getRegisterationNumber());
			preparedState.setString(2, regi.getName());
			preparedState.setString(3, regi.getDept());
			preparedState.setLong(4, regi.getMobileNo());
			int i=preparedState.executeUpdate();
			if(i>0)
			{
				System.out.println("Insertion Sucessfully");
			}
			else
			{
				System.out.println("Error Insertion");
			}
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		
	}
	void reteriveData()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3308/jdbclbj30","root","");
			PreparedStatement preparedStatement=con.prepareStatement("select * from registeration" );
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getInt(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getLong(5));
			}
			
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		
		
		
	}
	void updateData()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3308/jdbclbj30","root","");
			System.out.println("Enter the Mobile No u want to update");
			regi.setMobileNo(s1.nextLong());
			System.out.println("Enter the Register Number ");
			regi.setRegisterationNumber(s1.nextInt());
			
			PreparedStatement preparedStatement=con.prepareStatement("update registeration set mobileNo=? where regis_no=? ");
			preparedStatement.setLong(1, regi.getMobileNo());
			preparedStatement.setInt(2, regi.getRegisterationNumber());
			int i=preparedStatement.executeUpdate();
			if(i>0)
			{
				System.out.println("Update Operation Done Successfully");
			}
			else
			{
				System.out.println("Error in Updation ");
			}
			
			
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		
		
	}
	public static void main(String[] args) {
		MainClassPgm mcp=new MainClassPgm();
		mcp.getData();
		mcp.insertData();
		mcp.reteriveData();
		mcp.updateData();
		mcp.reteriveData();
	}
	

}

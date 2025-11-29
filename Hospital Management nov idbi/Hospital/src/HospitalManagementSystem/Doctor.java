package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

	private Connection connection;

	
	public Doctor(Connection connection, Scanner scanner)
	{
		this.connection = connection;
	
	}
	
	
	
	public void viewDoctors()
	{
		String query = "SELECT * FROM doctors";
	
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Doctors:");
			System.out.println(" | Doctor Id | name          | Specialization    | " );
			
			while(rs.next()) 
				{
				int id = rs.getInt("id");
				String name = rs.getString("name");
			
				String specialization = rs.getString("specialization");
				System.out.printf(" | %-10d | %-12s | %-20s | \n", id, name, specialization );
				}
			
		
			}
		
		
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean getDoctorById(int Id) {
		String query = "SELECT * FROM doctors WHERE id = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, Id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
			}catch(SQLException e) {
				
			}
		return false;
	}
}
	
	



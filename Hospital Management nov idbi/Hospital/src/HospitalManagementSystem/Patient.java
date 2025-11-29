package HospitalManagementSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Patient {

	private Connection connection;
	private Scanner scanner;
	
	public Patient(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public void addPatient()
	{
		System.out.println("Enter Patient Name:");
		String name = scanner.nextLine();
		
		System.out.println("Enter Patient Age:");
		int age = scanner.nextInt();
		
		System.out.println("Enter Patient Gender:");
		
String gender =		scanner.nextLine(); // Consume newline
		
		
		try {
			String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, gender);
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("Patient added successfully.");
			} 
			else {
				System.out.println("Failed to add patient.");
			}
			
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void viewPatients()
	{
		String query = "SELECT * FROM patients";
	
		try {
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Patient List:");
			System.out.println(" | Patient ID | Name         | Age    | Gender    | " );
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				System.out.printf(" | %-10d | %-12s | %-6d | %-9s | \n",  id, name, age, gender);
				
			}
		
}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getPatientById(int Id) {
		String query = "SELECT * FROM patients WHERE id = ?";
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
	
	

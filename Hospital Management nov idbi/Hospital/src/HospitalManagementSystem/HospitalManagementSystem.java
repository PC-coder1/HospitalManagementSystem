package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {

	
	
	private static final String url = "jdbc:mysql://localhost:3306/hospital";
	
	private static final String username = "root";
	
	private static final String password = "root";
	
	public static void main(String[] args) {
	try{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	}
	catch (ClassNotFoundException e)
	{
		e.printStackTrace();
	}
	Scanner scanner = new Scanner(System.in);
	try {
		Connection connection = DriverManager.getConnection(url, username, password);
		Patient patient = new Patient(connection, scanner);
		Doctor doctor = new Doctor(connection, scanner);
		while(true)
		{
			System.out.println("Hospital Management System");
		    System.out.println("1.Add Patient");
		    System.out.println("2.View Patient");
		    System.out.println("3. View Doctors");
		    System.out.println("4.Book Appointment");
		    System.out.println("5.Exit");
		    System.out.println("Enter your choice:");
		    
		    int choice = scanner.nextInt();
		    
		    
		    switch(choice)
		    {
		    
		    case 1:
		    // add patient
		    patient.addPatient();
		    
		    case 2:
		    	// view patient
		    	patient.viewPatients();
		    	
		    case 3:
		    	//view doctors
		    	
		    	doctor.viewDoctors();
		    	
		    case 4:
		    	// book appointment
		    
		    case 5:
		    	return;
		    	
		    default:
		    	System.out.println("Enter valid choice");
		    }
		}
	}
	catch(SQLException e)
	{
	e.printStackTrace();	
	}
	}
	
	
	public static void bookAppointment(Patient patient, Doctor doctor,Connection connection, Scanner scanner)
	{
		System.out.print("Enter Patient id: ");
		int patientId = scanner.nextInt();
		
		System.out.print("Enter Doctor id: ");
		
		int DoctorId = scanner.nextInt();
		
		System.out.print("Enter appointment date (YYYY-MM-DD");
		String appointmentDate = scanner.next();
		
		if (patient.getPatientById(patientId) && doctor.getDoctorById(DoctorId)) {
		    if (checkDoctorAvailability(DoctorId, appointmentDate, connection)) {
		        String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?,?,?)";
		        try {
		            PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
		            preparedStatement.setInt(1, patientId);
		            preparedStatement.setInt(2, DoctorId);
		            preparedStatement.setString(3, appointmentDate);

		            int affectedRows = preparedStatement.executeUpdate();
		            if (affectedRows > 0) {
		                System.out.println("Appointment booked successfully");
		            } else {
		                System.out.println("Failed to book appointment");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("Doctor not available on the selected date");
		    }
		} else {
		    System.out.println("Either patient or doctor does not exist");
		}
	}
	


	private static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
		// TODO Auto-generated method stub
		   String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
	        try{
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, doctorId);
	            preparedStatement.setString(2, appointmentDate);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if(resultSet.next()){
	                int count = resultSet.getInt(1);
	                if(count==0){
	                    return true;
	                }else{
	                    return false;
	                }
	            }
	        } catch (SQLException e){
	            e.printStackTrace();
	        }
	        return false;
	    }
	
	}
		
		
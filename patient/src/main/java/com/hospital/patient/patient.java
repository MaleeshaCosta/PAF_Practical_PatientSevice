package com.hospital.patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class patient {

	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Malee1999"); 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	
///////////////////////////
	
	public String readPatient() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>pid</th><th>pname</th><th>gender</th><th>address</th><th>phone </th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String pid = Integer.toString(rs.getInt("pid"));
				String pname = rs.getString("pname");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String phone = Integer.toString(rs.getInt("phone"));
				
				
				// Add into the html table
				//output += "<tr><td>" + itemID + "</td>";
				output += "<tr><td>" + pid + "</td>";
				output += "<td>" + pname + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + phone + "</td>";
				



				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"        "
						+ "value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      "
						+ "class=\"btn btn-danger\">" + "<input name=\"itemID\" type=\"hidden\" value=\"" + pid
						+ "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the appointments.";
			System.err.println(e.getMessage());
		}

		return output;

	}
	
	
	
//////////////////////////
	
	
	public String insertPatient(String pname, String gender, String address, String phone) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into patient (`pid`,`pname`,`gender`,`address`,`phone`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pname);
			preparedStmt.setString(3, gender);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, phone);
		


			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the patient.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	///////////////////////////
	public String updatePatient(int pid,String pname, String gender, String address,int phone) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE patient SET pname=?,gender=?,address=?,phone=? WHERE pid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, pname);
			preparedStmt.setString(2, gender);
			preparedStmt.setString(3, address);
			preparedStmt.setInt(4, phone );
			preparedStmt.setInt(5,pid);



			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Patient";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//////////////////////////
	
	public String deletePatient(String pid) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from patient where pid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the patient.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	/////////////////////////
	
	
	
	
}

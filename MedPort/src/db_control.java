import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db_control {

	private Connection mpCon;

	db_control(){

	}
	Connection getcon() {
		return mpCon;
	}

	boolean checkConnection(){

		try {
			mpCon = DriverManager.getConnection("jdbc:mysql://google/medport?cloudSqlInstance=medport&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=<MYSQL_USER_NAME>&password=<MYSQL_USER_PASSWORD>");

			return true;

		} catch (SQLException e) {

			return false;
		}
	}

	boolean validate(String user, String pass) {
		checkConnection();

		String checkuser = "SELECT * FROM employee_table WHERE username = \"" + user + "\" AND password = \"" + pass + "\";";
		try{
			Statement statement;
			ResultSet resultSet;
			statement = mpCon.createStatement();
			resultSet = statement.executeQuery(checkuser);

			if(resultSet.next()){
				return true;
			}
		}catch (SQLException e){
			return false;
		}
		return false; 

	}

	void getPatientProfile(String id, String name, String ssn) {
		checkConnection();
		
		String checkFirstName = "SELECT * FROM patient_table where first_Name = \"" + name + "\";";	
		String checkLastName = "SELECT * FROM patient_table where last_Name = \"" + name + "\";";
		String checkID = "SELECT * FROM patient_table where patient_id = \"" + id +  "\";";
		String checkSSN = "SELECT * FROM patient_table where ssnSerial = \"" + ssn + "\";";
		
		try{
			Statement statement,statement2,statement3,statement4;
			ResultSet resultSet, resultSet2, resultSet3, resultSet4;
			statement = mpCon.createStatement();
			statement2 = mpCon.createStatement();
			statement3 = mpCon.createStatement();
			statement4 = mpCon.createStatement();

			resultSet = statement.executeQuery(checkFirstName);

			if(resultSet.next()){
				patientFound(resultSet);
			}//end if
			else {

				resultSet2 = statement2.executeQuery(checkLastName);
				if(resultSet2.next()){

					patientFound(resultSet2);
				}
				else {

					resultSet3 = statement3.executeQuery(checkID);
					if(resultSet3.next()){

						patientFound(resultSet3);
					}else{

						resultSet4 = statement4.executeQuery(checkSSN);
						if(resultSet4.next()){

							patientFound(resultSet4);
						}
						patientProfile.found=false;
					}
					
				}
			}//end else 

			}catch (SQLException e){

				patientProfile.found=false;
			}


		}//end get patient profile
	
		void patientFound(ResultSet resultSet) {
			patientProfile.found=true;
			try {
				patientProfile.setID(resultSet.getNString("patient_id"));
				patientProfile.setFName(resultSet.getNString("first_name"));
				patientProfile.setMName(resultSet.getNString("mid_name"));
				patientProfile.setLName(resultSet.getNString("last_name"));
				patientProfile.setDOB(resultSet.getNString("DOB"));
				patientProfile.setGender(resultSet.getNString("gender"));
				patientProfile.setPDoctor(resultSet.getNString("primaryDoctor"));
				patientProfile.setSSN(resultSet.getNString("ssnSerial"));
				
				//getting address from address table
				setAddress();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				patientProfile.setID("ERROR");
			}

		}//end patient found
		
		void setAddress() {
				Statement statement5;
				ResultSet resultset5;
				String id = patientProfile.patientID;
				String checkAddress = "SELECT * FROM metportdb.address_table where address_id = " + id ;
				patientProfile.setStreetNum(patientProfile.patientID );
				
			try {
				statement5 = mpCon.createStatement();
				resultset5 = statement5.executeQuery(checkAddress);
				
				if(resultset5.next()){
					
					patientProfile.setStreetNum(resultset5.getNString("street_num"));
					patientProfile.setAptNum(resultset5.getNString("apt_num"));
					patientProfile.setStreetName(resultset5.getNString("street_name"));
					patientProfile.setCity(resultset5.getNString("city"));
					patientProfile.setState(resultset5.getNString("state").toUpperCase());
					patientProfile.setZipcode(resultset5.getNString("zipcode"));
					
				}//end if
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end setAddress

		boolean addPatientProfile(String patientID, String firstName, String midName,
				String lastName, String dateOfBirth, String gender, String primaryDoctor, String ssnArea, String ssnGroup, String ssnSerial) {

			if(checkConnection()){
				try {
					Statement statement = mpCon.createStatement();
					String insert = "INSERT INTO `metportdb`.`patient_table` (`patient_id`, `first_name`, `mid_name`, `last_name`, `DOB`, `gender`, `primaryDoctor`, `ssnArea`, `ssnGroup`, `ssnSerial`) "
							+ "VALUES ('"+ patientID + "', '"+ firstName +"', '"+ midName +"'" + 
							"		'"+ lastName+"', '"+ dateOfBirth+"', '"+ gender+"', '"+ primaryDoctor +"', '"+ ssnArea +"', '"+ ssnGroup + "', '" + ssnSerial +"');";	
					return true;

				} catch (SQLException e) {
					return false;
				}
			}

			return false;
		}//add patient profile

		boolean addEmployeeProfile(String eeID, String firstName, String midName,
				String lastName, String dob, String gender, String SSN, String doctor, String streetNum, String aptNum,
				String streetName, String city, String state, String zipcode) {

			if(checkConnection()){
				String insertProfile = "INSERT INTO `metportdb`.`patient_table` (`patient_id`, `first_name`, `mid_name`, `last_name`, `DOB`, `gender`, `primaryDoctor`, `ssnArea`, `ssnGroup`, `ssnSerial`) "
						+ "VALUES ('"+ eeID + "', '"+ firstName +"', '"+ midName +"'" + 
						"		'"+ lastName+"', '"+ dob+"', '"+ gender+"', '"+ SSN +"', '"+doctor+"')";
				String insertAddress = "INSERT INTO `metportdb`.`address_table` (`address_id`, `street_num`, `apt_num`, `street_name`, `city`, `state`, `zipcode`, "
						+ "VALUES ('"+ eeID + "', '"+ streetNum +"', '"+ aptNum +"'" + 
						"		'"+ streetName+"', '"+ city+"', '"+ state+"', '"+ zipcode +"');";	
				Statement statement, statement2;
				try {
					statement = mpCon.prepareStatement(insertProfile);
					statement2 = mpCon.prepareStatement(insertAddress);
					
					return true;
				} catch (SQLException e) {
					return false;
				}
			}
			return false;

		}
	}

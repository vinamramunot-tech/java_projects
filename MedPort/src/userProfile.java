
public class userProfile {
	private static String user, role;
	static String employeeID = "";
	static String firstName= "", midName= "", lastName= "", addressNum = "", streeName= "", cityName= "", stateName= "", gender= "", phoneNumber ="";
	
	
	userProfile(){
		
	}
	static void setUser(String u){
		user = u;
	}
	static String getUser(){
		return user;
	}
	public static String getRole() {
		return role;
	}
	public static void setRole(String role) {
		userProfile.role = role;
	}

}

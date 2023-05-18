package Driver;
import java.sql.*;
import java.util.Scanner;

public class Driver2 {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded!");
		Connection con =
		DriverManager.getConnection("jdbc:mysql://localhost/javatest","javaUser","password");
		System.out.println("Database Connected");
		//Create a statement
		Statement s = con.createStatement();
		//Read Table before inserting a new name.
		ResultSet rs = s.executeQuery
		("SELECT * FROM new_table");
		System.out.println("Before Add!");
		//Print out the results of the query.
		//(This will be empty the first time you run it)
		
		while(rs.next()) {
			//This is how you get an integer
			int ID = rs.getInt("idnew_table");
			//This is how you get a string.
			String name = rs.getString("name");
			System.out.println("ID: "+ID+" -> "+name);
		}
		
		//Ask the user to input a new name.
		Scanner kin = new Scanner(System.in);
		System.out.println("Enter a name: ");
		String n = kin.next();
		
		//For SQL commands that do not return results you must use execute not executeQuery
		//This will add the new name to the table, assumes idnew_table is Auto Incremented.
		Boolean result = s.execute("INSERT INTO new_table VALUE(0,'"+n+"')");
		System.out.println("The result from adding the name is: "+result);
		
		//Now read the whole table again!
		rs = s.executeQuery
		("SELECT * FROM new_table");
		System.out.println("After Add!");
		//Should print out your new name.
		while(rs.next()) {
			int ID = rs.getInt("idnew_table");
			String name = rs.getString("name");
			System.out.println("ID: "+ID+" -> "+name);
		}
	}
}

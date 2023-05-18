/*
 * CollectableCard.java - Abby Stucki
 * Program connects to MySQL, allows access to a table via user-friendly menu 
 */

package CollectableCards;
import java.sql.*;
import java.util.Scanner;

public class CollectableCard {
	public static Scanner scan;
	public static Statement s;
	public static ResultSet rs;
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// connect to schema
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/javatest","javaUser","password");
		System.out.println("Connection obtained.");
		
		// initialize s and scan
		s = con.createStatement();
		scan = new Scanner(System.in);
		
		// menu continues to execute
		boolean running = true;
		while(running) {
			System.out.println("MENU:");
			System.out.println("   1. Create a card");
			System.out.println("   2. Read all cards");
			System.out.println("   3. Update a card");
			System.out.println("   4. Delete a card");
			System.out.println("   5. Quit menu");
			
			System.out.println("\nPlease enter your decision as an integer: ");
			int i = scan.nextInt();
			
			// execute user decision
			switch (i) {
				case 1: createCard(); break;
				case 2: readCards(); break;
				case 3: updateCard(); break;
				case 4: deleteCard(); break;
				case 5: running = false; break;
				default: System.out.println("Invalid decision, please try again.");
			}
		}
		
		con.close();
	}
	
	public static void createCard() throws SQLException {
		// collect card data from user
		System.out.println("Creating new card. . .");
		System.out.println("     Please enter the card name: ");
		String name = scan.next();
		System.out.println("\n     Please enter the card rarity from [1-3]: ");
		int rarity = scan.nextInt();
		System.out.println("\n");
		
		// push data to MySQL via executed statement, else return to menu
		s.execute("INSERT INTO collectablecards(idcollectableCards,name,rarity) VALUES (0,'" + name + "'," + rarity + ")");
	}
	
	public static void readCards() throws SQLException {
		System.out.println("Reading all cards. . .\n");
		rs = s.executeQuery("SELECT * FROM collectablecards");
		
		while(rs.next()) {
			int id = rs.getInt("idcollectableCards");
			String name = rs.getString("name");
			int rarity = rs.getInt("rarity");
			System.out.println("ID: " + id +" -> " + name + ", " + rarity);
		}
		System.out.println("\n");
	}
	
	public static void updateCard() throws SQLException {
		//collect card data to be updated
		System.out.println("Updating a card. . .");
		System.out.println("     Please enter the card id as an integer: ");
		int id = scan.nextInt();
		System.out.println("     Please enter the updated name: ");
		String name = scan.next();
		System.out.println("     Please enter the updated rarity from [1-3]: ");
		int rarity = scan.nextInt();
		System.out.println("\n");
		
		// update card in MySQL via executed statement, else return to menu
		s.execute("UPDATE collectablecards SET name='" + name + "', rarity=" + rarity + " WHERE idcollectableCards=" + id);
		
	}
	
	public static void deleteCard() throws SQLException {
		// collect card data for specified deletion
		System.out.println("Deleting a card. . .");
		System.out.println("      Please enter the card id as an integer: ");
		int id = scan.nextInt();
		System.out.println("\n");
		
		// delete card from MySql via executed statement, else return to menu
		s.execute("DELETE FROM collectablecards WHERE idcollectableCards=" + id);
	}
}

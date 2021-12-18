package jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	
	public static Connection con;

	static String host = "localhost:3306";
	static String database = "assignment1";
	static String url = "jdbc:mysql://" + host + "/" + database;
	static {
		try {

			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "01MAY@gets");
			System.out.println("con " + con);

		} catch (Exception e) {
			System.out.println("NOTE:-  Exception at DBConfig " + e);
		}

	}

}

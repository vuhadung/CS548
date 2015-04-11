package connectDB.postgresql;

import java.sql.*;
import java.util.*;

public class ConnectPostgresqlDb {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// Get connection
			Class.forName("org.postgresql.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:postgresql://ec2-52-11-196-33.us-west-2.compute.amazonaws.com/serfdb",
							"serf", "tetatmui2015");
			// conn.setAutoCommit(false);
			System.out.println("Connected database successfully!");

			// Create new SQL statement
			stmt = conn.createStatement();
			String query = "select * from patient;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.printf("%d\t%s\t%s\t%d\n", rs.getInt(1),
						rs.getDate(2).toString(), rs.getString(3), rs.getInt(4));
			}
			rs.close();
			stmt.close();
			
			// Release all connection
			// conn.commit();
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

}

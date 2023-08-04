package pureView.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {

	public static Connection connect() throws ClassNotFoundException, SQLException {
		// 1. jdbc driver 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. DBMS 연결
		String url = "jdbc:oracle:thin:@10.3.1.105:1521/XE";
		String user = "c##pureview";
		String password = "pv0803";
		return DriverManager.getConnection(url, user, password);
	}

	public static void close(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
		}

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}

	}

}

package dbConnection;

import java.sql.*;

public class dbConnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// 加载驱动
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;DatabaseName=ecywz",
					"sa", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static void dbClose(Connection conn, PreparedStatement pstmt,
			ResultSet rs) {

		try {

			if (rs != null) {// 如果返回的结果集对象不能为空,就关闭连接

				rs.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		try {

			if (pstmt != null) {

				pstmt.close();// 关闭预编译对象

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		try {

			if (conn != null) {

				conn.close();// 关闭结果集对象

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

package dbConnection;

import java.sql.*;

public class dbConnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// ��������
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

			if (rs != null) {// ������صĽ����������Ϊ��,�͹ر�����

				rs.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		try {

			if (pstmt != null) {

				pstmt.close();// �ر�Ԥ�������

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		try {

			if (conn != null) {

				conn.close();// �رս��������

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

package oop;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/qlsv";
        String user = "root";
        String password = "123456"; // sửa lại mật khẩu của bạn

        return DriverManager.getConnection(url, user, password);
    }
}

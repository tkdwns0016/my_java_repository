package lamen;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String userId = "root";
		String password = "mysql";
		String url = "jdbc:mysql://localhost:3306/blackjack?"
				+ "charactorEncoding=utf-8&serverTimezone=Asia/Seoul";
		
		
		return DriverManager.getConnection(url,userId,password);
	}
}

package lamen;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Save implements Runnable{
	blackjack user;
	public Save(blackjack user) {
		this.user = user;
	}
	public void run() {
	String sql = "update user set rating=?, money=? where id=? and password=?";
		try(PreparedStatement pstm = MyConnect.getConnect().prepareStatement(sql)){
			pstm.setString(1, user.getrating());
			pstm.setInt(2, user.getMoney());
			pstm.setString(3, user.getId());
			pstm.setString(4, user.getPassword());
			pstm.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



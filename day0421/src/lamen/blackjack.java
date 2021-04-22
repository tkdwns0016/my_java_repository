package lamen;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class blackjack {
	String id;
	String password;
	String rating;
	int money;
	Scanner sc = new Scanner(System.in);
	private int userDeck[]= { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }; //플레이할동안 카드를 받을 횟수를 10번으로 잡고 배열 선언
	Socket socket;
	List<Socket> user = new ArrayList<Socket>();
	
	private static blackjack instance = new blackjack();

	public static blackjack getInstance() {
		return instance;
	}
	public blackjack(Socket socket) {
		this.socket = socket;
	}	
	public blackjack() {
	}		


	public blackjack(String id, String password,String rating, int money) {
		super();
		this.id = id;
		this.password = password;
		this.rating = rating;
		this.money = money;
	}

	public blackjack signup() {
		String selectid;
		String selectpw;
		blackjack user = new blackjack(socket);
		String sql ="insert into user values(?,?,\"브론즈\",100000)";
		int row=0;
		while(true) {
			try(PreparedStatement pstm = MyConnect.getConnect().prepareStatement(sql)){
				System.out.println("사용할 아이디를 정해주세요(최대 10글자)");
				selectid = sc.nextLine();
				if(selectid.length()>10) {
					System.out.println("10글자를 넘어섰습니다. 다시 입력해주세요");
				}else{
					String sql1 = "select id,count(id) as count from user where id=\""+selectid+"\"";
					try(PreparedStatement pstm1 = MyConnect.getConnect().prepareStatement(sql1);
							ResultSet rs = pstm1.executeQuery()){
						rs.next();
						int rsrow=rs.getInt("count");
						if(rsrow>0) {
							System.out.println("중복된 아이디가 있습니다.");
							continue;
						}else {
							pstm.setString(1, selectid);
							System.out.println("사용하실 비밀번호를 입력해주세요");
							selectpw = sc.nextLine();
							pstm.setString(2, selectpw);
							row = pstm.executeUpdate();
							if(row>0) {
								String selectall = "select * from user where id=\'"+selectid+"\' and password=\'"+selectpw+"\'";
								try(PreparedStatement pstm2 = MyConnect.getConnect().prepareStatement(selectall);
										ResultSet rs1 = pstm2.executeQuery()){
									rs1.next();
									user.setId(rs1.getString("id"));
									user.setPassword(rs1.getString("password"));
									user.setrating(rs1.getString("rating"));
									user.setMoney(rs1.getInt("money"));
									System.out.println("생성이 완료 되었습니다.");
									System.out.println(user);
									break;
								}
							}else {
								System.out.println("생성 실패, 다시 가입해주세요.");
							}
						}
					}
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}



	public void login(blackjack user) {
		String id1,password1;
		String sql = "select * from user where id=? and password=?";
		try(PreparedStatement pstm = MyConnect.getConnect().prepareStatement(sql)){
			System.out.println("Log In");
			while(true) {
				System.out.println("아이디를 입력해주세요");
				id1 = sc.nextLine();
				if(id1.equals("")) {
					System.out.println("공백을 입력하셨습니다");
				}else {
					pstm.setString(1, id1);
				}
				System.out.println("패스워드를 입력해주세요");
				password1 = sc.nextLine();
				pstm.setString(2, password1);
				try(ResultSet rs = pstm.executeQuery()){
					rs.next();
					System.out.println("로그인 성공했습니다.");
					user.setId(rs.getString("id"));
					user.setPassword(rs.getString("password"));
					user.setrating(rs.getString("rating"));
					user.setMoney(rs.getInt("money"));
					System.out.println("아이디 : "+user.getId()+"    보유 금액 : "+user.getMoney());
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}


	public void check(blackjack user) {
		String findid,findpw;
		String sql = "select * from user where id=? and password=?";
		try(PreparedStatement pstm = MyConnect.getConnect().prepareStatement(sql)){
			System.out.println("조회하실 아이디를 입력해주세요");
			findid = sc.nextLine();
			pstm.setString(1, findid);
			System.out.println("조회하실 비밀번호를 입력해주세요");
			findpw = sc.nextLine();
			pstm.setString(2, findpw);
			try(ResultSet rs = pstm.executeQuery()){
				rs.next();
				System.out.println("ID : "+rs.getString("id")+",  rating : "+rs.getString("rating")+",  Money : "+rs.getInt("money"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int[] getUserDeck() {
		return userDeck;
	}

	public Socket getSocket() {
		return socket;
	}
	public void setUserDeck(int[] userDeck) {
		this.userDeck = userDeck;
	}
	public void resetUserDeck() {
		int reuserDeck[]= { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
		this.userDeck = reuserDeck;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getrating() {
		return rating;
	}

	public void setrating(String rating) {
		this.rating = rating;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Scanner getSc() {
		return sc;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public static void setInstance(blackjack instance) {
		blackjack.instance = instance;
	}

	@Override
	public String toString() {
		return "blackjack [id=" + id + ", password=" + password + ", rating=" + rating + ", money=" + money+" ]";
	}



}
			
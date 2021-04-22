package lamen;

import java.util.Scanner;



public class product {

	public void run() {
		Scanner sc=new Scanner(System.in);
		//유저생성(테이블 없으니 객체생성)
		blackjack u1=new blackjack();
		u1.signup();
		u1.login(u1);
	}
}

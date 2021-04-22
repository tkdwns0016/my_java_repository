package lamen;

import java.util.Scanner;

public class first {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		CLS cls=new CLS();
		blackjack u = new blackjack();
		while(true) {
			System.out.println(">>> 메뉴를 선택해주세요 !!");
			System.out.println("1] 회원가입 ");
			System.out.println("2] 게임시작");
			System.out.println("3]  도움말");
			System.out.println("4]  조회");
			System.out.println("5]  종료");
			String choice = sc.nextLine();
			switch(choice) {
			case "1":
				cls.run();
				System.out.println(">>> 유저 아이디 생성을 선택하셨습니다.");
				product product = new product();
				product.run();
				break;
			case"2":
				cls.run();
				System.out.println(">>> 게임시작을 선택하셨습니다");
				Game game = new Game();
				game.run();
				break;
			case"3":
				cls.run();
				System.out.println(">>> 도움말을 선택하셨습니다");
				Help help = new Help();
				help.run();
				break;
			case"4":
				cls.run();
				System.out.println("정보를 조회합니다.");
				u.check(u);
				break;
			}
			if(choice.equals("5")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}
	}
}


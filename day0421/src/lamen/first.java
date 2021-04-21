package lamen;

import java.util.Scanner;

public class first {
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);

		while(true) {

			System.out.println("1. 회원가입  // 2.게임시작 // 3. 종료");
			String choice = sc.nextLine();
			switch(choice) {
			case "1":
				System.out.println("======생성=========");
				product product = new product();
				product.run();
				break;

			case"2":
				System.out.println("====게임시작=======");
				Game game = new Game();
				game.run();
				break;
			}
			if(choice.equals("3")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}
	}
}


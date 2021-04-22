package lamen;

import java.util.Scanner;

public class Help {
	public void run() {
		Scanner sc=new Scanner(System.in);
		CLS cls=new CLS();

		while(true) {
			System.out.println(">>> 메뉴를 선택해주세요");
			System.out.println("1] 블랙잭 도움말");
			System.out.println("2] 배팅 규칙");
			System.out.println("3] 이전메뉴");


			String msg=sc.nextLine();
			switch(msg) {
			case "1":
				System.out.println(">>> 블랙잭 설명 Rule ====");
				System.out.println("1. 플레이어와 딜러 간의 대결입니다.");
				System.out.println("2. 21에 가까운 쪽이 승리합니다.");
				System.out.println("3. 숫자 A는 1과 11로 계산합니다");
				System.out.println("4. [J],[Q],[K]는 숫자 10으로 통일합니다");
				System.out.println("5. 딜러는 16전에는 숫자를 받으나 16이 넘으면 카드받지 않습니다.");
				System.out.println(">>> 3초뒤 메뉴로 돌아갑니다.");

				try {
					Thread.sleep(3000);
					cls.run();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;

			case "2":
				System.out.println(">>> 배팅  ");
				System.out.println("1. 배팅은 판당 $5,000원 ");
				System.out.println("2. 승리할시 2배를, 패배시 $0원 획득");
				System.out.println(">>> 3초뒤 메뉴로 돌아갑니다");
				try {
					Thread.sleep(3000);
					cls.run();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			if(msg.equals("3")) {
				cls.run();
				break;
			}
		}
	}
}


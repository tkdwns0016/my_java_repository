package lamen;

import java.util.Random;
import java.util.Scanner;

public class Game{


	public void run() {

		CardDeck Deck = new CardDeck();
		int deckCard[]=Deck.getDeck();
		String cardShape[]=Deck.getPatterns(); //패턴을 받아옴
		String cardNumbers[] = Deck.getCardNumber(); //카드 숫자를 받아옴
		CLS cls = new CLS();
		Scanner sc = new Scanner(System.in);


		blackjack u1=new blackjack();
		blackjack u2=new blackjack();

		int userMoney = u1.getMoney();
		//카드생성
		Rating rating = new Rating(u1);
		Thread ratingThread = new Thread(rating);
		ratingThread.start();
		Save save = new Save(u1);
		Thread saveThread = new Thread(save);
		saveThread.start();
		while(true) {
			try{
				u1.login(u1);
				if(u1.getId().equals(null)) {
					break;
				}else {
					System.out.println("현재 잔액은  "+userMoney+" 포인트 입니다.");

					for (int i = 0; i < 52; i++) { // deck[0]=0;deck[1]=1;의 작업을 for문으로 빠르게 선언함
						deckCard[i] = i; // deck[0]=0;deck[1]=1;의 작업을 for문으로 빠르게 선언함
					}
					// 카드 섞기
					Random rd = new Random();
					for (int i = 0; i < 10000; i++) { // 카드를 만번 섞는다는 의미. 10000대신 다른 숫자를 넣어도 무방
						int rNumber = rd.nextInt(52);
						int temp;
						temp = deckCard[0];
						deckCard[0] = deckCard[rNumber];
						deckCard[rNumber] = temp;
					}
					// 카드를 한장씩 뽑아서 플레이어에게 나눠주는 작업 (중복되면 안되므로 나눠준 카드와 나눠줄 카드를 구분해야 한다)
					// -1이란 값은 -1이라는 숫자를 랜덤하게 나눠받은 카드에 대입하고자 사용함
					int deckIndex = 0;
					int userAIndex = 0;      
					int userBIndex = 0;
					boolean userAFlag = true;
					boolean userBFlag = true;
					int ScoreASum = 0;
					int ScoreBSum = 0;

					System.out.println("판돈은 3000포인트 입니다 이길 시 2배 패배 시 1.5배를 잃게 됩니다. 진행하시겠습니까?");
					System.out.println(" 1. 진행     // 2. 중단");
					int select = sc.nextInt();
					if(select ==1) {
						System.out.println("3000포인트를 냈습니다.");
						if(userMoney>3000) {
							userMoney -=3000;
						}else {
							System.out.println("잔액이 부족합니다.");
							break;
						}
					}else if(select == 2) {
						System.out.println("종료합니다.");
						u1.setMoney(userMoney);
						break;
					}else {
						System.out.println("다시 입력해주세요");
					}
					for (int j = 0; j < 10; j++) { // 분배받을 카드의 횟수를 10으로 지정함
						if (userAFlag) {
							System.out.println(u1.getId()+"님 카드를 받으시겠습니까? 1 or 2");
							switch(sc.nextLine()) {
							case "1":
								userAFlag = true;
								break;
							case "2":
								userAFlag = false;
								break;
							}

						}
						if (userBFlag) {
							System.out.println(" 카드를 받고있습니다.");
							if(ScoreBSum<16) {
								u2.getUserDeck()[userBIndex] = deckCard[deckIndex];
								userBIndex++;
								deckIndex++;
							}
							if(ScoreBSum>=16) {
								userBFlag = false;
							}
						}
						if (!(userAFlag || userBFlag)) {
							break;
						}
						if (userAFlag) {
							u1.getUserDeck()[userAIndex] = deckCard[deckIndex]; // deck[deckIndex]는 0이니까 userADeck[userAIndex]에 0을 넣어 userADeck[0]값이 된다
							deckIndex++; // 0 더하기 1
							userAIndex++; // 1을 더해서 값이 1로 변경된다
						}


						// userADeck 점수계산
						ScoreASum=0;
						for (int i = 0; i < userAIndex; i++) {
							int ScoreA = 0;
							ScoreA = u1.getUserDeck()[i] % 13 + 1;
							if (ScoreA > 10) {
								ScoreA = 10;
							}
							ScoreASum = ScoreASum + ScoreA;
						}
						//userBDeck 점수계산
						ScoreBSum=0;
						for (int i = 0; i < userBIndex; i++) {
							int ScoreB = 0;
							ScoreB = u2.getUserDeck()[i] % 13 + 1;
							if (ScoreB > 10) {
								ScoreB = 10;
							}
							ScoreBSum = ScoreBSum + ScoreB;
						}
						// A카드의 점수를 1점으로 정할것인지 11점으로 정할것인지 선택하자
						for (int i = 0; i < userAIndex; i++) {
							if (u1.getUserDeck()[i] % 13 == 0) {
								// 만약 A카드가 있다면 10을 더할텐데, 여기서 22가 넘지 않으면 10을 더해주고 넘으면 더하지 않으면 된다
								if ((ScoreASum + 10) <= 21) {
									ScoreASum = ScoreASum + 10;
								}
							}
						}
						// B카드의 점수를 1점으로 정할것인지 11점으로 정할것인지 선택하자
						for (int i = 0; i < userBIndex; i++) {
							if (u2.getUserDeck()[i] % 13 == 0) {
								// 만약 A카드가 있다면 10을 더할텐데, 여기서 22가 넘지 않으면 10을 더해주고 넘으면 더하지 않으면 된다
								if ((ScoreBSum + 10) <= 21) {
									ScoreBSum = ScoreBSum + 10;
								}
							}
						}
						// userA와 userB가 어떤 카드를 받았는지 출력해보자
						System.out.println(u1.getId()+"님의 Card");
						for (int i : u1.getUserDeck()) {
							if (i != -1) {
								// System.out.println(i + " "); A유저가 뽑은 카드 덱 번호 (출력되지 않아도 무관하므로 주석처리)
								System.out.println("카드 : " + "["+cardShape[i / cardNumbers.length] +"]"+ cardNumbers[i % cardNumbers.length]);
								System.out.println("--------------");
							}
						}
						System.out.println(" ");
						System.out.println("NPC님의 Card");
						for (int i = 0; i < userBIndex; i++) {
							// System.out.println(userBDeck[i] + " "); B유저가 뽑은 카드 덱 번호 (출력되지 않아도 무관하므로 주석처리)
							System.out.println("카드 : ["+cardShape[u2.getUserDeck()[i] / cardNumbers.length]+"]" + cardNumbers[u2.getUserDeck()[i] % cardNumbers.length]);
							System.out.println("--------------");
						}

						System.out.println("***"+u1.getId()+" 점수:" + ScoreASum);
						System.out.println("*** NPC 점수:" + ScoreBSum);

						if(ScoreASum>21) {
							System.out.println("21점을 초과했군요....");
							break;
						}
						if(ScoreBSum>21) {
							System.out.println("21점을 초과했군요....");
							break;
						}
					}


					// 승패 결정
					String playState = "계속 진행하세요 ===>";
					if (ScoreASum>21 && ScoreBSum>21) {
						playState = "무승부!";
					}else if(ScoreASum>21){
						playState = "NPC님이 승리 하셨습니다!!!";
						userMoney -=1500;
					}else if (ScoreBSum > 21) {
						playState = u1.getId()+ " 님이 승리 하셨습니다!!!";
						userMoney +=6000;
					}else if(ScoreASum > ScoreBSum) {
						playState = u1.getId()+ " 님이 승리 하셨습니다!!!"; 
						userMoney +=6000;
					}else if(ScoreBSum > ScoreASum) {
						playState = "NPC님이 승리 하셨습니다!!!"; 
						userMoney -=1500;
					}
					u1.setMoney(userMoney);
					System.out.println(playState);
					System.out.println("================================");
					System.out.println(">>> 게임을 더 하시겠습니까?");
					System.out.println(">>> 게임을 끝내시려면 종료를 입력해주세요"); 

					u1.resetUserDeck();
					//u2.resetUserDeck();

					if(sc.nextLine().equals("종료")) {
						break;
					}
				}
			}catch(NullPointerException e) {
				cls.run();
				System.out.println("없는 아이디 입니다.");
				break;
			}
		}
	}
}


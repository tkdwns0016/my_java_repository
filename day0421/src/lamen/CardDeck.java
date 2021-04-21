package lamen;

public class CardDeck {
	
	private static String[] patterns = { "♠", "♣", "◆", "♥" };
	private static String[] cardNumber = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // 카드 숫자 선언

	private static int UserIndex=0;
	int deck[] = new int[52];

	public static int getUserIndex() {
		return UserIndex;
	}

	public static void setUserIndex(int userIndex) {
		UserIndex = userIndex;
	}

	public String[] getPatterns() {
		return patterns;
	}

	public int[] getDeck() {
		return deck;
	}

	public void setDeck(int[] deck) {
		this.deck = deck;
	}

	public static void setPatterns(String[] patterns) {
		CardDeck.patterns = patterns;
	}

	public String[] getCardNumber() {
		return cardNumber;
	}

	public static void setCardNumber(String[] cardNumber) {
		CardDeck.cardNumber = cardNumber;
	}

	public CardDeck() {
		super();
		this.deck = deck;
	}


	
	
	   
}

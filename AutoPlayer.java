

public class AutoPlayer extends AbstractPlayer {

	AutoPlayer(CardPile left, CardPile right, String name) {
		super(left, right, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int selectCard() {
		int[] indextoavoid = new int[4];
		Rank[] hands = new Rank[4];
		for (int i=0; i < 4; i++){
			hands[i] = super.cards[i].getRank();
			for(int j = i-1; j > 0; j--){
				if(hands[i].equals(hands[j])){
					//count++
				}
			}
		}
	}

}
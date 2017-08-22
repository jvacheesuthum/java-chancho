

/**
 * This class implements a Deck of playing cards using a min-heap to store the
 * cards. Complete implementations of the methods: size(), isEmpty(),
 * addToBottom(Card) and removeFromTop() have been provided and should not be
 * modified.
 * 
 * The developer should implement the two Deck shuffling operations: cut() and
 * riffleShuffle(Deck).
 */
public final class MinHeapDeck implements Deck {

	private final IMinHeap<HeapEntry<Card>> heap;

	public MinHeapDeck() {
		heap = new MinHeap<HeapEntry<Card>>();
	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public void addToBottom(Card card) {
		assert card != null : "Attempting to add 'null' to a MinHeapDeck";

		heap.add(new HeapEntry<Card>(card, size()));
	}

	public Card removeFromTop() {
		assert !isEmpty() : "Attempting to remove a card from an empty MinHeapDeck.";
		return heap.removeMin().getItem();
	}

	public Deck cut() {
		assert !(heap.isEmpty());
		Deck newdeck = (Deck) new MinHeap<HeapEntry<Card>>();
		int size = heap.size() / 2;
		for (int i = 0; i < size; i++) {
			newdeck.addToBottom(((Deck) heap).removeFromTop());
		}
		return newdeck;
	}

	public Deck riffleShuffle(Deck deck) {
		int size = heap.size();
		Deck newdeck = (Deck) new MinHeap<HeapEntry<Card>>();
		for (int i = 0; i < size; i++) {
			newdeck.addToBottom(removeFromTop());
			newdeck.addToBottom(deck.removeFromTop());
		}
		for (int i = 0; i < size * 2; i++) {
			// for reversing order
			newdeck.addToBottom(newdeck.removeFromTop());
		}
		return newdeck;
	}

	@Override
	public String toString() {
		return heap.toString();
	}

}

/**
 * This class implements a min-heap abstract data type (as described by the
 * generic interface IMinHeap<T extends Comparable<T>>) using a fixed array of
 * size MinHeap.MAXIMUM_HEAP_SIZE.
 */
public class MinHeap<T extends Comparable<T>> implements IMinHeap<T> {

	private int maxsize = 52;
	private T[] heap;
	private int lastindex;

	public MinHeap() {
		heap = (T[]) new Comparable[maxsize];
		lastindex = 0;
	}

	@Override
	public void add(T element) throws HeapException {
		if (lastindex >= maxsize) {
			throw new HeapException("no more space");
		} else {
			heap[lastindex] = element;
			sortAfterAdd(lastindex);
			lastindex++;
		}
	}

	private void rebuildAfterAdd(int index) {
		if (index == 0) {
			return;
		}
		if (index % 2 == 0) {
			if (heap[(index - 2) / 2].compareTo(heap[index]) > 0) {
				T temp = heap[(index - 2) / 2];
				heap[(index - 2) / 2] = heap[index];
				heap[index] = temp;
				sortAfterAdd((index - 2) / 2);
			} else {
				return;
			}
		} else {
			if (heap[(index - 1) / 2].compareTo(heap[index]) < 0) {
				T temp = heap[(index - 1) / 2];
				heap[(index - 1) / 2] = heap[index];
				heap[index] = temp;
				sortAfterAdd((index - 1) / 2);
			} else {
				return;
			}
		}
	}

	@Override
	public T removeMin() {
		if (isEmpty()) {
			return null;
		}
		T root = heap[0];
		heap[0] = heap[lastindex];
		heap[lastindex] = null;
		sortAfterRemove(0);
		return root;
	}

	private void rebuildAfterRemove(int index) {
		if (lastindex >= index * 2 + 2) {
			int nextrootindex = heap[index * 2 + 1]
					.compareTo(heap[index * 2 + 2]) < 0 ? index * 2 + 1
					: index * 2 + 2;
			if (heap[nextrootindex].compareTo(heap[index]) < 0) {
				T temp = heap[nextrootindex];
				heap[nextrootindex] = heap[index];
				heap[index] = temp;
			} else {
				return;
			}
		} else {
			return;
		}
	}

	@Override
	public T getMin() {
		if (isEmpty()) {
			return null;
		}
		return heap[0];
	}

	@Override
	public boolean isEmpty() {
		return lastindex == 0;
	}

	@Override
	public int size() {
		return lastindex;
	}

}
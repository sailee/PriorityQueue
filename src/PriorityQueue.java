import java.util.ArrayList;


public class PriorityQueue <E extends Comparable<E>> implements Comparable<PriorityQueue<E>>{
	int maxSize, size;
	E head, tail;
	ArrayList<E> elements;

	public PriorityQueue()
	{
		this(10000);
	}

	public PriorityQueue(int maxSize)
	{ 
		if(maxSize >= 0)
		{
			this.maxSize = maxSize;
			size = 0;
			elements = new ArrayList<>(maxSize);
		}
		else throw new IllegalArgumentException("Invalid Queue size");
	}


	@Override
	public int compareTo(PriorityQueue<E> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Inserts the element e into the priority queue (in O(log n) time). 
	 * If the priority queue has already reached its maximum size, then an exception may be raised. 
	 * 
	 * @param e element to be inserted into the queue
	 * @throws Exception throws an exception if the maxSize of the queue is exceeded
	 */
	public void insert(E e) throws Exception
	{
		if(size < maxSize)
		{
			size ++;
		}
		else
			throw new Exception("Exceeds maximum size");

	}

	/**
	 * Removes the largest element from the priority queue (in O(log n) time).
	 * @return
	 */
	public E remove()
	{
		return null;
	}
	
	/**
	 * Removes all elements from the priority queue (in constant time).
	 */
	public void clear()
	{
		
	}
	
	/**
	 * Returns the head (i.e. the largest element) of the priority queue (in constant time), but does not remove it.
	 * @return
	 */
	public E head()
	{
		return head;
	}
	
	/**
	 * Checks if the queue is empty
	 * @return
	 */
	public boolean isEmpty()
	{
		return (size == 0);
	}

}

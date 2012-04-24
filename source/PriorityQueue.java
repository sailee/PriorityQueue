import java.util.ArrayList;


public class PriorityQueue <E extends Comparable<E>> implements Comparable<PriorityQueue<E>>{
	int maxSize, currentPosition;
	ArrayList<E> elements;
	private static final int DEFAULT_CAPACITY = 10000;

	public PriorityQueue()
	{
		this(DEFAULT_CAPACITY);
	}

	public PriorityQueue(int maxSize)
	{ 
		if(maxSize >= 0)
		{
			this.maxSize = maxSize;
			currentPosition = 0;
			elements = new ArrayList<E>(maxSize);			
		}
		else throw new IllegalArgumentException("Invalid Queue size");
	}


	@Override
	public int compareTo(PriorityQueue<E> o) {
		return this.head().compareTo(o.head());
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
		if(currentPosition < maxSize)
		{
			elements.add(currentPosition,e);

			int position = currentPosition++;
			int parent = parent(position);

			while(position !=0 && elements.get(position).compareTo(elements.get(parent)) > 0)
			{
				//swap elements
				E temp = elements.get(parent);
				elements.set(parent, elements.get(position));
				elements.set(position, temp);

				//change index
				position = parent(position);
				parent = parent(position);
			}

		}
		else
			throw new Exception("Exceeds maximum size");

	}

	private int parent(int position) {
		
		if(position==0)
			return 0;		
		return (int)Math.floor((position-1)/2D);
	}

	/**
	 * Removes the largest element from the priority queue (in O(log n) time).
	 * @return
	 * @throws Exception 
	 */
	public E remove() throws Exception
	{
		if( isEmpty( ) )
			throw new Exception("Invalid operation. Cannot find head of empty queue");
		else
		{			
			E returnValue = elements.get(0);			
			elements.set(0, elements.get(--currentPosition));			
			adjust(0);			
			return returnValue;
		}
	}

	private void adjust(int position) {

		if(isLeaf(position))
			return;
		else
		{
			E left = null, right = null, larger = null, current;
			int index = 0;
			
			current = elements.get(position);

			if(hasLeftChild(position))
				left = elements.get(2*position + 1);
			if(hasRightChild(position))
				right = elements.get(2*position + 2);

			if(left != null && right != null)
			{
				if(left.compareTo(right) > 0 && left.compareTo(current) > 0)
				{
					larger = left;
					index = 2*position + 1;
				}
				else if(right.compareTo(left) > 0 && right.compareTo(current) > 0)
				{
					larger = right;
					index = 2*position + 2;
				}
				else				
				{
					return;
				}
			}			
			else if(left == null && right.compareTo(current) > 0)
			{
				larger = right;
				index = 2*position + 2;
			}
			else if(right == null && left.compareTo(current) > 0)
			{
				larger = left;
				index = 2*position + 1;
			}
			else return;

			elements.set(index, current);
			elements.set(position, larger);
			adjust(index);
		}	
	}

	private boolean isLeaf(int position) {
		return (!hasLeftChild(position) && !hasRightChild(position));
	}

	private boolean hasLeftChild(int position)
	{
		return (currentPosition > 2*position + 1);
	}

	private boolean hasRightChild(int position)
	{
		return (currentPosition > 2*position + 2);
	}	

	/**
	 * Removes all elements from the priority queue (in constant time).
	 */
	public void clear()
	{
		currentPosition = 0;		
	}

	/**
	 * Returns the head (i.e. the largest element) of the priority queue (in constant time), but does not remove it.
	 * @return
	 * @throws Exception 
	 */
	public E head()
	{
		if( !isEmpty( ) )
			return elements.get(0);
		return null;
		
	}

	/**
	 * Checks if the queue is empty
	 * @return
	 */
	public boolean isEmpty()
	{
		return (currentPosition == 0);
	}
}

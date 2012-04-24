import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Test {
	private static final int DEFAULT_CAPACITY = 1000;
	//	sort: This should be a generic method parameterized by type E, where E ranges over the classes that implement the java.lang.Comparable interface 
	//(for comparing with other E's). The method should should take a List<E> as a parameter and return a List<E> containing the same elements but in sorted order. 
	//It should perform the sort by inserting every element of the input list into a priority queue (of your priority queue class) and then removing every element 
	//from the priority queue and inserting it in the appropriate place of the output list. 
	//You may choose to use any class implementing List<E> (e.g. ArrayList<E>) that you want for the return value.

	public static <E extends Comparable<E>> ArrayList<E> sort(List<E> values) throws Exception
	{
		PriorityQueue<E> priorityQueue = new PriorityQueue<E>(values.size());
		ArrayList<E> result = new ArrayList<E>(values.size());
		//System.out.println("inserting into priority queue");
		for(E element : values)
		{
			priorityQueue.insert(element);
		}
		//System.out.println("Removing from queue");

		for(int i=values.size(); i>0; i--)
		{
			result.add(0, priorityQueue.remove());
		}

		return result;

	}

	//	testInt: This procedure should perform the following:
	//			Create a List of integers in random order (using, say, Java's Random class). The list have at least 10,000 integers.
	//			Call the sort procedure, above, to create a sort version of your list.
	//			Without printing out the elements of the sorted list, check if the resulting list is sorted.

	public static void testInt() throws Exception
	{
		ArrayList<Integer> values = new ArrayList<Integer>(DEFAULT_CAPACITY);
		Random randomGenerator = new Random();
		boolean foundError = false;

		System.out.println("Creating test values");

		for(int i=0; i<DEFAULT_CAPACITY;i++)
		{
			values.add(randomGenerator.nextInt(DEFAULT_CAPACITY));
		}

		System.out.println("Sorting elements");
		ArrayList<Integer> sorted = sort(values);

		System.out.println("Verifying sort");

		for(int i=0;i<DEFAULT_CAPACITY-1;i++)
		{
			if(sorted.get(i).compareTo(sorted.get(i+1)) > 0)
			{
				System.out.println("Error at :" + i);
				System.out.println(sorted.get(i));
				System.out.println(sorted.get(i+1));

				foundError = true;
				break;
			}
		}

		if(foundError)
		{
			System.out.println("Error in Sorting");
		}
		else
		{
			System.out.println("Integer ArrayLists Sorted Successfully");
		}		
	}	

	//	testPQ: This procedure should perform the same steps as testInt, above, except that, instead of sorting a list of integers, 
	//it sorts a list of priority queues of integers. That is, you'll need to create and populate a fairly large number of priority queue objects 
	//(where the elements of each priority queue are integers), put them in a list, and then sort that list.
	public static void testPQ() throws Exception
	{
		ArrayList<PriorityQueue<Integer>> values = new ArrayList<PriorityQueue<Integer>>(DEFAULT_CAPACITY);
		Random randomGenerator = new Random();
		boolean foundError = false;

		System.out.println("\n\nGenerating priority queues");
		for(int i=0; i< DEFAULT_CAPACITY/5;i++)
		{			
			PriorityQueue<Integer> queue = new PriorityQueue<Integer>(DEFAULT_CAPACITY);

			for(int j=0; j<DEFAULT_CAPACITY;j++)
			{				
				queue.insert(randomGenerator.nextInt());
			}

			values.add(queue);
		}

		System.out.println("Sorting Priority Queues");
		ArrayList<PriorityQueue<Integer>> sorted = sort(values);

		System.out.println("Verifying sort");

		for(int i=0; i< sorted.size() - 1 ;i++)
		{
			if(sorted.get(i).compareTo(sorted.get(i+1)) > 0)
			{
				System.out.println("Error at :" + i);
				System.out.println(sorted.get(i).head());
				System.out.println(sorted.get(i+1).head());
				foundError = true;
				break;
			}
		}

		if(foundError)
		{
			System.out.println("Error in Sorting");
		}
		else
		{
			System.out.println("Sorted Successfully");
		}		
	}	

	public static void main(String args[])
	{
		try {
			testInt();
		} catch (Exception e) {
			System.out.println("Error occurred in testInt(). Details: " + e.getMessage());
		}
		try {
			testPQ();
		} catch (Exception e) {			
			System.out.println("Error occurred in testPQ(). Details: " + e.getMessage());
		}
	}
}

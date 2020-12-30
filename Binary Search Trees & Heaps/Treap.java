package Treap;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;


/**
 * @author Shreya Vhadadi
 * @CWID : 10453495
 */
public class Treap<E extends Comparable<E>> {
		

	private Random priorityGenerator = new Random(10);
	private Node<E> root;
	private Stack<Node<E>> route=new Stack<Node<E>>();
	private HashSet<Integer> prioritySet = new HashSet<>();//checks for priorities to be unique

	private static class Node<E>
	{
	
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
	
		
		/**
		 * Creates a new node with the given data and priority
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority)
		{
			 try
			 {
				this.data=data;
				this.priority=priority;
				left= null;
				right= null;
			 }
			 catch(Exception e)
			 {
					System.out.println(e);
			 }
		}
		

		/**
		 * Performs a right rotation in the Treap
		 * @return
		 */
		public Node<E> rotateRight()
		{
			Node<E> rotateNodeRight = new Node<E>(this.data,this.priority);
			if (this.left != null) 
			{
				if(this.left.right != null) 
				{
					rotateNodeRight.left=this.left.right;
				}
				if(this.right != null) 
				{
					rotateNodeRight.right = this.right;
				}
				this.data = this.left.data;
				this.priority = this.left.priority;
				this.right = rotateNodeRight;
				if(this.left.left == null)
				{
					this.left = null;
				} 
				else
				{
					this.left = this.left.left;
				}
			}
			return this.right;
		}		
		
		/**
		 * Performs a left rotation	in the Treap
		 * @return
		 */
		public Node<E> rotateLeft()
		{
			Node<E> rotateNodeLeft = new Node<E>(this.data,this.priority);
			if(this.right != null) 
			{
				if(this.right.left !=  null) 
				{
					rotateNodeLeft.right = this.right.left;
				}
				if(this.left != null) 
				{
					rotateNodeLeft.left = this.left;
				}
				this.data = this.right.data;
				this.priority = this.right.priority;
				this.left = rotateNodeLeft;
				if(this.right.right == null) 
				{
					this.right = null;
				} 
				else 
				{
					this.right = this.right.right;	
				}
			}
			return this.left;

		}
		
		
	}
	

	/**
	 * Creates an empty Treap using new Random()
	 */
	public Treap()
	{
		this.priorityGenerator=new Random();
		this.root=null;
	}
	

	/**
	 * Creates an empty Treap using new Random(seed)
	 * @param seed
	 * @throws Exception
	 */
	public Treap(long seed)throws Exception
	{
		if(seed<0)
				throw new Exception("Positive Seed Number Required");
		this.priorityGenerator=new Random(seed);
		this.root=null;
	}
	

	/**
	 * Creates an empty Treap using new Random(seed)
	 * @param key
	 * @return
	 */
	boolean add(E key)
	{
		int randomPriority=priorityGenerator.nextInt();
		while(prioritySet.contains(randomPriority)) {
			randomPriority=priorityGenerator.nextInt();
		}				
		return add(key,randomPriority);	
	}
		

	/**
	 * Inserts an element with the given key and priority
	 * @param key
	 * @param priority
	 * @return
	 */
	boolean add(E key,int priority)
	{
		if(find(key)==true || prioritySet.contains(priority))//If the priority not unique then we do not modify the heap just return false
			return false;
		Node<E> newNode=new Node<E>(key,priority);
		prioritySet.add(priority);
		if(root==null)
		{
			root=newNode;
			return true;
		}
		
		Node<E> rootNode=root;
		while(rootNode!=null)
			
		{
			route.push(rootNode);
			if(key.compareTo(rootNode.data)<0)
			{
				rootNode=rootNode.left;
			}
			else
			{
				rootNode=rootNode.right;
			}
		}
		
		if(key.compareTo(route.peek().data)<0)
		{
			route.peek().left=newNode;
		}
		else
		{
			route.peek().right=newNode;
		}
		reheap(newNode);
		return true;
	}
	
	

	/**
	 * Helper function to restore the heap invariant 
	 * @param newNode
	 */
	private void reheap(Node<E> newNode)
	{
		
		Node<E> currentTop=route.pop();
		while(currentTop!=null && currentTop.priority < newNode.priority) 
		{
			
			if(newNode.data.compareTo(currentTop.data)<0) 
			{
				currentTop.rotateRight();
				if(route.isEmpty())
				{
					return;
				}
				currentTop=route.pop();
			}
			else 
			{
				currentTop.rotateLeft();
				if(route.isEmpty()) 
				{
					return;
				}
				currentTop=route.pop();
			}
			
		}
	}
		

	/**
	 * Deletes the node with the given key 
	 * @param key
	 * @return
	 */
	boolean delete(E key)
	{
		if(find(key)!=true)//If key not found just returning false
			return false;
		else if(root.data.compareTo(key) == 0 && root.left == null && root.right==null)
        {
        	prioritySet.remove(root.priority);
        	root = null;
        	return true;
        }
		else 
		{
			return deleteHelper(root , key);	
		}
	}
	
	
	/**
	 * Deletes the node with the given key 
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean deleteHelper(Node<E> root,E key)
	{
		Node<E> rootNode=root;//Basically the element to be deleted
		Node<E> deleteNode = null;
        while(rootNode.data.compareTo(key) != 0)
        {
        	deleteNode=rootNode;
            if(rootNode.data.compareTo(key) > 0) 
            {
            	rootNode=rootNode.left;
            }
            else 
            {
            	rootNode=rootNode.right;
            }
        }
		prioritySet.remove(rootNode.priority);
        while(!(rootNode.left == null && rootNode.right == null)) 
        {
        	deleteNode=rootNode;
        	if(rootNode.left == null) 
        	{
        		rootNode=rootNode.rotateLeft();	        	
        	}
        	else if(rootNode.right == null) 
        	{
        		rootNode=rootNode.rotateRight();
        	}
        	else if(rootNode.left.priority>rootNode.right.priority) 
        	{
        		rootNode=rootNode.rotateRight();
        	}
        	else 
        	{
        		rootNode=rootNode.rotateLeft();
        	}
        }
        if((deleteNode.right != null && deleteNode.right.data.compareTo(key)==0))
        {
        	deleteNode.right = null;
        }
        else if(deleteNode.left != null && deleteNode.left.data.compareTo(key)==0) 
        {
        	deleteNode.left = null;
        }
        return true;
	}
	

	/**
	 * Finds the node with Root and Key provided
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node<E> root,E key)
	{
		if(root==null) {
			return false;
		}
		if(root.data==key) {
			return true;
		}
		else if(root.data.compareTo(key)<0) {
			return find(root.right,key);
		}
		else {
			return find(root.left,key);
		}

	}

	/**
	 * Finds the node with only Key provided
	 * @param key
	 * @return
	 */
	public boolean find(E key)
	{
		return find(root,key);
	}
	
	

	/**
	 * Preorder Traversal of the Treap
	 * @param rootNode
	 * @param depth
	 * @param sb
	 */
	private void preorderTraversal(Node<E> rootNode,int depth,StringBuilder sb)
	{
		for(int i=0;i<depth;i++)
		{
			sb.append("  ");
		}
		if(rootNode==null)
		{
			sb.append("null\n");
		}
		else
		{
			sb.append("(key="+rootNode.data.toString()+" , priority="+rootNode.priority+")\n");
			preorderTraversal(rootNode.left,depth+1,sb);
			preorderTraversal(rootNode.right,depth+1,sb);
		}
	}
	

	/**
	 * Converts and returns Treap as a string
	 */
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		preorderTraversal(root,1,sb);
		return sb.toString();
	}



public static void main(String[] args) {
	// TODO Auto-generated method stub
	Treap<Integer> testTree = new Treap <Integer>(); 
//	try {
//	Treap<Integer> testTree1 = new Treap <Integer>(-3);    
//	}
//	catch(Exception e) {
//			System.out.println("Caught Exception successfully "+e);
//	
//	}
	  testTree.add(4,19); 
	  testTree.add(2,31);
	  testTree.add(6,70); 
	  testTree.add(1,84);
	  testTree.add(3,12); 
	  testTree.add(5,83);
	  testTree.add(7,26);	
	String string=testTree.toString();
	System.out.println(string);
	
//	  testTree.add(7,7); 
//	  testTree.add(2,2);
//	  testTree.add(4,8);
//	  testTree.add(3,9); 
	  //testTree.add(5);
	  String string1=testTree.toString();
	  System.out.println(string1);
	  //testTree.delete(4);
	  testTree.delete(5); 
	  String string2=testTree.toString();
	  System.out.println(string2);
//	  testTree.add(9,11); 
//	  testTree.add(9,12); 
//	  String string3=testTree.toString();
//	  System.out.println(string3);
//	  testTree.add(5,-4); 
//	  String string4=testTree.toString();
//	  System.out.println(string4);
//	  testTree.add(6,9); 
//	  String string5=testTree.toString();
//	  System.out.println(string5);
//		if(testTree.find(5))
//		{
//			System.out.println("Found!");
//		}
//		else
//		{
//			System.out.println("Not found!");
//		}


	if(testTree.find(5))
	{
		System.out.println("Found!");
	}
	else
	{
		System.out.println("Not found!");
	}
//	testTree.delete(4);
//	testTree.delete(3);
//	testTree.delete(2);
//	testTree.delete(7);
//	testTree.delete(5);
//	String str=testTree.toString();
//	System.out.println(str);
//	Treap<Integer> testTree1 = new Treap <Integer>();
//	String str2=testTree1.toString();
//	System.out.println(str2);
//	Treap<Integer> testTree = new Treap<Integer>();
//	testTree.add (4 ,19);
//	testTree.add (2 ,31);
//	testTree.add (6 ,70);
//	testTree.add (1 ,84);
//	testTree.add (3 ,12);
//	testTree.add (5 ,83);
//	testTree.add (7 ,26);
//	//System.out.println(testTree.toString());
//	testTree.delete(1);
//	testTree.delete(7);
//	testTree.delete(4);
//	testTree.delete(6);
//	testTree.delete(3);
//	testTree.delete(5);
//	testTree.delete(2);
//	System.out.println(testTree.find(5));
//	System.out.println(testTree.toString());
	

}
}
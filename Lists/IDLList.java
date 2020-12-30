/**
 * 
 */
/**
 * @author shreyavhadadi
 *
 */
//Shreya Vhadadi(10453495)
package DLL;
import java.util.*;

public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//Inner class Node<E> 
	private static class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		
		Node (E elem){
			data=elem;
			this.next=null;
			this.prev=null;
		
		}
		Node (E elem, Node<E> prev, Node<E> next){
			data=elem;
			this.prev=prev;
			this.next=next;
		}
	
	}
	
	//Creates an empty double-linked list
	public IDLList () {
		this.head=null;
		this.tail=null;
		this.size=0;
		this.indices=new ArrayList<>();
		
	}
	
	//Adds element at position index
	public boolean add (int index, E elem) {
		boolean flag=false;
		if(index<0 || index>size) {
			throw new ArrayIndexOutOfBoundsException("Invalid index");
		}
		
		if(head==null) {
			Node<E> newnode = new Node<>(elem);
			head=newnode;
			tail=newnode;
			indices.add(index,newnode);
			size++;
			flag=true;
		}
		else if(index==0) {//size is 1 or more
			Node<E> newnode = new Node<>(elem,null,head);
			head.prev=newnode;
			head=newnode;
			indices.add(index,newnode);
			size++;
			flag=true;
		}
		else if(index>0 && index<size) {//size is 1 or more
			Node<E> prevnode=indices.get(index-1);
			Node<E> nextnode=indices.get(index);
			Node<E> newnode = new Node<>(elem,prevnode,nextnode);
			prevnode.next=newnode;
			nextnode.prev=newnode;
			indices.add(index,newnode);
			size++;
			flag=true;
		}
		else if(index==size){//size is 1 or more
			Node<E> newnode = new Node<>(elem,tail,null);
			tail.next=newnode;
			tail=newnode;
			indices.add(index,newnode);
			size++;
			flag=true;
			
		}

		return flag;
	}
	
	//Adds element at the head
	public boolean add (E elem) {
		boolean flag=false;
		if(size==0) {
			Node<E> headnode = new Node<>(elem);
			this.head=headnode;
			this.tail=headnode;
			size++;
			indices.add(0,headnode);
			flag=true;
		}
		else {
			Node<E> headnode=new Node<>(elem,null,head);
			head.prev=headnode;
			head=headnode;
			size++;
			indices.add(0,headnode);
			flag=true;
		}
		return flag;
	}
	
	//Adds element as the new last element of the list
	public boolean append (E elem) {
		boolean flag=false;
		if(size==0) {
			Node<E> lastnode=new Node<>(elem);
			head=lastnode;
			tail=lastnode;
			indices.add(lastnode);
			size++;
			flag=true;
		}
		else {
			Node<E> lastnode=new Node<>(elem,tail,null);
			tail.next=lastnode;
			tail=lastnode;
			indices.add(lastnode);
			size++;
			flag=true;
		}
		return flag;
	}
	
	//Returns the object at position index
	public E get (int index) {
		if(index<0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException("Invalid index");
		}
		Node<E> node=indices.get(index);
		return node.data;
	}
	
	//Returns the object at the head
	public E getHead () {
		if(size==0) {
			throw new NoSuchElementException("List is empty");
		}
		Node<E> node=indices.get(0);
		return  node.data;
	}
	
	//Returns the object at the tail
	public E getLast () {
		if(size==0) {
			throw new NoSuchElementException("List is empty");
		}
		Node<E> node=indices.get(size-1);
		return node.data;
	}
	
	//Returns the list size
	public int size () {
		return size;
	}
	
	//Removes and returns the element at the head
	public E remove () {
		Node<E> removehead;
		if(size==0) {
			throw new NoSuchElementException("No elements to be removed");
		}
		if(size==1) {
			removehead=indices.get(0);
			head=null;
			tail=null;
			indices.remove(0);
			size--;
			
		}
		else {
			removehead=indices.get(0);
			head=removehead.next;
			head.prev=null;
			indices.remove(0);
			size--;
		}
		return removehead.data;
	}
	
	//Removes and returns the element at the tail
	public E removeLast () {
		Node<E> removelast;
		if(size==0) {
			throw new NoSuchElementException("No elements to be removed");
		}
		if(size==1) {
			removelast=indices.get(0);
			head=null;
			tail=null;
			indices.remove(0);
			size--;
		}
		else {
			removelast=indices.get(size-1);
			tail=removelast.prev;
			tail.next=null;
			indices.remove(size-1);
			size--;
		}
		return removelast.data;
		
	}
	
	//Removes and returns the element at the index
	public E removeAt (int index) {
		Node<E> removeIdxNode;
		if(index<0 || index>=size) {
			throw new ArrayIndexOutOfBoundsException("Invalid index");
		}
		if(size==1) {
			removeIdxNode=indices.get(index);
			head=null;
			tail=null;
			indices.remove(index);
			size--;
		}
		else {
			if(index==0) {
				removeIdxNode=indices.get(index);
				head=removeIdxNode.next;
				head.prev=null;
				indices.remove(index);
				size--;
			}
			else if(index==size-1){
				removeIdxNode=indices.get(index);
				tail=removeIdxNode.prev;
				tail.next=null;
				indices.remove(index);
				size--;
			}
			else {
				removeIdxNode=indices.get(index);
				Node<E> prevnode=indices.get(index-1);
				Node<E> nextnode=indices.get(index+1);
				prevnode.next=nextnode;
				nextnode.prev=prevnode;
				indices.remove(index);
				size--;
			}
		}
		return removeIdxNode.data;
	}
	
	//Removes the first occurrence of the element
	public boolean remove (E elem) {
		boolean flag=false;
		int indexEle=0;
		Node<E> removeEle;
		
		if(size==0) {
			throw new NoSuchElementException("List is empty");
		}
		for(int i=0;i<size;i++) {
			Node<E> node=indices.get(i);
			if(node.data.equals(elem)) {
				indexEle=i;
				flag=true;
				break;
			}
		}
		if(!flag) {
			return flag;
		}
		if(size==1) {
			removeEle=indices.get(indexEle);
			head=null;
			tail=null;
			indices.remove(indexEle);
			size--;
		}
		else {
			if(indexEle==0) {//remove
				removeEle=indices.get(indexEle);
				head=removeEle.next;
				head.prev=null;
				indices.remove(indexEle);
				size--;
			}
			else if(indexEle==size-1){//remove last
				removeEle=indices.get(indexEle);
				tail=removeEle.prev;
				tail.next=null;
				indices.remove(indexEle);
				size--;
			}
			else {
				removeEle=indices.get(indexEle);
				Node<E> prevnode=indices.get(indexEle-1);
				Node<E> nextnode=indices.get(indexEle+1);
				prevnode.next=nextnode;
				nextnode.prev=prevnode;
				indices.remove(indexEle);
				size--;
			}
		}
		return flag;
	}
	
	//Presents a string representation of the list
	@Override
	public String toString() {
		String strlist="";
		for(int i=0;i<size;i++) {
			Node<E> stringnode=indices.get(i);
			strlist=strlist+stringnode.data+"";
		}
		return strlist;
	}		
}

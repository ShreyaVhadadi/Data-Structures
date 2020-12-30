/**
 * 
 */
/**
 * @author shreyavhadadi
 *
 */
//Shreya Vhadadi(10453495)
package DLL;

public class IDLListTest {
	public static void main(String args[]) {
		IDLList<Character> list=new IDLList<>();
		IDLList<Character> list1=new IDLList<>();
		IDLList<Character> list2=new IDLList<>();
		IDLList<Character> list3=new IDLList<>();
		
		//Adding the elements using index(valid case)
		list.add(0,'a');
		list.add(1,'b');
		list.add(2,'c');
		list.add(3,'d');
		list.add(4,'e');
		System.out.println(list);
		
		//Adding the elements using index(invalid case)
		try {
			list.add(-1,'x');
		}
		catch(Exception e){
			System.out.println("Caught Exception successfully "+e);
		}
		
		try {
			list.add(6,'x');
		}
		catch(Exception e){
			System.out.println("Caught Exception successfully "+e);
	
		}
	
		
		//Adding element at the head
		list.add('1');
		System.out.println(list);		
		list.add(0,'x');//shifting head element		
		System.out.println(list);		
		
		System.out.println(list1.add('1'));//empty list 
		System.out.println(list1);
		
		
		//Adding element at the last
		list.append('z');//non empty append
		System.out.println(list);
		System.out.println(list2.append('q'));//empty append
		System.out.println(list2);
		
		
		//Getting object at position index(valid case)
		System.out.println(list);
		System.out.println(list.get(0));
		
		//Getting object at position index(invalid case)
		try {
			System.out.println(list.get(-1));
		}
		catch(Exception e){
			System.out.println("Caught Exception successfully "+e);
		}
		
		try {
			System.out.println(list.get(8));
		}
		catch(Exception e){
			System.out.println("Caught Exception successfully "+e);
		}
		
		//Getting the object at head(valid case)
		System.out.println(list);
		System.out.println(list.getHead());
	
		//Getting the object at head(invalid case)
		System.out.println(list3);
		try {
		System.out.println(list3.getHead());
		}
		catch(Exception e) {
			System.out.println("Caught Exception successfully "+e);
		}
		
		//Getting the object at tail(valid case)
		System.out.println(list);
		System.out.println(list.getLast());
		System.out.println(list1);
		System.out.println(list1.getLast());
		System.out.println(list3);
		
		//Getting the object at tail(invalid case)
		try {
		System.out.println(list3.getLast());
		}
		catch(Exception e) {
			System.out.println("Caught Exception successfully "+e);
		}
		
		//Getting the list size
		System.out.println(list.size());
		System.out.println(list1.size());
		System.out.println(list3.size());
		
		
		//Removing the element at the head(valid case)
		System.out.println(list);
		System.out.println(list.remove());
		System.out.println(list);
		
		//Removing the element at the head(invalid case)
		System.out.println(list3);
		try {
			System.out.println(list3.remove());
		}
		catch(Exception e) {
			System.out.println("Caught Exception successfully "+e);
		}
		
		//Removing the element at the tail(valid case)
		System.out.println(list);
		System.out.println(list.removeLast());
		System.out.println(list);
		
		//Removing the element at the tail(invalid case)
		System.out.println(list3);
		try {
			System.out.println(list3.removeLast());
		}
		catch(Exception e) {
			System.out.println("Caught Exception successfully "+e);
		}
		
		//Removing the element at the index(valid case)
		System.out.println(list);
		System.out.println(list.removeAt(2));
		System.out.println(list);

		
		//Removing the element at the index(invalid case)
		try {
			System.out.println(list.removeAt(-1));
		}
		catch(Exception e) {
			System.out.println("Caught Exception successfully "+e);
		}
		
		try {
			System.out.println(list.removeAt(5));
		}
		catch(Exception e) {
			System.out.println("Caught Exception successfully "+e);
		}
		
		
		//Removing the first occurrence of the element(valid case)
		System.out.println(list);
		System.out.println(list.remove('a'));//element exist
		System.out.println(list);
		System.out.println(list.remove('b'));//element does not exist
		System.out.println(list);
		list.add(1,'c');
		System.out.println(list);
		System.out.println(list.remove('c'));//removing the 1st occurrence of repeating elements
		System.out.println(list);

		//Removing the first occurrence of the element(invalid case)
		System.out.println(list3);//removing from empty list
		try {
			System.out.println(list3.remove('a'));
		}
		catch(Exception e) {
			System.out.println("Caught Exception successfully "+e);
		}
		
		//Getting the string representation of list
		System.out.println(list.toString());
		System.out.println(list1.toString());
		System.out.println(list2.toString());
		System.out.println(list3.toString());
				
	}

}

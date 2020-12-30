/**
 * 
 */
/**
 * @author shreyavhadadi
 *
 */
//Shreya Vhadadi(10453495)
package Anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes;
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	public Anagrams () //Constructor
	{
		primes=new Integer[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
				 67, 71, 73, 79, 83, 89, 97, 101};
		letterTable = new HashMap<>();
		buildLetterTable();
		anagramTable = new HashMap<>();
	}
	
	/**
	 * Builds a Letter Table for hashing
	 */
	private void buildLetterTable() {
		Character[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for(int i=0;i<26;i++) {
			letterTable.put(letters[i], primes[i]);
		}
	}
	
	/**
	 * Method for storing anagrams
	 * @param s
	 */
	private void addWord(String s) {
		if(s==null || s.length()==0) {
			throw new IllegalArgumentException("Invalid String");
		}
		Long hash=myHashCode(s);
		if(!anagramTable.containsKey(hash)) {
			anagramTable.put(hash,new ArrayList<>());
		}
		anagramTable.get(hash).add(s);
	}
	
	/**
	 * To compute the hash code for given a string
	 * @param s
	 * @return
	 */
	private Long myHashCode(String s) {
		if(s==null || s.length()==0) {
			throw new IllegalArgumentException("Invalid String");
		}
		char[] characters=s.toLowerCase().toCharArray();
		Long value=(long)1;
		for(int i=0;i<characters.length;i++) {		
			value=value*letterTable.get(characters[i]);
		}
		return value;
	}
	
	/**
	 * Method reads and processes the input file
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream( s );
		BufferedReader br = new BufferedReader ( new InputStreamReader( fstream ));
		String strLine ;
		while (( strLine = br. readLine ()) != null ) {
		this.addWord ( strLine );
		}
		br.close ();
	}
	
	/**
	 * Gets the entry with the most anagrams
	 * @return
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){//only max entries 
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntry=new ArrayList<>();
		int max=0;
		for(Map.Entry<Long,ArrayList<String>> entry:anagramTable.entrySet()) {
			if(entry.getValue().size()>max) {
				maxEntry.clear();
				max=entry.getValue().size();
				maxEntry.add(entry);
			}
			else if(entry.getValue().size() < max)
			{
				continue;
			}
			else
			{
				maxEntry.clear();
				maxEntry.add(entry);
			}
			
		}
		return maxEntry;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime ();
		try {
		a.processFile("words_alpha.txt");
		} catch ( IOException e1 ) {
		e1.printStackTrace ();
		}
		ArrayList < Map.Entry < Long , ArrayList < String >>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime () - startTime;
		final double seconds = (( double ) estimatedTime / 1000000000 ) ;
		System.out.println ( " Elapsed Time : "+ seconds );
		System.out.println ( " Key of max anagrams : "+ maxEntries.get(0).getKey());
		System.out.println ( " List of max anagrams : "+ maxEntries.get(0).getValue());
		System.out.println ( " Length of list of max anagrams : "+ maxEntries.get(0).getValue().size());
	}

}

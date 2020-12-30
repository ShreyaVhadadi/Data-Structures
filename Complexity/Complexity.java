/**
 * 
 */
/**
 * @author shreyavhadadi
 *
 */
//Shreya Vhadadi(10453495)
package Complexity;

public class Complexity {
	private static int counterexp=0;
	
	//Has time complexity O(n)
	public static void method0(int n) { 
		int counter=0;
		for (int i=0; i<n; i++) 
		{
		System.out.println("Operation "+counter); counter++;
		}
	}
	
	//Has time complexity O(n2)
	 public static void method1(int n) {
		int counter=0;
		for (int i=0; i<n; i++)
		{
			for(int j=0;j<n;j++) 
			{
				counter++;
				System.out.println("Operation "+counter);
			}
		}
	 }
	 
	//Has time complexity O(n3)
	 public static void method2(int n) {
		int counter=0; 
		for (int i=0; i<n; i++)
		{
			for(int j=0;j<n;j++) 
			{
					for(int k=0;k<n;k++) 
					{
						counter++;
						System.out.println("Operation "+counter); 
					}
			}
		}
	 }
	 
	//Has time complexity O(logN)
	 public static void method3(int n) {
		 int counter=0;
		 for(int i=1;i<n;i*=2) {
			 counter++;
			 System.out.println("Operation "+counter); 
		 }
	 }	 
	 
	//Has time complexity O(NlogN)
	 public static void method4(int n) {
		 int counter=0;
		 for(int i=0;i<n;i++) {
			 for(int j=1;j<n;j*=2) {
				 counter++; 
				 System.out.println("Operation "+counter); 
			 }
		 }
		 
	 }
	 
	//Has time complexity O(log(logN))	 
	 public static void method5(int n) {
		 int counter=0;
		 for(int i=n;i>0;i/=2) {
			 counter++; 
			 System.out.println("Operation "+counter); 
		 }
	 }
	 
	//Has time complexity O(2^n)		 
	 public static int method6(int n) {
		 counterexp++;
		 System.out.println("Operation "+counterexp);
		 if(n<=1) {
			 return n;
		 }
		 
		return method6(n-2)+method6(n-1);		
		 
	 }
	 
}

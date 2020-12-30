/**
 * 
 */
/**
 * @author shreyavhadadi
 *
 */
//Shreya Vhadadi(10453495)
package Binary;

import java.util.Arrays;

public class BinaryNumber 
{
	private int data[]; 
	private boolean overflow;
	
	//CODE IS IMPLIMENTED USING THE LITTLE ENDIAN FORMAT
	
	//Constructor consisting only of zeros
	public BinaryNumber(int length) throws Exception
	{
		if(length <1) 
		{
			throw new Exception("The length cannot be zero or less than zero");
		}
		this.data = new int[length];
	}
	
	//Constructor for converting a string of Binary Number to a Numeric Array
	public BinaryNumber(String str)throws Exception  
	{
		if(str==null || str.length()==0) 
		{
			throw new Exception("Invalid String Entered");
		}
		data = new int[str.length()];
		for(int i=0;i<str.length();i++) 
		{
			this.data[i]=Character.getNumericValue(str.charAt(i));
		}
	}
	
	//Method to find length of a Binary Number
	public int getLength() 
	{
		return this.data.length;
	}
	
	//Method to find the value at a specific position in a Numeric Array
	public int getDigit(int index)throws Exception 
	{
		if(index<0 || index>=this.data.length) 
		{
			throw new Exception("Entered index is out of bounds");
		}
		return this.data[index];
	} 
	
	//Method to shift right a specific number of places the digits of a Binary Number
	public void shiftR(int amount) 
	{
		int size=data.length+amount;
		int j=data.length-1;
		int[] shift= new int[size];
		while(j>=0) 
		{
			shift[j+amount]=data[j];
			j--;
		}
		this.data=Arrays.copyOf(shift,size);
			
	}
	
	//Method to add two Binary Numbers
	public void add(BinaryNumber aBinaryNumber)throws Exception 
	{
		if(this.data.length!=aBinaryNumber.getLength()) 
		{
			throw new Exception("The length of the Binary Numbers are different");
		}
		int size=this.data.length;
		BinaryNumber sum = new BinaryNumber(size);
		int carry=0;
		for(int i=0;i<size;i++) 
		{
			sum.data[i]=this.data[i]+aBinaryNumber.data[i]+carry;
			if(sum.data[i]>1) 
			{
				carry=sum.data[i]/2;
			}
			else 
			{
				carry=0;
			}
			sum.data[i]=sum.data[i]%2;

		}
		if(carry!=0) 
		{
			overflow=true;
		}
		this.data=sum.data.clone();	
		
	}
	
	//Method for transforming a binary number to a String
	public String toString() 
	{
		if(overflow==true) 
		{
			return "Overflow";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<data.length;i++) 
		{
			sb.append(data[i]);
		}
		return sb.toString();

	}
	
	//Method to convert a Binary Number to its Decimal Equivalent
	public int toDecimal() 
	{
		int sum=0;
		for(int i=0;i<data.length;i++) 
		{
			sum+=data[i]*(Math.pow(2, i));
		}
		return sum;
	}
	
	//Method that clears the overflow flag
	public void clearOverflow() 
	{
		overflow=false;
		System.out.println("The Overflow Flag is set to False");
	}
}

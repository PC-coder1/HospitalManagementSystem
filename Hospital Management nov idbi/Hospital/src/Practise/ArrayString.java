package Practise;

import java.util.Arrays;

public class ArrayString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          String str = "Hello World";
          StringBuilder reversed = new StringBuilder(str).reverse();
          System.out.println(reversed);
          
          
          String str1 = "Hello World";
          String reversed1 = new StringBuilder(str1).reverse().toString();
          System.out.println(str.equals(reversed1));
          
          int num = 111;
          boolean isPrime = true;
          for(int i=2; i<=Math.sqrt(num); i++) {
			  if(num % i == 0) {
				  isPrime = false;
				  break;
			  }
		  }
          System.out.println(isPrime);
          
          {
        	  System.out.println(false);
          }
          
          int [] arr = {5,8,3,2,9};
          Arrays.sort(arr);
          System.out.println(Arrays.toString(arr));
          
          
         int a=5;
         int b=10;
         
         
          a= a+b;
          b= a-b;
         a= a-b;
         System.out.println(a);
         System.out.println(b);
         
         
         int n=5; int factorial =1;
         
         for(int i=1; i<=n; i++)
         {
        	 factorial = factorial * i;
			 System.out.println(factorial);
         }
         }
         
	}
	



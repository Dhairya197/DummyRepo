package com.java.OOPSConcept;

import java.util.Scanner;

public class bouquet {

	
	public void cost(int r, int l, int j)
	{
		int sum= (r*1)+(j*2)+(l*3);
		System.out.println("The cost of Bouquet is :" + sum +"$");
	}
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
	bouquet br=new bouquet();
	System.out.println("Enter the number of roses");
	int r=sc.nextInt();
	System.out.println("Enter the number of jasmine");
	int j=sc.nextInt();
	System.out.println("Enter the number of Lily");
	int l=sc.nextInt();

	br.cost(r, l, j);
	
	}

}

package com.java.OOPSConcept;

public class PalindromeString {

		 
	  public static void main(String[] args) {
	 
	        String inputString = "madam";
	        String reversedString = reverseString(inputString);
	 
	        if (inputString.equals(reversedString)) {
	            System.out.println("The input String '" + inputString + "' is a palindrome String.");
	        } else {
	            System.out.println("The input String '" + inputString + "' is not a palindrome String.");
	        }
	    }
	 
	    // a method to reverse the string
	    public static String reverseString(String str) {
	        String reversedString = "";
	 
	        for (int i = str.length() - 1; i >= 0; i--) {
	            reversedString += str.charAt(i);
	 
	        }
	        return reversedString;
	    }
	 
	}


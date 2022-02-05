package com.java.DesignPattern;

public class SingletonPattern {

	private static SingletonPattern obj=new SingletonPattern();//Early, instance will be created at load time  
	 private SingletonPattern(){}  
	   
	 public static SingletonPattern getInstance(){  
	  return obj;  
	 }  
	  
	 public void doSomething(){  
	
		 System.out.println("Implementing Singleton Pattern");
	 }  
	 public static void main(String[] args)
	 {
		 SingletonPattern ob1=SingletonPattern.getInstance();
		 SingletonPattern ob2=SingletonPattern.getInstance();
		 System.out.println(ob1.hashCode() == ob2.hashCode());
	 }
}

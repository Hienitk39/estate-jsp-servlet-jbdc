package com.laptrinhjavaweb.entity;

public class Dog {
	private int puppyAge;
	public Dog (String name) {
		 System.out.println("Name chosen is :" + name );
	}
	  public void setAge( int age ) {
	      puppyAge = age;
	   }

	   public int getAge( ) {
	      System.out.println("Puppy's age is :" + puppyAge );
	      return puppyAge;
	   }
}

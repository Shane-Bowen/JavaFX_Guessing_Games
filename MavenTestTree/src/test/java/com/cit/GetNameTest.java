package com.cit;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetNameTest 
{
	@Test
	public void test() {
		Tree test = new Tree();
		test.readFile();
		String output = test.getName(0, Tree.fourList);
		assertEquals("Apple", output);
	}
}	

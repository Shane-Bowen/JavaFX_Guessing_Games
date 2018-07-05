package com.cit;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetPrizeTest {

	@Test
	public void test() {
		Tree test = new Tree();
		test.readFile();
		String output = test.getPrize(2, Tree.sixList);
		assertEquals("trip to Majorca", output);
		
	}

}

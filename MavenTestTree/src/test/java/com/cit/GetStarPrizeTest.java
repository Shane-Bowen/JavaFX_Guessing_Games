package com.cit;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetStarPrizeTest {

	@Test
	public void test() {
		Tree test = new Tree();
		test.readFile();
		int output = test.getKey(0, Tree.fiveList);
		assertEquals(5, output);
	}

}

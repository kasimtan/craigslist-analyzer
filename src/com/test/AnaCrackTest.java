package com.test;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.analysis.control.AnaCrack;

/**
 * 
 * @author mschimpf
 *
 */
public class AnaCrackTest {
	static Logger logger = Logger.getLogger(CrawlTest.class);

	@Test
	public void test_createAnaCrack() {
		System.out.println("Create AnaCrack class...");
		AnaCrack c = new AnaCrack();
		assertTrue(c != null);
	}
}

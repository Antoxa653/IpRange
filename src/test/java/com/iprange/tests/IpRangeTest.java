package com.iprange.tests;

import org.junit.Test;

import com.iprange.IpRange;

public class IpRangeTest {

	@Test(expected = IllegalArgumentException.class)
	public void testOverflowParam() {
		String testArray1 = "-1.0.0.0";
		String testArray2 = "-2.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(testArray1, testArray2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpMoreThenSecondIp() {
		String testArray1 = "0.0.0.1";
		String testArray2 = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(testArray1, testArray2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpEqualsSecondIp() {
		String testArray1 = "0.0.0.0";
		String testArray2 = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(testArray1, testArray2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpBadFormat() {
		String testArray1 = "0.0.0";
		String testArray2 = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(testArray1, testArray2);
	}

	@Test(expected = NullPointerException.class)
	public void testNullIpArrays() {
		String testArray1 = null;
		String testArray2 = null;
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(testArray1, testArray2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidSeparator() {
		String testArray1 = "0-0-0-1";
		String testArray2 = "0-0-0-0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(testArray1, testArray2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpCharacters() {
		String testArray1 = "a.b.c.d";
		String testArray2 = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(testArray1, testArray2);
	}
}

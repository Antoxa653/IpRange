package com.iprange.tests;

import org.junit.Test;

import com.iprange.IpRange;

public class IpRangeTest {

	@Test(expected = IllegalArgumentException.class)
	public void testOverflowParam() {
		String firstIp = "-1.0.0.0";
		String secondIp = "-2.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(firstIp, secondIp);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpMoreThenSecondIp() {
		String firstIp = "0.0.0.1";
		String secondIp = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(firstIp, secondIp);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpEqualsSecondIp() {
		String firstIp = "0.0.0.0";
		String secondIp = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(firstIp, secondIp);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpBadFormat() {
		String firstIp = "0.0.0";
		String secondIp = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(firstIp, secondIp);
	}

	@Test(expected = NullPointerException.class)
	public void testNullIpArrays() {
		String firstIp = null;
		String secondIp = null;
		IpRange ipRange = new IpRange();		
		ipRange.proccessIpRange(firstIp, secondIp);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidSeparator() {
		String firstIp = "0-0-0-1";
		String secondIp = "0-0-0-0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(firstIp, secondIp);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFirstIpCharacters() {
		String firstIp = "a.b.c.d";
		String secondIp = "0.0.0.0";
		IpRange ipRange = new IpRange();
		ipRange.proccessIpRange(firstIp, secondIp);
	}
}

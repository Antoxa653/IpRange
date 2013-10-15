package com.iprange.tests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

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
	
	@Test
	public void testValidIps() {
		String testArray1 = "0.0.0.0";
		String testArray2 = "0.0.0.10";
		List<String> expectedResultList = new LinkedList();
		expectedResultList.add("0.0.0.1");
		expectedResultList.add("0.0.0.2");
		expectedResultList.add("0.0.0.3");
		expectedResultList.add("0.0.0.4");
		expectedResultList.add("0.0.0.5");
		expectedResultList.add("0.0.0.6");
		expectedResultList.add("0.0.0.7");
		expectedResultList.add("0.0.0.8");
		expectedResultList.add("0.0.0.9");
		IpRange ipRange = new IpRange();
		assertEquals("Result must be:", expectedResultList, ipRange.proccessIpRange(testArray1, testArray2));
		ipRange.proccessIpRange(testArray1, testArray2);
	}

}

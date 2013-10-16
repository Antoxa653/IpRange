package com.iprange;


public class Loader {

	public static void main(String[] args) {
		IpRange ipRange = new IpRange();
		String test1String = "0.0.0.0";
		String test2String = "0.0.2.0";
		ipRange.proccessIpRange(test1String, test2String);
	}
}

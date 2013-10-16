package com.iprange;

import java.util.Scanner;


public class Loader {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter start IP:");
		String test1String = in.nextLine();
		System.out.println("Enter end IP:");
		String test2String = in.nextLine();
		IpRange ipRange = new IpRange();		
		ipRange.proccessIpRange(test1String, test2String);
	}
}

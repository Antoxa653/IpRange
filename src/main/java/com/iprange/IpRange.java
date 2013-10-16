package com.iprange;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class IpRange {
	private Logger log = Logger.getLogger(getClass());

	public void proccessIpRange(String ip1, String ip2) {
		if (ipsShouldBeNotNull(ip1, ip2) & validateInputIps(ip1, ip2)) {
			String[] array1String = ip1.split("\\.");
			String[] array2String = ip2.split("\\.");
			int[] array1 = new int[4];
			int[] array2 = new int[4];
			for (int i = 0; i < array1String.length; i++) {
				array1[i] = Integer.parseInt(array1String[i]);
				array2[i] = Integer.parseInt(array2String[i]);
			}
			if (isFirstIpLessThenSecond(array1, array2) & !Arrays.equals(array1, array2)) {

				while (!Arrays.equals(array1, array2)) {
					array1[array1.length - 1]++;
					for (int i = array1.length - 1; i > 0; i--) {
						if (array1[i] > 255) {
							array1[i] = 0;
							array1[i - 1]++;
						}
						else {
							break;
						}
					}
					if (!Arrays.equals(array1, array2)) {
						printIp(array1);
					}
				}
			}
		}
	}

	public boolean isFirstIpLessThenSecond(int[] ip1Array, int[] ip2Array) {
		long ip1Size = 0;
		long ip2Size = 0;
		long multiplier = 1;
		int range = 256;

		for (int i = ip1Array.length - 1; i >= 0; i--) {
			ip1Size += ip1Array[i] * multiplier;
			ip2Size += ip2Array[i] * multiplier;
			multiplier *= range;
		}
		if (ip1Size < ip2Size) {
			return true;
		}
		else {
			throw new IllegalArgumentException("First ip adress must be lower than second");
		}
	}

	public void printIp(int[] array) {
		StringBuilder sb = new StringBuilder();
		String separator = "";
		for (int i : array) {
			sb.append(separator);
			separator = ".";
			sb.append(i);

		}
		log.debug(sb.toString());
	}

	public boolean validateInputIps(String ip1, String ip2) {
		String regex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		if (ip1.matches(regex) && ip2.matches(regex)) {
			return true;
		}
		else {
			throw new IllegalArgumentException("Bad ipv4 adress format it must be string like 255.255.255.255");
		}
	}

	public boolean ipsShouldBeNotNull(String ip1, String ip2) {
		if (ip1 != null & ip2 != null) {
			return true;
		}
		else {
			throw new NullPointerException("First ip adress must be lower than second");
		}
	}
}

package com.iprange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IpRange {

	public List<String> proccessIpRange(String ip1, String ip2) {
		List<String> resultList = new ArrayList();
		if (ip1 != null && ip2 != null) {
			String regex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
			if (ip1.matches(regex) && ip2.matches(regex)) {
				String[] array1String = ip1.split("\\.");
				String[] array2String = ip2.split("\\.");
				int[] array1 = new int[4];
				int[] array2 = new int[4];
				for (int i = 0; i < array1String.length; i++) {
					array1[i] = Integer.parseInt(array1String[i]);
					array2[i] = Integer.parseInt(array2String[i]);
				}
				boolean check = false;
				for (int i = 0; i < array1.length; i++) {
					if (array1[i] < array2[i]) {
						check = true;
						break;
					}
				}
				if (check) {
					StringBuilder sb = new StringBuilder();
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
							for (int i = 0; i < array1.length; i++) {
								sb.append(array1[i]);
								if (i < 3) {
									sb.append(".");
								}
							}
							resultList.add(sb.toString());
							sb.setLength(0);
						}
					}					
				}
				else {
					throw new IllegalArgumentException("First ip adress must be lower than second");
				}
			}
			else {
				throw new IllegalArgumentException("Bad ipv4 adress format it must be string like 255.255.255.255");
			}
		}
		else {
			throw new NullPointerException("Ip adresses must be not null");
		}
		return resultList;
	}
}

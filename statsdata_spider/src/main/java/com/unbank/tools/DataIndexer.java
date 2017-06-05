package com.unbank.tools;

public class DataIndexer {

	public static void main(String args[]) {
		String sjcode = "201612";
		String year = null;
		String month = null;
		switch (sjcode.length()) {
		case 4:
			year = sjcode.substring(0, 4);
			month = "0";
			break;
		case 5:
			year = sjcode.substring(0, 4);
			month = sjcode.substring(4, 5);
			switch (month.charAt(0)) {
			case 'A':
				month = "03";
				break;
			case 'B':
				month = "06";
				break;
			case 'C':
				month = "09";
				break;
			case 'D':
				month = "12";
				break;
			default:
				break;
			}
			break;
		default:
			year = sjcode.substring(0, 4);
			month = sjcode.substring(4, 6);
			break;
		}

		System.out.println(year);
		System.out.println(month);
	}

}

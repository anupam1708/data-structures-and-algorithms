package com.anupam.algo.toolbox.week2;

import java.util.*;

public class LCM {

	private static long lcm_fast(long a, long b) {

		long gcd = gcd_fast(a,b);
		return  a * b/gcd;
	}

	private static long gcd_fast(long a, long b) {

		if (a == 0) {
			return b;
		} else if (b == 0) {
			return a;
		} else if (a == b) {
			return a;
		} else if(a > b) {
			return gcd_fast(a%b, b);
		} else if (a < b) {
			return gcd_fast(b%a, a);
		} 
		return 0;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		long a = scanner.nextLong();
		long b = scanner.nextLong();

		System.out.println(lcm_fast(a, b));
	}
}

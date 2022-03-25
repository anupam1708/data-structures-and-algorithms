package com.anupam.algo.toolbox.week2;

import java.util.*;

public class GCD {

	private static int gcd_naive(int a, int b) {
		int current_gcd = 1;
		for(int d = 2; d <= a && d <= b; ++d) {
			if (a % d == 0 && b % d == 0) {
				if (d > current_gcd) {
					current_gcd = d;
				}
			}
		}

		return current_gcd;
	}

	private static int gcd_fast(int a, int b) {

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
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(gcd_fast(a, b));
	}
}

package com.anupam.algo.toolbox.week2;

import java.util.*;

public class FibonacciPartialSum {

	private static long getFibonacciPartialSumNaive(long from, long to) {

		long higher =  getFibonacciHugeModulo(to+2, 10);

		long lower =  getFibonacciHugeModulo(from+1, 10);
		
		long lastDigit =  higher - lower;
		if(lastDigit >= 0) {
			return lastDigit;
		} else {
			return 10 + lastDigit;
		}

	}

	private static long getFibonacciHugeModulo(long n, long m) {
		if (n <= 1)
			return n;

		//we need the pisano period for m
		long previous = 0;
		long current  = 1;
		long[] pisano = new long[10000000];
		pisano[0] = 0;
		pisano[1] = 1;
		int size = 0;
		int i=2;

		while(true) {

			long tmp_previous = previous % m;
			previous = current % m;
			current = (tmp_previous + current) % m;
			pisano[i] = current;

			if(pisano[i] == 1 && pisano[i-1] == 0) {
				size = i-1; 
				break;
			}
			i++;
		}

		int index = (int) (n % size);

		return pisano[index];
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long from = scanner.nextLong();
		long to = scanner.nextLong();
		System.out.println(getFibonacciPartialSumNaive(from, to));
	}
}


package com.anupam.algo.toolbox.week6;

import java.util.*;
import java.io.*;

public class Partition3 {

	private static int partition3(int[] A) {

		if(A.length <3) {
			return 0;
		}

		//create a map to store the solutions to the subproblem
		// memoization
		Map<String, Boolean> lookup = new HashMap<String, Boolean>();

		//sum of all numbers in the array
		int sum = Arrays.stream(A).sum();

		//call the recursive method 
		boolean canPartition3Ways = canPartition3Ways(A, A.length-1, sum/3, sum/3, sum/3, lookup);


		if(sum%3 == 0 && canPartition3Ways) {
			return 1;
		} else {
			return 0;
		}
	}

	private static boolean canPartition3Ways(int[] a, int i, int j, int k, int l, Map<String, Boolean> lookup) {

		if(j==0 && k==0 && l==0) {
			return true;
		}

		if(i<0) {
			return false;
		}

		//create a unique key 
		String key = j + "|" + k + "|" + l + "|" + i;

		if(!lookup.containsKey(key)) {

			//Case 1: The current item is part of the first subset
			boolean A = false;
			if(j-a[i] >=0) {
				A = canPartition3Ways(a, i-1, j-a[i], k, l, lookup);
			}

			//Case 2: The current item is part of 2nd subset
			boolean B = false;
			if(!A && k-a[i] >=0) {
				B = canPartition3Ways(a, i-1, j, k-a[i], l, lookup);
			}

			boolean C = false;
			if(!A && !B && l-a[i] >=0) {
				C = canPartition3Ways(a, i-1, j, k, l-a[i], lookup);
			}

			lookup.put(key, A || B || C);

		}

		return lookup.get(key);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		System.out.println(partition3(A));
	}
}


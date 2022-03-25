package com.anupam.algo.toolbox.week3;

import java.util.*;
import java.util.stream.Collectors;

public class DotProduct {
	private static long maxDotProduct(int[] a, int[] b) {

		//write your code here
		long result = 0;
		//sort  both the lists 
		List<Integer> aList =Arrays.stream(a).boxed().collect(Collectors.toList());
		List<Integer> bList =Arrays.stream(b).boxed().collect(Collectors.toList());
		Collections.sort(aList);
		Collections.sort(bList);

		for (int i = 0; i < a.length; i++) {

			result += (long)aList.get(i) * (long)bList.get(i);
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			b[i] = scanner.nextInt();
		}
		System.out.println(maxDotProduct(a, b));
	}
}


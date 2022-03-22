package com.anupam.algo.toolbox.week4;

import java.util.*;
import java.io.*;

public class MajorityElement {

	private static int getMajorityElement(int[] a, int left, int right) {

		// base case; the only element in an array of size 1 is the majority
		// element.
		if (left == right) {
			return a[left];
		}

		// recurse on left and right halves of this slice.
		int mid = (left+ right)/2;
		int leftElem = getMajorityElement(a, left, mid);
		int rightElem = getMajorityElement(a, mid+1, right);

		// if the two halves agree on the majority element, return it.
		if (leftElem == rightElem) {
			return rightElem;
		}

		// otherwise, count each element and return the "winner".
		int leftCount = countInRange(a, leftElem, left, right);
		int rightCount = countInRange(a, rightElem, left, right);

		int size = (right - left + 1)/2;
		if(leftCount> size) {
			return leftElem;
		} else if (rightCount > size) {
			return rightElem;
		} else {
			return -1;
		}

	}

	private static int countInRange(int[] nums, int num, int lo, int hi) {
		int count = 0;
		for (int i = lo; i <= hi; i++) {
			if (nums[i] == num) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		if (getMajorityElement(a, 0, a.length-1) != -1) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}


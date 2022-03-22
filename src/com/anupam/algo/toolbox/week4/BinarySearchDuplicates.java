package com.anupam.algo.toolbox.week4;

import java.io.*;
import java.util.*;

public class BinarySearchDuplicates {

	static int binarySearch(int[] a, int x) {
		int left = 0, right = a.length-1;
		return binarySearchDuplicates(a, left, right, x);
	}

	private static int binarySearchDuplicates(int[] a, int left, int right, int x) {
		int mid = (left + right)/2;
		if(x<a[left] || x> a[right]) {
			return -1;
		} else if(x<a[mid]) {
			return binarySearchDuplicates(a, left, mid-1, x);
		} else if(x>a[mid]) {
			return binarySearchDuplicates(a, mid+1, right, x);
		} else if (x==a[mid]) {
			int lastFoundIndex = mid;
			int k = lastFoundIndex-1;
			while(k>=0) {
				if(x != a[k]) {
					return lastFoundIndex;

				}
				lastFoundIndex = k;
				k--;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		int m = scanner.nextInt();
		int[] b = new int[m];
		for (int i = 0; i < m; i++) {
			b[i] = scanner.nextInt();
		}
		for (int i = 0; i < m; i++) {
			//replace with the call to binarySearch when implemented
			System.out.print(binarySearch(a, b[i]) + " ");
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

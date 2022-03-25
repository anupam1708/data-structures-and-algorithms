package com.anupam.algo.toolbox.week4;

import java.io.*;
import java.util.*;

public class Sorting {

	private static void quick3Sort(int[] a, int lo, int hi) {
		if(hi<=lo) {
			return;
		}
		int lt = lo, i = lo+1, gt = hi;
		int v = a[lo];
		while(i<=gt) {
			if(a[i]<v) exch(a, lt++, i++);
			else if (a[i]>v) exch(a, i , gt--);
			else i++;
		}
		quick3Sort(a, lo, lt-1);
		quick3Sort(a, gt+1, hi);
	}

	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		quick3Sort(a, 0, n - 1);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
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


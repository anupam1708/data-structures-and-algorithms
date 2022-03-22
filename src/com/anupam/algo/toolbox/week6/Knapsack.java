package com.anupam.algo.toolbox.week6;

import java.util.*;

public class Knapsack {

	static int optimalWeight(int W, int[] w) {

		int numItems = w.length;

		int[][] value = new int[W+1][numItems+1];

		for (int i=0; i<=numItems; i++) {
			value[0][i] = 0;
		}

		for (int i=0; i<=W; i++) {
			value[i][0] = 0;
		}

		for(int i = 1; i<=numItems; i++) {
			
			for(int wt=1; wt<=W; wt++) {

				value[wt][i] = value[wt][i-1];

				int wi = w[i-1];

				if(wi <= wt) {
					int val = value[wt-wi][i-1] + wi;

					if(value[wt][i] < val) {
						value[wt][i] = val;
					}
				}

			}
		}

		return value[W][numItems];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int W, n;
		W = scanner.nextInt();
		n = scanner.nextInt();
		int[] w = new int[n];
		for (int i = 0; i < n; i++) {
			w[i] = scanner.nextInt();
		}
		System.out.println(optimalWeight(W, w));
	}
}


package com.anupam.algo.toolbox.week5;

import java.util.*;

class EditDistance {

	public static int EditDistance(String s, String t) {

		String A = '-' +s;
		String B = '-' +t;

		int n = s.length();
		int m = t.length();

		//add the values to column 0 and row 0
		int[][] editDist = new int[n+1][m+1];
		for (int i=0; i<=n; i++) {
			editDist[i][0] = i;
		}
		for(int j=0; j<=m; j++) {
			editDist[0][j] = j;
		}

		//calculate the rest of the matrix
		for (int j=1; j<=m; j++) {
			for(int i=1; i<=n; i++) {
				int insertion = editDist[i][j-1] +1;
				int deletion = editDist[i-1][j] +1;
				int match = editDist[i-1][j-1];
				int mismatch = editDist[i-1][j-1] +1;

				if(A.charAt(i) == B.charAt(j)) {
					editDist[i][j] = minAmong(insertion, deletion, match);
				} else {
					editDist[i][j] = minAmong(insertion, deletion, mismatch);
				}
			}
		}
		return editDist[n][m];
	}
	private static int minAmong(int insertion, int deletion, int match) {

		int min = Math.min(insertion, deletion);
		return Math.min(min, match);
	}
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String s = scan.next();
		String t = scan.next();

		System.out.println(EditDistance(s, t));
	}

}

package com.anupam.algo.toolbox.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PointsAndSegments {

	private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
		int[] cnt = new int[points.length];

		//status {start = 1, end = -1, point = 0}

		List<Point> pointsInLine = new ArrayList<Point>();

		for(int i = 0; i<starts.length; i++ ) {
			Point startPoint = new Point(starts[i], -1, -1);
			Point endPoint = new Point(ends[i], 1, -1);
			pointsInLine.add(startPoint);
			pointsInLine.add(endPoint);
		}

		for(int i = 0; i<points.length; i++ ) {
			Point point = new Point(points[i], 0, i);
			pointsInLine.add(point);
		}
		Collections.sort(pointsInLine);

		int counter = 0;
		for(int i = 0; i< (starts.length + ends.length + points.length); i++) {

			if(pointsInLine.get(i).getState() == -1) {
				counter++;
			} else if (pointsInLine.get(i).getState() == 1) {
				counter--;
			} else if (pointsInLine.get(i).getState() == 0) {
				cnt[pointsInLine.get(i).getIndex()] = counter;
			}

		}

		return cnt;
	}

	static class Point implements Comparable<Point> {
		private int value;
		private int state;
		private int index;

		public Point(int value, int state, int index) {
			this.value = value;
			this.state = state;
			this.index = index;
		}

		@Override
		public int compareTo(Point o) {
			if(value==o.value) {
				if(state > o.state) {
					return 1;
				} else if(state < o.state) {
					return -1;
				} else {
					return 0;
				}
			}
			else if(value>o.value) 
				return 1;  
			else  
				return -1;  
		}

		public int getValue() {
			return value;
		}

		public int getState() {
			return state;
		}

		public int getIndex() {
			return index;
		}

	}

	private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
		int[] cnt = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < starts.length; j++) {
				if (starts[j] <= points[i] && points[i] <= ends[j]) {
					cnt[i]++;
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		int[] starts = new int[n];
		int[] ends = new int[n];
		int[] points = new int[m];
		for (int i = 0; i < n; i++) {
			starts[i] = scanner.nextInt();
			ends[i] = scanner.nextInt();
		}
		for (int i = 0; i < m; i++) {
			points[i] = scanner.nextInt();
		}
		//use fastCountSegments
		int[] cnt = fastCountSegments(starts, ends, points);
		for (int x : cnt) {
			System.out.print(x + " ");
		}
	}
}


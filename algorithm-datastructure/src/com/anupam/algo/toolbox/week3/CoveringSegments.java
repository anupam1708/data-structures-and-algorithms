package com.anupam.algo.toolbox.week3;

import java.util.*;
import java.util.stream.Collectors;

public class CoveringSegments {

	private static int[] optimalPoints(Segment[] segments) {

		//Sort by start and end
		List<Segment> segmentList =Arrays.stream(segments).collect(Collectors.toList());

		Collections.sort(segmentList, new SortbyStart());
		int size = segmentList.size();

		int[] points = new int[size];
		int pointCount = 0;
		boolean refChange = false;
		int ref = 0;

		for (int i = 0; i < size-1; i++) {

			if(refChange) {
				ref = i;
			}

			int end = segmentList.get(ref).end;

			if(segmentList.get(i).start <= end && segmentList.get(i+1).start > end) {
				points[pointCount++] = segmentList.get(i).start;
				refChange = true;
			}
		}
		points[pointCount] = segmentList.get(size-1).start;
		
		int[] filteredArray = Arrays.stream(points).filter(num -> num != 0).toArray();    
		
		return filteredArray;
	}

	private static class Segment {
		int start, end;

		Segment(int start, int end) {
			this.start = start;
			this.end = end;
		}

	}

	private static class SortbyStart implements Comparator<Segment> {
		// Used for sorting in ascending order of start
		public int compare(Segment a, Segment b)
		{
			return a.start - b.start;
		}
	}

	private static class SortbyEnd implements Comparator<Segment> {
		// Used for sorting in ascending order of end
		public int compare(Segment a, Segment b)
		{
			return a.end - b.end;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Segment[] segments = new Segment[n];
		for (int i = 0; i < n; i++) {
			int start, end;
			start = scanner.nextInt();
			end = scanner.nextInt();
			segments[i] = new Segment(start, end);
		}
		int[] points = optimalPoints(segments);
		System.out.println(points.length);
		for (int point : points) {
			System.out.print(point + " ");
		}
	}
}


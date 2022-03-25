package com.anupam.algo.toolbox.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CarFueling {

	static int computeMinRefills(int dist, int tank, int[] stops) {

		int numRefills = 0;
		int startPoint = 0;

		//Add the first and the last stops
		List<Integer> stopsList = new ArrayList<Integer>();
		stopsList.add(0);

		List<Integer> list11 =Arrays.stream(stops).boxed().collect(Collectors.toList()); 
		stopsList.addAll(list11);
		stopsList.add(dist);
		int size = stopsList.size();
		int lastRefillIndex = 0;

		for(int i=0; i<size-1; i++) {
			
			if(stopsList.get(i) - startPoint > tank) {
				numRefills = -1;
			}

			if(stopsList.get(i+1) - startPoint > tank) {
				if(stopsList.get(i) - startPoint <= tank && i != lastRefillIndex) {
					numRefills++;
					startPoint = stopsList.get(i);
					lastRefillIndex = i;
				} else {
					numRefills = -1;
				}
			} else if(stopsList.get(i+1) - startPoint == tank && i != lastRefillIndex && i + 1 < size -1) {
				numRefills++;
				startPoint = stopsList.get(i+1);
				lastRefillIndex = i+1;
			}
		}

		if(stopsList.get(size-1) - startPoint > tank) {
			numRefills = -1;
		}


		return numRefills;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int dist = scanner.nextInt();
		int tank = scanner.nextInt();
		int n = scanner.nextInt();
		int stops[] = new int[n];
		for (int i = 0; i < n; i++) {
			stops[i] = scanner.nextInt();
		}

		System.out.println(computeMinRefills(dist, tank, stops));
	}
}

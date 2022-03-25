package com.anupam.algo.toolbox.week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FractionalKnapsack {

	private static double getOptimalValue(int capacity, int[] values, int[] weights) {

		//sort the values/weight array
		int size = values.length;
		List<Values> valuePerWeight = new ArrayList<Values>();
		for (int i=0; i<size; i++) {
			Values value = new FractionalKnapsack.Values(values[i], weights[i], (double) values[i]/weights[i]);
			valuePerWeight.add(value);
		}
		Collections.sort(valuePerWeight, Collections.reverseOrder());

		double capacityLeft = capacity;
		double valueAdded = 0;

		for (int i=0; i<size && capacityLeft>0 ; i++) {

			if(valuePerWeight.get(i).weight <= capacityLeft) {
				valueAdded += valuePerWeight.get(i).value;
				capacityLeft -= valuePerWeight.get(i).weight;
			} else {
				double ratio = capacityLeft/valuePerWeight.get(i).weight;
				valueAdded += valuePerWeight.get(i).value*ratio;
				capacityLeft -= valuePerWeight.get(i).weight*ratio;
			}

		}

		return valueAdded;
	}

	static class Values implements Comparable<Values> {

		int value;
		int weight;
		double valuePerWeight;

		public Values(int value, int weight, double valuePerWeight) {
			this.value = value;
			this.weight = weight;
			this.valuePerWeight = valuePerWeight;
		}

		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public double getValuePerWeight() {
			return valuePerWeight;
		}
		public void setValuePerWeight(double valuePerWeight) {
			this.valuePerWeight = valuePerWeight;
		}

		@Override
		public int compareTo(Values o) {

			if(valuePerWeight==o.valuePerWeight)  
				return 0;  
			else if(valuePerWeight>o.valuePerWeight)  
				return 1;  
			else  
				return -1;  
		}  

	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int capacity = scanner.nextInt();
		int[] values = new int[n];
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = scanner.nextInt();
			weights[i] = scanner.nextInt();
		}
		System.out.println(getOptimalValue(capacity, values, weights));
	}
} 

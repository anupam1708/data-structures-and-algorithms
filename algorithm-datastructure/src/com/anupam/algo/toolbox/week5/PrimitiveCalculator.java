package com.anupam.algo.toolbox.week5;

import java.util.*;

public class PrimitiveCalculator {

	private static Map<Integer, Integer> cachedNumOfOperations = new HashMap<Integer, Integer>();
	private static List<Integer> sequence = new ArrayList<Integer>();

	private static List<Integer> optimal_sequence_DP(int n) {

		for(int i=1; i<=n; i++) {
			int minOperations = minOperations(i);
			cachedNumOfOperations.put(i, minOperations);
		}

		sequence.add(n);
		optimal_sequence_backtracking(n);

		return sequence;
	}

	private static void optimal_sequence_backtracking(int n) {
		
		if(n<=1) {
			return;
		}

		int numAdd1Operations = cachedNumOfOperations.get(n-1);
		if(n%3 == 0) {
			int num3Operations = cachedNumOfOperations.get(n/3);
			if(num3Operations < numAdd1Operations) {
				sequence.add(n/3);
				optimal_sequence_backtracking(n/3);
			} else {
				sequence.add(n-1);
				optimal_sequence_backtracking(n-1);
			}


		} else if (n%2 ==0) {
			int num2Operations = cachedNumOfOperations.get(n/2);
			if(num2Operations < numAdd1Operations) {
				sequence.add(n/2);
				optimal_sequence_backtracking(n/2);
			} else {
				sequence.add(n-1);
				optimal_sequence_backtracking(n-1);
			}
		}  else {
			sequence.add(n-1);
			optimal_sequence_backtracking(n-1);
		}

	}

	private static int minOperations(int n) {

		if(n==0 || n==1) {
			return 0;
		} else if (n==2 || n==3) {
			return 1;
		}

		//memoization
		if(cachedNumOfOperations.containsKey(n)) {
			return cachedNumOfOperations.get(n);
		}

		int numAdd1Operations = minOperations(n-1);
		if(n%3 == 0) {
			int num3Operations = minOperations(n/3);
			return Math.min(num3Operations+1, numAdd1Operations+1);

		} else if (n%2 ==0) {
			int num2Operations = minOperations(n/2);
			return Math.min(num2Operations+1, numAdd1Operations+1);
		} else {
			return numAdd1Operations +1;
		}
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer> sequence = optimal_sequence_DP(n);
		System.out.println(sequence.size() - 1);
		for (Integer x : sequence) {
			System.out.print(x + " ");
		}
	}
}


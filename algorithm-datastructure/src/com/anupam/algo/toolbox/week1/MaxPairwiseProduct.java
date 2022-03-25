package com.anupam.algo.toolbox.week1;

import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
	
	/*
	 * static int getMaxPairwiseProduct(int[] numbers) { int max_product = 0; int n
	 * = numbers.length;
	 * 
	 * for (int first = 0; first < n; ++first) { for (int second = first + 1; second
	 * < n; ++second) { max_product = Math.max(max_product, numbers[first] *
	 * numbers[second]); } } return max_product; }
	 */
    
	static long getMaxPairwiseProduct(int[] numbers) {
		int n = numbers.length;
		long product = 0;
		
		int highestIndex = 0;
		for(int i= 1; i < n; i++) {
			if(numbers[i] > numbers[highestIndex]) {
				highestIndex = i;
			}
		}
		
		int secondHighestIndex = 0;
		if(highestIndex ==0) {
			secondHighestIndex = 1;
		}
		
		for(int i= 1; i < n; i++) {
			if(i != highestIndex && numbers[i] > numbers[secondHighestIndex]) {
				secondHighestIndex = i;
			}
		}

		product = (long)numbers[highestIndex]*numbers[secondHighestIndex];
		return product;
	}

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
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

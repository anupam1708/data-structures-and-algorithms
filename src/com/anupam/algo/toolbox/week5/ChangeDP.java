package com.anupam.algo.toolbox.week5;

import java.util.Scanner;

public class ChangeDP {
	private static int getChange(int m) {

		int numCoins = 0;

		// we have 3 coins - 1 , 3, 4 

		if(m ==1) {
			return 1;
		} else if(m==2) {
			return 2;
		} else if(m==3 || m==4) {
			return 1;
		}

		//divide the number by 4

		int num = m/4;
		int rem = m%4;

		if(rem == 0) {
			numCoins = num;
		} else if (rem == 1) {
			numCoins = num + 1;
		} else if (rem == 2) {
			numCoins = num + 1;
		} else {
			numCoins = num+1;
		}
		return numCoins;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		System.out.println(getChange(m));

	}
}


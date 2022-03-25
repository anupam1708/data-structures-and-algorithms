package com.anupam.algo.toolbox.week3;

import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
    	
    	int num10 = m/10;
    	int remAfter10 = m%10;
    	
    	int num5 = remAfter10/5;
    	int remAfter5 = remAfter10%5;
    	
        //write your code here
        return num10 + num5 + remAfter5;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}


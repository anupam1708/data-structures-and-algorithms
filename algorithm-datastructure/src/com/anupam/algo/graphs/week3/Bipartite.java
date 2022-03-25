package com.anupam.algo.graphs.week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {

	static int RED = 1;
	static int BLUE = 2;

	private static int bipartite(ArrayList<Integer>[] adj) {

		int isBipartite = -1;
		int size = adj.length;
		int[] color = new int[size];
		for(int i=0; i<size; i++) {
			color[i] = -1;
		}
		for(int i=0; i<size; i++) {
			if(color[i] == -1) {
				isBipartite =  isBipartite(adj, color, i);
				if(isBipartite == 0) {
					break;
				}
			}

		}
		return isBipartite;
	}

	private static int isBipartite(ArrayList<Integer>[] adj, int[] color, int s) {
		//create a queue
		Queue<Integer> q = new LinkedList<Integer>();

		color[s] = RED;
		q.add(s);

		while(!q.isEmpty()) {
			int w = q.poll();
			for(int v: adj[w]) {
				if(color[v] == -1) {
					q.add(v);
					color[v] = (color[w] == RED) ? BLUE : RED;
				} else if (color[v] == color[w]) {
					return 0;
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
			adj[y - 1].add(x - 1);
		}
		System.out.println(bipartite(adj));
	}
}


package com.anupam.algo.graphs.week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

	private static int distance(ArrayList<Integer>[] adj, int s, int t) {

		int INFINITY = Integer.MAX_VALUE;

		int size = adj.length;
		int[] dist = new int[size];
		for(int i=0; i<size; i++) {
			dist[i] = INFINITY;
		}

		//create a queue
		Queue<Integer> q = new LinkedList<Integer>();

		dist[s] = 0;
		q.add(s);

		while(!q.isEmpty()) {
			int w = q.poll();
			for(int v: adj[w]) {
				if(dist[v] == INFINITY) {
					q.add(v);
					dist[v] = dist[w] +1;
				}
			}
		}
		return dist[t] == INFINITY ? -1: dist[t];
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
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		System.out.println(distance(adj, x, y));
	}
}


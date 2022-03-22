package com.anupam.algo.graphs.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NegativeCycle {

	static int INFINITY = Integer.MAX_VALUE;

	private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {

		int size = adj.length;
		int[] dist = new int[size];
		Arrays.fill(dist, INFINITY);

		boolean[] modified = new boolean[size];

		for(int i=0; i<size; i++) {
			if(modified[i]) {
				continue;
			}
			boolean hasCycle = hasCycle(adj, cost, dist, i);
			if(hasCycle) {
				return 1;
			}
			for(int u=0; u<size; u++) {
				if(dist[u] != INFINITY) {
					modified[u] = true;
				}
			}
		}
		return 0;
	}

	private static boolean hasCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist,  int s) {

		dist[s] = 0;
		int size = adj.length;

		for(int i=1; i<=size-1; i++) {
			for(int u=0; u<size; u++) {
				for(int j=0; j<adj[u].size(); j++) {
					int v = adj[u].get(j);
					int weight = cost[u].get(j);
					if(dist[u] != INFINITY && dist[v] > dist[u] + weight ) {
						dist[v] = dist[u] + weight;
					}
				}
			}
		}

		for(int u=0; u<size; u++) {
			for(int j=0; j<adj[u].size(); j++) {
				int v = adj[u].get(j);
				int weight = cost[u].get(j);
				if(dist[u] != INFINITY && dist[v] > dist[u] + weight ) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
		ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			cost[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			adj[x - 1].add(y - 1);
			cost[x - 1].add(w);
		}
		System.out.println(negativeCycle(adj, cost));
	}
}


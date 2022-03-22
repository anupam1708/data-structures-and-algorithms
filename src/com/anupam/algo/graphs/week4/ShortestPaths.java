package com.anupam.algo.graphs.week4;

import java.util.*;

public class ShortestPaths {

	private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {

		int size = adj.length;
		distance[s] = 0;
		reachable[s] = 1;

		//we need to run bellman-ford for v-1 times

		for(int i=1; i<=size-1; i++) {
			for(int u=0; u<size; u++) {
				for(int j=0; j<adj[u].size(); j++) {
					int v = adj[u].get(j);
					int uv = cost[u].get(j);
					if(distance[u] != Long.MAX_VALUE && distance[v] > distance[u] + uv) {
						distance[v] = distance[u] + uv;
						reachable[v] = 1;
					}
				}
			}
		}

		//Run the bellman-ford for vth time and save all nodes relaxed in this iteration in a queue
		Queue<Integer> relaxedNodeOnV = new LinkedList<Integer>();
		boolean[] marked = new boolean[size];

		for(int u=0; u<size; u++) {
			for(int j=0; j<adj[u].size(); j++) {
				int v = adj[u].get(j);
				int uv = cost[u].get(j);
				if(distance[u] != Long.MAX_VALUE && distance[v] > distance[u] + uv) {
					if(!marked[v]) {
						marked[v] = true;
						relaxedNodeOnV.add(v);
					}

				}
			}
		}

		while(!relaxedNodeOnV.isEmpty()) {
			int u = relaxedNodeOnV.poll();
			shortest[u] = 0;
			marked[u] = true;
			for(int w: adj[u]) {
				if(!marked[w]) {
					relaxedNodeOnV.add(w);
				}
			}
		}
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
		int s = scanner.nextInt() - 1;
		long distance[] = new long[n];
		int reachable[] = new int[n];
		int shortest[] = new int[n];
		for (int i = 0; i < n; i++) {
			distance[i] = Long.MAX_VALUE;
			reachable[i] = 0;
			shortest[i] = 1;
		}
		shortestPaths(adj, cost, s, distance, reachable, shortest);
		for (int i = 0; i < n; i++) {
			if (reachable[i] == 0) {
				System.out.println('*');
			} else if (shortest[i] == 0) {
				System.out.println('-');
			} else {
				System.out.println(distance[i]);
			}
		}
	}

}


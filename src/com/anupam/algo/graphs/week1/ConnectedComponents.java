package com.anupam.algo.graphs.week1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {

	private static int numberOfComponents(ArrayList<Integer>[] adj) {

		int numVertices = adj.length;
		boolean[] visited = new boolean[numVertices];
		int cc = 0;
		for(int i=0; i<numVertices; i++) {

			if(!visited[i]) {
				explore(adj, visited, i);
				cc++;
			}

		}
		return cc;
	}

	private static void explore(ArrayList<Integer>[] adj, boolean[] visited, int x) {

		visited[x] = true;
		ArrayList<Integer> neighbours = adj[x];
		for(int s : neighbours) {
			if(!visited[s]) {
				explore(adj, visited, s);
			}
		}
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
		System.out.println(numberOfComponents(adj));
	}
}


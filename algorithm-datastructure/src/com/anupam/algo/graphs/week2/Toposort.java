package com.anupam.algo.graphs.week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
	private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {

		ArrayList<Integer> order = new ArrayList<Integer>();

		int numVertices = adj.length;
		boolean[] visited = new boolean[numVertices];
		for(int i=0; i<numVertices; i++) {
			if(!visited[i]) {
				explore(adj, visited, order, i);
			}
		}
		Collections.reverse(order);
		return order;
	}

	private static void explore(ArrayList<Integer>[] adj, boolean[] visited, ArrayList<Integer> order, int x) {

		visited[x] = true;
		ArrayList<Integer> neighbours = adj[x];
		for(int s : neighbours) {
			if(!visited[s]) {
				explore(adj, visited, order, s);
			}
		}
		//finished exploring the vertex x
		order.add(x);
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
		}
		ArrayList<Integer> order = toposort(adj);
		for (int x : order) {
			System.out.print((x + 1) + " ");
		}
	}
}


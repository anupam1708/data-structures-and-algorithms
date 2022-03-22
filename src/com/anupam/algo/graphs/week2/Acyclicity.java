package com.anupam.algo.graphs.week2;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
	
	private static boolean hasCycle = false;

	private static int acyclic(ArrayList<Integer>[] adj) {

		int numVertices = adj.length;
		boolean[] visited = new boolean[numVertices];
		boolean[] onRecursionStack = new boolean[numVertices];

		for(int i=0; i<numVertices; i++) {

			if(hasCycle) break;
			if(!visited[i]) {
				hasCycle(adj, visited, onRecursionStack, i);
			}
		}
		return hasCycle ? 1:0;
	}

	private static void hasCycle(ArrayList<Integer>[] adj, boolean[] visited, boolean[] onRecursionStack, int x) {
		
		visited[x] = true;
		onRecursionStack[x] = true;

		ArrayList<Integer> neighbours = adj[x];
		for(int s : neighbours) {
			if(!visited[s]) {
				hasCycle(adj, visited, onRecursionStack, s);
			} else if(onRecursionStack[s]) {
				hasCycle = true;
				return;
			}
		}
		onRecursionStack[x] = false;
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
		System.out.println(acyclic(adj));
	}
}


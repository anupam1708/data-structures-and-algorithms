package com.anupam.algo.graphs.week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StronglyConnected {

	private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {

		int cc = 0;
		//write your code here
		ArrayList<Integer>[] reverse = reverseGraph(adj);
		List<Integer> reversePostOrder = reversePostOrder(reverse);
		List<Integer> order = new ArrayList<>();
		int size = reversePostOrder.size();
		
		boolean[] visited = new boolean[size];
		for(int i=0; i<size; i++) {
			int w = reversePostOrder.get(i);
			if(!visited[w]) {
				explore(adj, visited, order, w);
				cc++;
			}
		}
		return cc;
	}

	private static List<Integer> reversePostOrder(ArrayList<Integer>[] adj) {
		List<Integer> order = new ArrayList<>();
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

	private static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {

		int size = adj.length;
		ArrayList<Integer>[] reverse = (ArrayList<Integer>[]) new ArrayList[size];
		for(int i=0; i<reverse.length; i++) {
			reverse[i] = new ArrayList<Integer>();
		}

		for(int i=0; i<size; i++) {
			for (int w : adj[i]) {
				reverse[w].add(i);
			}
		}
		return reverse;
	}
	
	private static void explore(ArrayList<Integer>[] adj, boolean[] visited, List<Integer> order, int x) {

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
		System.out.println(numberOfStronglyConnectedComponents(adj));
	}
}


package com.anupam.algo.graphs.week4;

import java.util.*;

public class Dijkstra {

	private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {

		int INFINITY = Integer.MAX_VALUE;

		int size = adj.length;
		int[] dist = new int[size];
		int[] prev = new int[size];

		Arrays.fill(dist, INFINITY);
		Arrays.fill(prev, -1);

		dist[s] = 0;

		Queue<Vertex> distQueue = new PriorityQueue<Vertex>();
		Vertex initVertex = new Vertex(s, dist[s]);
		distQueue.add(initVertex);

		while(!distQueue.isEmpty()) {

			Vertex vertex = distQueue.poll();
			int u = vertex.getNode();

			int k = 0;
			for(int v: adj[u]) {

				int vdist = dist[v];
				if(dist[v] > dist[u] + cost[u].get(k)) {
					dist[v] = dist[u] + cost[u].get(k);
					prev[v] = u;

					Vertex currentVertex = new Vertex(v, vdist);
					Vertex relaxedVertex = new Vertex(v, dist[v]);

					if(distQueue.contains(currentVertex)) {
						distQueue.remove(currentVertex);
						distQueue.add(relaxedVertex);
					} else {
						distQueue.add(relaxedVertex);
					}
				}
				k++;
			}
		}
		int shortestDist = dist[t];

		if(shortestDist == INFINITY) {
			shortestDist = -1;
		}
		return shortestDist;
	}

	private static class Vertex implements Comparable<Vertex> {

		int node;
		int dist;

		public Vertex(int node, int dist) {
			super();
			this.node = node;
			this.dist = dist;
		}

		public int getNode() {
			return node;
		}

		public void setNode(int node) {
			this.node = node;
		}

		public int getDist() {
			return dist;
		}

		public void setDist(int dist) {
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.dist - o.dist;
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
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		System.out.println(distance(adj, cost, x, y));
	}
}


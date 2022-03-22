package com.anupam.algo.graphs.week5;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Clustering {

	private static double clustering(int[] x, int[] y, int k) {

		//Add all edges into priority queue
		Queue<Edge> edges = new PriorityQueue<Edge>();
		int size = x.length;
		for(int i=0; i<size; i++) {
			for(int j=i+1; j<size; j++) {

				Node start = new Node(x[i], y[i], i);
				Node end = new Node(x[j], y[j], j);
				Edge edge = new Edge(i, j, calculateWeight(start, end));
				edges.add(edge);

			}
		}

		Node[] nodes = new Node[size];

		for(int i=0; i<size; i++) {
			makeSet(nodes, i, x, y);
		}

		int unionNum = 0;
		while(!edges.isEmpty()) {

			Edge edge = edges.poll();
			int start = edge.getStart();
			int end = edge.getEnd();

			int r1 = find(start, nodes);
			int r2 = find(end, nodes);
			
			if(r1 != r2) {
				unionNum++;
				union(r1, r2, nodes);
			}


			if(unionNum > size-k) {
				return edge.getWeight();
			}

		}

		return -1.;
	}



	private static int find(int i, Node[] nodes) {

		if(i != nodes[i].parent) {
			nodes[i].parent = find(nodes[i].parent, nodes);
		}
		return nodes[i].parent;
	}

	private static void union(int u, int v, Node[] nodes) {

		int r1 = find(u, nodes);
		int r2 = find(v, nodes);

		if(r1 == r2) {
			return;
		} 

		if(nodes[r1].rank > nodes[r2].rank) {
			nodes[r2].parent = r1;
		} else {
			nodes[r1].parent = r2;

			if(nodes[r1].rank == nodes[r2].rank) {
				nodes[r2].rank++;
			}
		}
	}



	private static void makeSet(Node[] nodes, int i, int[] x, int[] y) {

		nodes[i] = new Node(x[i], y[i], i);

	}

	private static class Node {

		int x;
		int y;
		int parent;
		int rank;

		public Node(int x, int y, int parent) {
			this.x = x;
			this.y = y;
			this.parent = parent;
			this.rank = 0;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getY() {
			return y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getParent() {
			return parent;
		}

		public void setParent(int parent) {
			this.parent = parent;
		}

		public void setY(int y) {
			this.y = y;
		}

	}

	public static double calculateWeight(Node a, Node b) {
		return Math.sqrt((a.getX() - b.getX())*(a.getX() - b.getX()) + (a.getY() - b.getY())*(a.getY() - b.getY()));
	}

	private static class Edge implements Comparable<Edge> {

		int start;
		int end;
		double weight;

		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.weight == o.weight) {
				return 0;
			} else if (this.weight > o.weight) {
				return 1;
			} else {
				return -1;
			}
		}

	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = scanner.nextInt();
			y[i] = scanner.nextInt();
		}
		int k = scanner.nextInt();
		System.out.println(clustering(x, y, k));
	}
}


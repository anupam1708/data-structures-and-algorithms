package com.anupam.algo.graphs.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ConnectingPoints {

	private static double minimumDistance(int[] x, int[] y) {
		double result = 0.;

		int INFINITY = Integer.MAX_VALUE;

		int size = x.length;
		int[] cost = new int[size];
		int[] parent = new int[size];

		boolean[] marked = new boolean[size];

		Arrays.fill(cost, INFINITY);
		Arrays.fill(parent, -1);
		Arrays.fill(marked, false);

		// take the first point as the starting point
		int s = 0;
		cost[s] = 0;
		parent[s] = -1;
		marked[s] = true;

		// add the 1st point in the priority queue
		Queue<Vertex> distQueue = new PriorityQueue<Vertex>();
		List<Vertex> nodes = new ArrayList<Vertex>();
		
		Vertex initVertex = new Vertex(0, x[0], y[0], cost[s]);
		distQueue.add(initVertex);
		nodes.add(initVertex);

		//add the remaining nodes in another list
		// nodes are added with x and y and cost as INFINITY
		//cost will be calculated inside the while loop
		
		for(int i=1; i<size; i++) {
			Vertex vertex = new Vertex(i, x[i], y[i], INFINITY);
			distQueue.add(vertex);
			nodes.add(vertex);
		}

		while(!distQueue.isEmpty()) {

			Vertex startNode = distQueue.poll();
			marked[startNode.getId()] = true;

			for(Vertex endNode: nodes) {

				if(marked[endNode.getId()])
					continue;

				double updatedCost = calculateCost(startNode, endNode);
				double initialCost = endNode.getCost();

				if(updatedCost < initialCost) {

					parent[endNode.getId()] = startNode.getId();

					endNode.setCost(updatedCost);
					Vertex currentVertex = new Vertex(endNode.getId(), endNode.getX(), endNode.getY(), initialCost);
					Vertex relaxedVertex = new Vertex(endNode.getId(), endNode.getX(), endNode.getY(), updatedCost);

					distQueue.remove(currentVertex);
					distQueue.add(relaxedVertex);
				}
			}
		}

		for(int i=1; i<size; i++) {
			if(!marked[i]) {
				continue;
			}
			result += nodes.get(i).getCost();
		}

		return result;

	}

	private static double calculateCost(Vertex startNode, Vertex endNode) {

		int xdiff = startNode.getX() - endNode.getX();
		int ydiff = startNode.getY() - endNode.getY();
		Double squareDiff = Math.pow(xdiff, 2) + Math.pow(ydiff, 2);

		Double distance = Math.sqrt(squareDiff);
		return distance;
	}

	private static class Vertex implements Comparable<Vertex> {

		int id;
		int x;
		int y;
		double cost;

		public Vertex(int id, int x, int y, double cost) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		public int getId() {
			return id;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public double getCost() {
			return cost;
		}

		public void setCost(double cost) {
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return (int)(this.cost - o.cost);
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
		System.out.println(minimumDistance(x, y));
	}
}


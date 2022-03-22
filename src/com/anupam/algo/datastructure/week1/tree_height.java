package com.anupam.algo.datastructure.week1;

import java.util.*;

import java.io.*;

public class tree_height {

	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class Tree implements Comparable<Tree> {
		int key;
		List<Tree> children;
		int parent;
		int height;

		public Tree(int key, int parent) {
			this.key = key;
			this.children = new ArrayList<>();
			this.parent = parent;
		}

		public void addChild(Tree tree) {
			children.add(tree);
		}

		public List<Tree> getChildren() {
			return children;
		}

		@Override
		public int compareTo(Tree o) {

			if (this.height == o.height) {
				return 0;
			} else if (this.height > o.height) {
				return 1;
			} else {
				return -1;
			}
		}


	}

	public class TreeHeight {
		int n;
		int parent[];
		List<Tree> forest = new ArrayList<Tree>();

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
			// Replace this code with a faster implementation
			int maxHeight = 0;
			Tree root = null;
			//construct the tree 
			int size = parent.length;
			for (int i=0; i<size; i++) {
				Tree tree = new Tree(i, parent[i]);
				forest.add(tree);
				if(parent[i] == -1) {
					root = tree;
				}
			}

			//add children
			addChildren(forest);

			//find the max height
			maxHeight =  computeRootHeight(root);

			return maxHeight;
		}

		private int computeRootHeight(Tree root) {

			if(root == null) {
				return 0;
			}
			List<Tree> children = root.getChildren();

			if(children.size() == 0) {
				return 1;
			}

			int maxHeight = 0;
			for(int i=0; i<children.size(); i++) {
				Tree tree = children.get(i);
				int height = computeRootHeight(tree);
				if(height > maxHeight) {
					maxHeight = height;
				}
			}

			return 1 + maxHeight;
		}

		private void addChildren(List<Tree> forest) {

			for(int i=0; i<forest.size(); i++) {
				Tree tree = forest.get(i);
				int treeParent = tree.parent;
				if(treeParent != -1) {
					forest.get(treeParent).addChild(tree);
				}
			}
		}
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_height().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}

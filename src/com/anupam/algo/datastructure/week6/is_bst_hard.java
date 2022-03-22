package com.anupam.algo.datastructure.week6;

import java.util.*;

import java.io.*;

public class is_bst_hard {
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

	public class IsBST {
		class Node {
			long key;
			int left;
			int right;

			Node(long key, int left, int right) {
				this.left = left;
				this.right = right;
				this.key = key;
			}
		}

		int nodes;
		Node[] tree;
		boolean ascending = true;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			nodes = in.nextInt();
			tree = new Node[nodes];
			for (int i = 0; i < nodes; i++) {
				tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
			}
		}

		public boolean solve() {
			ArrayList<Long> result = new ArrayList<Long>();
			// Finish the implementation
			// You may need to add a new recursive method to do that

			inOrderTraversal(0, result);

			return isSorted(result);
		}

		private boolean isSorted(ArrayList<Long> result) {

			//return true is list is sorted
			int size = result.size();
			for(int i=0; i<size-1 && ascending; i++) {
				ascending = ascending && (result.get(i+1) - result.get(i) >= 0);
			}
			return ascending;
		}

		private void inOrderTraversal(int index, ArrayList<Long> result) {
			// TODO Auto-generated method stub
			if(index < nodes && index != -1) {
				Node node = tree[index];
				int leftIndex = node.left;

				if(leftIndex < nodes && leftIndex != -1) {
					Node leftNode = tree[leftIndex];
					if(node.key == leftNode.key) {
						ascending = false;
						return;
					}
				}
				inOrderTraversal(node.left, result);
				result.add(node.key);
				inOrderTraversal(node.right, result);
			}

		}
	}


	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new is_bst_hard().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		IsBST tree = new IsBST();
		tree.read();
		if (tree.solve()) {
			System.out.println("CORRECT");
		} else {
			System.out.println("INCORRECT");
		}
	}
}

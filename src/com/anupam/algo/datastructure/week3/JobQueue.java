package com.anupam.algo.datastructure.week3;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }
    
    private class Worker implements Comparable<Worker> {
    	
    	private int index;
    	private long nextProcessingTime;
    	
		public Worker(int index, long nextProcessingTime) {
			super();
			this.index = index;
			this.nextProcessingTime = nextProcessingTime;
		}

		public int getIndex() {
			return index;
		}

		public long getNextProcessingTime() {
			return nextProcessingTime;
		}

		@Override
		public int compareTo(Worker that) {
			
			if(this.nextProcessingTime < that.nextProcessingTime) return -1;
			if(this.nextProcessingTime > that.nextProcessingTime) return 1;
			
			return this.index - that.index;
		}
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        
        //priority queue for keeping the threads
        PriorityQueue<Worker> threadQueue = new PriorityQueue<Worker>();
        for (int i=0; i < numWorkers; i++) {
        	Worker worker = new Worker(i, 0);
        	threadQueue.offer(worker);
        }
        for(int i=0; i < jobs.length; i++) {
        	int duration = jobs[i];
        	Worker bestWorker = threadQueue.poll();
        	assignedWorker[i] = bestWorker.getIndex();
        	startTime[i] = bestWorker.getNextProcessingTime();
        	Worker newWorker = new Worker(bestWorker.getIndex(), bestWorker.getNextProcessingTime() + duration);
        	threadQueue.offer(newWorker);
        }
        
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

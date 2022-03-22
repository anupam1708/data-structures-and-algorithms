package com.anupam.algo.datastructure.week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
	Bracket(char type, int position) {
		this.type = type;
		this.position = position;
	}

	boolean Match(char c) {
		if (this.type == '[' && c == ']')
			return true;
		if (this.type == '{' && c == '}')
			return true;
		if (this.type == '(' && c == ')')
			return true;
		return false;
	}

	char type;
	int position;
}

class check_brackets {
	public static void main(String[] args) throws IOException {
		InputStreamReader input_stream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input_stream);
		String text = reader.readLine();

		int bracketIndex = -1;
		boolean bracketMatching = true;

		Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
		for (int position = 0; position < text.length(); ++position) {
			char next = text.charAt(position);

			if (next == '(' || next == '[' || next == '{') {
				// Process opening bracket, write your code here
				Bracket bracket = new Bracket(next, position+1);
				opening_brackets_stack.push(bracket);
				bracketMatching = false;
			}

			if (next == ')' || next == ']' || next == '}') {
				// Process closing bracket, write your code here
				if(opening_brackets_stack.isEmpty()) {
					bracketMatching = false;
					bracketIndex = position+1;
					break;
				}

				Bracket lastBracket = opening_brackets_stack.pop();
				if(!lastBracket.Match(next)) {
					bracketMatching = false;
					bracketIndex = position+1;
					break;
				} else {
					bracketMatching = true;
				}
			}
		}

		// Printing answer, write your code here
		if(bracketMatching && opening_brackets_stack.isEmpty()) {
			System.out.println("Success");
		} else {
			if(bracketIndex == -1) {
				Bracket lastBracket = opening_brackets_stack.pop();
				System.out.println(lastBracket.position);
			} else {
				System.out.println(bracketIndex);
			}
		}
	}
}

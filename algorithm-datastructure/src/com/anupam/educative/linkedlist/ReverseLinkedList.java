package com.anupam.educative.linkedlist;

// Importing required functions
import java.util.*;

class Reverse {
	
    public static LinkedListNode reverse(LinkedListNode head) {
    	
    	if(head == null || head.next == null) {
    		return head;
    	}
    	
    	//assign reversed as the head of the reverse list
        LinkedListNode reversed = head;
        
        //assign oldHead as the head.next
        LinkedListNode oldHead = head.next;
        
        
        reversed.next = null;
        
        //iterate through the rest of the old linked list 
        while(oldHead != null) {
        	
        	LinkedListNode temp = oldHead;
        	oldHead = oldHead.next;
        	
        	temp.next = reversed;
        	reversed = temp;
        }
        
        // TODO: Write - Your - Code
        return reversed;
    }
    
    public static void main(String[] args) {
        Integer[][] inputs = {{29, 23, 82, 11, 4, 3, 21}, {59, 7, -3, 21, 14, 30, 120}};

        for (int i = 0; i < inputs.length; i++) {
            LinkedList obj = new LinkedList();
            obj.createLinkedList(Arrays.asList(inputs[i]));

            System.out.print((i + 1) + ". Original list:\t");
            obj.displayLinkedList();

            // Reversing the created linked list
            LinkedList objTmp = new LinkedList();
            objTmp.head = reverse(obj.head);
            System.out.print("   Reversed list:\t");

            // Display Reversed List
            objTmp.displayLinkedList();
            if (i != inputs.length - 1)
                System.out.println(
                        "-------------------------------------------------------------------------------------------------------\n");
        }
    }
}
package com.anupam.educative.linkedlist;

import java.util.Arrays;

public class DeleteAllOccurance {

	public static LinkedListNode deleteNode(LinkedListNode head, int key) {
		
		if(head == null) {
			return null;
		}
		
		LinkedListNode prev = head;
		
		while(head.data == key) {
			head = head.next;
		}
		
		LinkedListNode current = prev.next;
		
		while(current != null) {
			
			if(current.data == key) {
				
				prev.next = current.next;
				current = prev.next;
				
			}
			prev = prev.next;
			current = prev.next;
			
		}
		
		return head;
	}
	
    public static void main(String[] args) {
        Integer[][] inputs = {{29, 23, 82, 11, 4, 3, 21}};

        for (int i = 0; i < inputs.length; i++) {
            LinkedList obj = new LinkedList();
            obj.createLinkedList(Arrays.asList(inputs[i]));

            System.out.print((i + 1) + ". Original list:\t");
            obj.displayLinkedList();

            // removing the created linked list
            LinkedList objTmp = new LinkedList();
            objTmp.head = deleteNode(obj.head, 11);
            System.out.print("   removed key: " + 11);

            // Display Reversed List
            objTmp.displayLinkedList();
            if (i != inputs.length - 1)
                System.out.println(
                        "-------------------------------------------------------------------------------------------------------\n");
        }
    }

}

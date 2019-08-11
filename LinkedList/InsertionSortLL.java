package LinkedList;

import java.util.Scanner;

public class InsertionSortLL {
	private static Scanner s = new Scanner(System.in);
	public static LinkedListNode<Integer> input() {
		int data = s.nextInt();

		LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> tail = null;
		while (data != -1) {
			LinkedListNode<Integer> newNode = new LinkedListNode<Integer>(data);
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
			data = s.nextInt();
		}
		return head;
	}
	public static void print(LinkedListNode<Integer> head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

    	
	public static void main(String[] args) {
		print(insertionSort(input()));
		
	}
	public static LinkedListNode<Integer> insertionSort(LinkedListNode<Integer> head) {
		LinkedListNode<Integer> sorted=null;
        LinkedListNode<Integer> curr=head;
        while(curr!=null){
            LinkedListNode<Integer> key=curr.next;
            insertPlace(sorted,curr);
            curr=key;  
        }
        return sorted;

	}
    
    private static void insertPlace(LinkedListNode<Integer> sorted,LinkedListNode<Integer> node){
        if(sorted==null || sorted.data>=node.data){
            node.next=sorted;
            sorted=node;
        }
        else{
            LinkedListNode<Integer> curr=sorted;
            while(curr.next!=null && curr.next.data<sorted.data)
                curr=curr.next;
            node.next=curr.next;
            curr.next=node;
        }
    }

}

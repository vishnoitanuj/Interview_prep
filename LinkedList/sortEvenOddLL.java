package LinkedList;

import java.util.ArrayList;
import java.util.Scanner;


public class sortEvenOddLL {
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		print(Solution.sortEvenOdd(input()));
	}

	public static LinkedListNode<Integer> input() {
		int data = s.nextInt();
		
		LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> tail = null;
		while (data!=-1) {
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
}

class Solution {
	public static LinkedListNode<Integer> sortEvenOdd(LinkedListNode<Integer> head) {
        LinkedListNode<Integer> curr=head,temp;
        ArrayList<Integer> e=new ArrayList<>();
        ArrayList<Integer> o=new ArrayList<>();
        int n;
        while(curr!=null){
            if(curr.data%2==0){
                e.add(curr.data);
            }
            else
            	o.add(curr.data);
            curr=curr.next;
        }
        if(o.size()==0 || e.size()==0)
        	return head;
        head=new LinkedListNode(o.get(0));
        curr=head;
        for(int i=1;i<o.size();i++) {
        	temp=new LinkedListNode(o.get(i));
        	curr.next=temp;
        	curr=temp;
        }
        for(int i:e) {
        	temp=new LinkedListNode(i);
        	curr.next=temp;
        	curr=temp;
        }
        return head;

	}
}

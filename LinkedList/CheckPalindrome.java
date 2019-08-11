package LinkedList;

import java.util.Scanner;

public class CheckPalindrome {
	
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print(isPalindrome_2(input()));
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
	
public static boolean isPalindrome_2(LinkedListNode<Integer> head) {
	LinkedListNode<Integer> slow=head,fast=head;
	while(fast.next!=null && fast.next.next!=null) {
		slow=slow.next;
		fast=fast.next.next;
	}
	LinkedListNode<Integer> h2=reverse(slow.next);
	slow.next=null;
	LinkedListNode<Integer> h1=head;
	LinkedListNode<Integer> curr1=h1,curr2=h2;
	System.out.println(h1.data+ " " + h2.data);
	while(curr1!=null && curr2!=null) {
		if(curr1.data!=curr2.data)
			return false;
		curr1=curr1.next;
		curr2=curr2.next;
	}
	return true;
	
	
        
    }

private static LinkedListNode<Integer> reverse(LinkedListNode<Integer> head) {
	// TODO Auto-generated method stub
	LinkedListNode<Integer> curr=head,temp,prev=null;
	while(curr!=null) {
		temp=curr.next;
		curr.next=prev;
		prev=curr;
		curr=temp;
	}
	return prev;
}

public static void print(LinkedListNode<Integer> head) {
	LinkedListNode<Integer> curr=head;
	while(curr!=null) {
		System.out.print(curr.data);
		curr=curr.next;
	}
	System.out.println();
}


}

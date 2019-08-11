package LinkedList;

import java.util.*;
public class UnionofLL {
	
	static Scanner s=new Scanner(System.in);
	public static LinkedListNode<Integer> createlist() {
		
		LinkedListNode<Integer> head=null;
		LinkedListNode<Integer> rear=null;
		int data=s.nextInt();
		while(data!=-1)
		{
			LinkedListNode<Integer> newnode=new LinkedListNode<Integer>(data);
			if(head==null)
			{
				head=newnode;
				rear=head;
			}
			else
			{
				rear.next=newnode;
				rear=rear.next;
			}
			data=s.nextInt();
		}
		return head;
	}
	
	public static void print(LinkedListNode<Integer> head)
	{  
		while(head!=null)
		{
			System.out.println(head.data);
			head=head.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedListNode<Integer> head1=createlist();
		LinkedListNode<Integer> head2=createlist();
		head1=Union(head1,head2);
		print(head1);
	}
	
	
	
	public static LinkedListNode<Integer> Union(LinkedListNode<Integer> head1, LinkedListNode<Integer> head2) {
		 if(head1==null){
            head1=head2;
            return head1;
        }
        HashMap<Integer, Integer> map=new HashMap<>();
        LinkedListNode<Integer> current=head1,last=head1;
        while(current!=null){
            last=current;
            if(map.containsKey(current.data))
                map.put(current.data,map.get(current.data)+1);
            else
                map.put(current.data,1);
            current=current.next;
        }
        current=head2;
        while(current!=null){
            if(map.containsKey(current.data)){
                if(map.get(current.data)<=0){
                	LinkedListNode<Integer> temp=new LinkedListNode<Integer>(current.data);  /*Here*/
                    last.next=temp;
                    last=current;
                }
                else
                    map.put(current.data,map.get(current.data)-1);
            }
            else{
            	LinkedListNode<Integer> temp=new LinkedListNode<Integer>(current.data);   /*Here*/
                last.next=temp;
                last=last.next;
            }
            current=current.next;
        }
        return head1;
		
	}
}

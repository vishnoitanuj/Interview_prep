package BinaryTree;

import java.util.ArrayList;
import java.util.Scanner;

public class LevelLinkedList {
	static Scanner s = new Scanner(System.in);

	public static BinaryTreeNode<Integer> takeInput(){
		QueueUsingLL<BinaryTreeNode<Integer>>  pendingNodes = new QueueUsingLL<BinaryTreeNode<Integer>>(); 
		Scanner s = new Scanner(System.in);
		int rootData = s.nextInt();
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
		pendingNodes.enqueue(root);

		while(!pendingNodes.isEmpty()){
			BinaryTreeNode<Integer> currentNode;
			try {
				currentNode = pendingNodes.dequeue();
			} catch (QueueEmptyException e) {
				return null;
			}
			int leftChildData = s.nextInt();
			if(leftChildData != -1){
				BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<Integer>(leftChildData);
				currentNode.left = leftChild;
				pendingNodes.enqueue(leftChild);
			}
			int rightChildData = s.nextInt();
			if(rightChildData != -1){
				BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<Integer>(rightChildData);
				currentNode.right = rightChild;
				pendingNodes.enqueue(rightChild);
			}
		}
		return root;
	}
	
	
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = takeInput();
		ArrayList<Node<BinaryTreeNode<Integer>>> output = LLForEachLevel(root);
		for(Node<BinaryTreeNode<Integer>> head : output){
			Node<BinaryTreeNode<Integer>> temp = head;
			while(temp != null){
				System.out.print(temp.data.data + " ") ;
				temp = temp.next;
			}
			System.out.println();
		}
	}
	
public static ArrayList<Node<BinaryTreeNode<Integer>>> LLForEachLevel(BinaryTreeNode<Integer> root) {
		
        ArrayList<Node<BinaryTreeNode<Integer>>> out=new ArrayList<>();
		QueueUsingLL<BinaryTreeNode<Integer>> q=new QueueUsingLL<>();
        q.enqueue(root);
        q.enqueue(null);
        Node<BinaryTreeNode<Integer>> head=null,tail=null,curr=null;
        BinaryTreeNode<Integer> temp=root;
        while(!q.isEmpty()){
            try{
                temp=q.dequeue();
                curr=new Node<>(temp);
            }
            catch(Exception e){
               System.out.println("Error");
            }
            if(temp==null){
                out.add(head);
                q.enqueue(null);
                head=tail=null;
            }
            else{
                if(head==null){
                    head=curr;
                    tail=curr;
                }
                else{
                    tail.next=curr;
                    tail=curr;
                }
                if(temp.left!=null)
                    q.enqueue(temp.left);
                if(temp.right!=null)
                    q.enqueue(temp.right);
            }
            
        }
        return out;

	}


}


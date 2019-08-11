package BinaryTree;

import java.util.Scanner;
import java.util.Stack;

public class PathWithgivenSumBT {
	
	public static void printPath(BinaryTreeNode<Integer> root,int k) {
		Stack<Integer> st=new Stack<>();
		printPath(root, k,0,st);
	}
	
	private static void printPath(BinaryTreeNode<Integer> root,int k,int sum,Stack<Integer> st) {
		if(root==null)
			return;
		sum+=root.data;
		st.push(root.data);
		if(sum==k) {
			printStack(st);
		}
		printPath(root.left,k,sum,st);
		printPath(root.right,k,sum,st);
		if(!st.isEmpty())
			sum-=st.pop();
		if(!st.isEmpty())
			sum-=st.pop();
		
		
	}
	private static void printStack(Stack<Integer> st) {
		int sum=0;
		while(!st.isEmpty()) {
			System.out.print(st.pop()+" ");
		}
		System.out.println();
		
	}
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
		System.out.println("Enter sum");
		int k=s.nextInt();
		printPath(root,k);
	}

}

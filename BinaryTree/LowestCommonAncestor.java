package BinaryTree;

import java.util.Scanner;

public class LowestCommonAncestor {
	
	private static BinaryTreeNode<Integer> lowestAncestor(BinaryTreeNode<Integer> root, int a, int b) {
		if(root==null)
			return null;
		if(root.data==a || root.data==b)
			return root;
		else {
			BinaryTreeNode<Integer> l=lowestAncestor(root.left, a, b);
			BinaryTreeNode<Integer> r=lowestAncestor(root.right, a, b);
			if(l!=null && r!=null)
				return root;
			else if(l==null && r==null)
				return null;
			else if(l==null)
				return r;
			else
				return l;
		}
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
		System.out.println("Enter search numbers");
		int a =s.nextInt();
		int b=s.nextInt();
		System.out.println(lowestAncestor(root,a,b).data);
	}

}

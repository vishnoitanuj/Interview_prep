package BinaryTree;

import java.util.Scanner;


public class CheckBalance {
	
	public static	 class BalanceReturn{
		int height;
		boolean ans;
	}
	
	public static boolean checkBalanced(BinaryTreeNode<Integer> root){
			
			if(root==null)
	            return true;
			return helper(root).ans;
	        
			
		}
	    
	 private static BalanceReturn helper(BinaryTreeNode<Integer> root) {
		 BalanceReturn out=new BalanceReturn();
		 if(root==null) {
			 out.height=0;
			 out.ans=true;
			 return out;
		 }
		 
		 BalanceReturn l=helper(root.left);
		 BalanceReturn r=helper(root.right);
		 int heightDiff= Math.abs(l.height-r.height);
		 out.height=Math.max(l.height, r.height)+1;
		 if(!l.ans || !r.ans || heightDiff>1) {
			 out.ans=false;
		 }
		 else
			 out.ans=true;
		 return out;
		 
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
			System.out.println(checkBalanced(root));
		}


}

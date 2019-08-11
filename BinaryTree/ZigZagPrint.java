package BinaryTree;

import java.util.Scanner;
import java.util.Stack;
import java.util.*;
public class ZigZagPrint {
	
public static void printZigZag(BinaryTreeNode<Integer> root) {
		
        if(root==null)
            return;
        Queue<BinaryTreeNode<Integer>> q=new LinkedList<>();
        Stack<BinaryTreeNode<Integer>> s=new Stack<>(); 
        q.add(root);
        int c=1;
        while(!q.isEmpty()){
            BinaryTreeNode<Integer> temp;
            temp=q.remove();
            System.out.print(temp.data+" ");
            if(c%2==0) {
            	if(temp.right!=null)
            		s.push(temp.right);
            	if(temp.left!=null)
            		s.push(temp.left);
            }
            else {
            	if(temp.left!=null)
            		s.push(temp.left);
            	if(temp.right!=null)
            		s.push(temp.right);
            }
            if(q.isEmpty()) {
            	while(!s.isEmpty()) {
            		q.add(s.pop());
            	}
            	System.out.println();
            	c++;
            }
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
	printZigZag(root);
}

}

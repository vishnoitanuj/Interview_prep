package BinaryTree;

class Node1{
	int data;
	Node1 left;
	Node1 right;
	Node1(int data){
		this.data=data;
		left=right=null;
	}
}


public class MaxSumPathleaves {
	
	static Node1 root;
	
	static class Res{
		int val;
	}
	public static int maxPathUtil(Node1 node, Res res) {
		if(node==null)
			return 0;
		if(node.left==null && node.right==null)
			return node.data;
		int ls=maxPathUtil(node.left,res);
		int rs=maxPathUtil(node.right,res);
		if(node.left!=null && node.right!=null) {
			res.val=Math.max(ls+rs+node.data, res.val);
			return Math.max(ls, rs)+node.data;
		}
		return (node.left==null)?rs+node.data:ls+node.data;
	}
	
	public static int maxPath(Node1 node) {
		Res res=new Res();
		res.val=Integer.MIN_VALUE;
		maxPathUtil(node, res);
		return res.val;
	}

	public static void main(String[] args) {
//		MaxSumPathleaves tree = new MaxSumPathleaves();
        root = new Node1(-15); 
        root.left = new Node1(5); 
        root.right = new Node1(6); 
        root.left.left = new Node1(-8); 
        root.left.right = new Node1(1); 
        root.left.left.left = new Node1(2); 
        root.left.left.right = new Node1(6); 
        root.right.left = new Node1(3); 
        root.right.right = new Node1(9); 
        root.right.right.right = new Node1(0); 
        root.right.right.right.left = new Node1(4); 
        root.right.right.right.right = new Node1(-1); 
        root.right.right.right.right.left = new Node1(10); 
        System.out.println("Max pathSum of the given binary tree is "
                + maxPath(root)); 
	}

}

import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    }
public class inorder_iterative {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Stack<TreeNode> s=new Stack<>();
        TreeNode curr=root;
        while(curr!=null || s.size()>0){
            while(curr!=null){
                s.push(curr);
                curr=curr.left;
            }
            curr=s.pop();
            out.add(curr.val);
            curr=curr.right;
        }
        return out;
    }
}
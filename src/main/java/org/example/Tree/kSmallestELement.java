package org.example.Tree;

public class kSmallestELement {
    public class TreeNode {
        int val;
         TreeNode left;
         TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val,  TreeNode left,  TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // number of node reach so far
    int count = 0;
    // in-order traveral based method : find until ++count == k
    public int kthSmallest(TreeNode root, int k) {
        TreeNode node = kthSmallestt(root, k);
        return node.val;
    }

    public TreeNode kthSmallestt(TreeNode root, int k) {
        // base case
        if(root == null)
            return null;
        TreeNode left = kthSmallestt(root.left, k);
        // if find in left sub-tree
        if(left != null)        return left;
        else if (++count == k)  return root;
        else                    return kthSmallestt(root.right, k);
    }

    public static void main(String[] args) {

    }

}

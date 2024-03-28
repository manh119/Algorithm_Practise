package org.example.Tree;

import org.example.Tree.TreeNode;
public class isValidBST {
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        boolean isLeftValid = isValidBST(root.left);
        boolean isRightValid = isValidBST(root.right);
        boolean x = true;
        if (root.left != null) {
            x = root.val > root.left.val;
        }
        boolean y = true;
        if (root.right != null) {
            y = root.val < root.right.val;
        }
        return (isRightValid && isLeftValid && x && y);

    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        t.left = left;
        t.right = right;
        isValidBST i = new isValidBST();
        System.out.println(        i.isValidBST(t)
);
    }
}

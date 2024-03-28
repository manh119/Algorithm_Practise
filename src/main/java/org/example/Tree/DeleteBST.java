package org.example.Tree;

public class DeleteBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val,   TreeNode left,   TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public    DeleteBST.TreeNode deleteNode(   DeleteBST.TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) {
            if(root.right == null) return root.left;
            if(root.left == null) return root.right;
            DeleteBST.TreeNode n = max(root.left);
            root.val = n.val;
            root.left =  deleteNode(root.left, n.val);
            return root;
        }
        else if (key > root.val) root.right = deleteNode(root.right, key);
        else root.left = deleteNode(root.left, key);
        return root;
    }

    private    DeleteBST.TreeNode max(   DeleteBST.TreeNode root) {
        if(root == null) return null;
        if(root.right == null) return root;
        else return max(root.right);
    }

    public static void main(String[] args) {
           DeleteBST i = new    DeleteBST();
           DeleteBST.TreeNode root = i.new TreeNode(40);
        root.left =    i.new TreeNode(20);
        root.right =    i.new TreeNode(60);
        root.left.left =    i.new TreeNode(10);
        root.left.right =    i.new TreeNode(30);
        root.right.left =    i.new TreeNode(50);
        root.right.right =    i.new TreeNode(70);
        i.deleteNode(root, 60);
        printInOrder(root);
    }
    // Helper method to print the BST in-order
    private static void printInOrder(   DeleteBST.TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.val + " ");
            printInOrder(node.right);
        }
    }
}

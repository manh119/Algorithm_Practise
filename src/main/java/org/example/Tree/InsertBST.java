package org.example.Tree;

public class InsertBST {
    private class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }

    public TreeNode insertIntoBST(TreeNode node, int val) {
        if(node == null) return new TreeNode(val);
        if(val > node.val) node.right = insertIntoBST(node.right, val);
        else node.left = insertIntoBST(node.left, val);
        return node;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) {
            if(root.right == null) return root.left;
            if(root.left == null) return root.right;
            TreeNode n = max(root);
            root.val = n.val;
            return deleteNode(root.right, n.val);
        }
        else if (key > root.val) root = deleteNode(root.right, key);
        else root = deleteNode(root.left, key);
        return root;
    }

    private TreeNode max(TreeNode root) {
        if(root == null) return null;
        if(root.right == null) return root;
        else return max(root.right);
    }

    public static void main(String[] args) {
        InsertBST i = new InsertBST();
        InsertBST.TreeNode root = i.new TreeNode();
        root.left =    i.new TreeNode(20);
        root.right =    i.new TreeNode(60);
//        root.left.left =    i.new TreeNode(10);
//        root.left.right =    i.new TreeNode(30);
//        root.right.left =    i.new TreeNode(50);
//        root.right.right =    i.new TreeNode(70);
        System.out.println(i.max(root).val);
    }
    // Helper method to print the BST in-order
    private static void printInOrder(InsertBST.TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.val + " ");
            printInOrder(node.right);
        }
    }

}

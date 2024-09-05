import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;          // Value of the node
    TreeNode left;   // Pointer to the left child
    TreeNode right;  // Pointer to the right child

    // Constructor to initialize the node with a value
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    private TreeNode root; // Root of the binary tree

    public BinaryTree() {
        this.root = null; // Initialize the binary tree as empty
    }

    // Method to add a value to the binary tree
    public void add(int val) {
        root = addRecursive(root, val);
    }

    // Recursive method to insert a new value in the binary tree
    private TreeNode addRecursive(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val); // Create a new node if we reach a null position
        }

        // Insert value in the left or right subtree
        if (val < node.val) {
            node.left = addRecursive(node.left, val); // Go to the left subtree
        } else if (val > node.val) {
            node.right = addRecursive(node.right, val); // Go to the right subtree
        }
        return node; // Return the unchanged node pointer
    }

    // Method to perform level-order traversal
    public ArrayList<ArrayList<Integer>> levelOrderTraversal() {
        return levelOrderTraversal(root); // Call the method with the root node
    }

    // Helper method to perform level-order traversal given the root node
    private ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode node) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (node == null) {
            return result; // Return empty list if the tree is empty
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node); // Start with the root node

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes at the current level
            ArrayList<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll(); // Get the front node in the queue
                currentLevel.add(currentNode.val); // Add the node's value to the current level

                // Add the left and right children to the queue
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            result.add(currentLevel); // Add the current level to the result
        }
        return result; // Return the level order traversal result
    }

    // Method to print the binary tree in-order
    public void printInOrder() {
        printInOrderRecursive(root);
        System.out.println(); // New line after printing
    }

    // Recursive method for in-order traversal
    private void printInOrderRecursive(TreeNode node) {
        if (node != null) {
            printInOrderRecursive(node.left); // Visit left subtree
            System.out.print(node.val + " "); // Visit node
            printInOrderRecursive(node.right); // Visit right subtree
        }
    }

    public void searchinBST(){
        int target = 7;
        boolean ans = search(root, target);
        System.out.println(ans);
        
    }

    public static boolean search(TreeNode root, int target){
        if(root==null){
            return false;
        }
        if(root.val == target){
            return true;
        }else if(root.val > target){
            return search(root.left, target); 
        }else{
            return search(root.right, target);
        }


    }
    
    public static ArrayList<Integer> Morris(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode curr = root;
        while(curr!=null){
            if(curr.left == null){
                list.add(curr.val);
                curr= curr.right;
            }else{
                TreeNode temp = curr.left;
                while(temp.right != null && temp.right != curr){
                    temp=temp.right;
                }

                if(temp.right == null){
                    temp.right = curr;
                    curr= curr.left;
                }else{
                    temp.right = null;
                    list.add(curr.val);
                    curr= curr.right;
                }
            }
        }
        return list;
    } 
    
    public void morrisInorder(){
        ArrayList<Integer> ans = Morris(root);
        System.out.println("Morris inorder traversal is: " + ans);
    }


}

// Example usage
public class Tree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); // Create a new binary tree
        tree.add(5); // Add nodes
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(1);

        // Perform level-order traversal
        ArrayList<ArrayList<Integer>> list = tree.levelOrderTraversal();

        // Print the level-order traversal
        System.out.println("Level-order traversal:");
        for (ArrayList<Integer> level : list) {
            System.out.println(level);
        }

        // Search in BST
        tree.searchinBST();

        //Morris inorder traversal
        tree.morrisInorder();
    }
}
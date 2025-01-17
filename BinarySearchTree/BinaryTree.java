import java.util.Random;

public class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    private TreeNode addNodeRecursive(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data)
            root.left = addNodeRecursive(root.left, data);
        else if (data > root.data)
            root.right = addNodeRecursive(root.right, data);

        return root;
    }

    public void addNode(int data) {
        root = addNodeRecursive(root, data);
    }

    private TreeNode removeNodeRecursive(TreeNode root, int data) {
        if (root == null)
            return root;

        if (data < root.data)
            root.left = removeNodeRecursive(root.left, data);
        else if (data > root.data)
            root.right = removeNodeRecursive(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = removeNodeRecursive(root.right, root.data);
        }

        return root;
    }
    public int minValue(TreeNode root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public void removeNode(int data) {
        root = removeNodeRecursive(root, data);
    }

    public void inorderTraversal(TreeNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }
    public TreeNode getRandomNode() {
        if (root == null)
            return null;

        Random random = new Random();
        TreeNode current = root;

        
        while (current != null && current.left != null) {
            if (random.nextBoolean()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
       
        return current;
    }
}

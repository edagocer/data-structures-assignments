public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Operations representing the adventure of the Little Fox
        tree.addNode(5);
        tree.addNode(3);
        tree.addNode(8);
        tree.addNode(1);
        tree.addNode(7);

        System.out.println("binary search tree (inorder traversal) showing the path of the fox cub ");
        tree.inorderTraversal(tree.root);

        // The little fox chose a hole in the tree trunk and got out
        TreeNode randomNode = tree.getRandomNode();
        if (randomNode != null) {
            System.out.println("\n\nLittle Fox found a hole and climbed into it: " + randomNode.data + ". It's free!");
            // The process of removing the hole from the tree, representing the little fox reaching freedom
            tree.removeNode(randomNode.data);
        } else {
            System.out.println("\n\nNo hole found. Little Fox couldn't find a way out.");
        }

        System.out.println("\nBinary search tree (inorder traversal) showing the path of the little fox: ");
        tree.inorderTraversal(tree.root);
    }
}

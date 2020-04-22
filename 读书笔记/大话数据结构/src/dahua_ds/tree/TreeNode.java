package dahua_ds.tree;


public class TreeNode {
    int value;
    ThreadingTreeNode left;
    ThreadingTreeNode right;
    TreeNode() {}
    TreeNode(int value) {
        this.value = value;
    }
    TreeNode(int value, ThreadingTreeNode left, ThreadingTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

package dahua_ds.tree;

class ThreadingTreeNode {
    int value;
    ThreadingTreeNode left;
    ThreadingTreeNode right;
    boolean ltag;
    boolean rtag;
    ThreadingTreeNode() {}
    ThreadingTreeNode(int value) {
        this.value = value;
    }
    ThreadingTreeNode(int value, ThreadingTreeNode left, ThreadingTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
public class ThreadingTree {
    private static ThreadingTreeNode previous;

    /**
     * 通过中序遍历的方式进行线索化，可以看出这个函数去掉两个if语句之后就和二叉树转双向链表没啥区别了
     * @param root
     */
    public static void InOrderThreading(ThreadingTreeNode root) {
        if (root != null) {
            InOrderThreading(root.left);
            if (root.left == null) {
                root.left = previous;
                root.rtag = true;
            }
            if (previous.right == null) {
                previous.right = root;
                previous.rtag = true;
            }
            previous = root;
            InOrderThreading(root.right);
        }
    }
}

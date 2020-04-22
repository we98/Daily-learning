package dahua_ds.tree;

import java.util.Stack;

//@LeetcodeFinished
public class TreeTravesal {

    private static void PreOrder(ThreadingTreeNode root) {
        if (root != null) {
            System.out.println(root.value);
            PreOrder(root.left);
            PreOrder(root.right);
        }
    }
    /**
     * 先序遍历
     * 循环过程：
     * 每次拿出一个root节点
     * 直接向左下访问，每访问一个节点，push右节点
     * @param root
     */
    private static void PreOrderStack(ThreadingTreeNode root) {
        if (root == null) {
            return;
        }
        Stack<ThreadingTreeNode> help = new Stack<>();
        help.push(root);
        while (!help.isEmpty()) {
            ThreadingTreeNode current = help.pop();
            while (current != null) {
                System.out.println(current.value);
                if (current.right != null) {
                    help.push(current.right);
                }
                current = current.left;
            }
        }
    }

    private static void InOrder(ThreadingTreeNode root) {
        if (root != null) {
            InOrder(root.left);
            System.out.println(root.value);
            InOrder(root.right);
        }
    }
    /**
     * 中序遍历
     * 循环过程：
     * 每次直接往左下push保存左节点
     * push到头之后，拿出root节点进行访问，在将当前root指向右节点
     * @param root
     */
    private static void InOrderStack(ThreadingTreeNode root) {
        if (root == null) {
            return;
        }
        Stack<ThreadingTreeNode> help = new Stack<>();
        while (root != null || !help.isEmpty()) {
            while (root != null) {
                help.push(root);
                root = root.left;
            }
            root = help.pop();
            System.out.println(root.value);
            root = root.right;
        }
    }

    private static void PostOrder(ThreadingTreeNode root) {
        if (root != null) {
            PostOrder(root.left);
            PostOrder(root.right);
            System.out.println(root.value);
        }
    }

    /**
     * 后序遍历
     * 循环过程：
     * 循环过程的第一步和中序遍历类似，往左下一直push保存左节点
     * 然后拿出root节点，这里就产生了不一致，因为拿出的节点不确定其右节点是否已经访问过
     * 因此，如果右节点为空，或已经等于lastAccessed变量，则可以访问当前root节点（注意，这里访问完成之后应该使root为空，否则，再次进入循环后又将当前root进行压栈保存，然而访问过的节点不需要保存了）
     * 否则，该root节点应该重新入栈，并进入到root.right右子树当中
     * @param root
     */
    private static void PostOrderStack(ThreadingTreeNode root) {
        if (root == null) {
            return;
        }
        Stack<ThreadingTreeNode> help = new Stack<>();
        ThreadingTreeNode lastAccessed = null;
        while (root != null || !help.isEmpty()) {
            while (root != null) {
                help.push(root);
                root = root.left;
            }
            root = help.pop();
            if (root.right == null || root.right == lastAccessed) {
                System.out.println(root.value);
                lastAccessed = root;
                root = null;
            }
            else {
                help.push(root);
                root = root.right;
            }
        }
    }

}

package dahua_ds.tree;

import java.util.Stack;

public class TreeTravesal {

    private static void PreOrder(Tree root) {
        if (root != null) {
            System.out.println(root.value);
            PreOrder(root.left);
            PreOrder(root.right);
        }
    }
    private static void PreOrderStack(Tree root) {
        if (root == null) {
            return;
        }
        Stack<Tree> help = new Stack<>();
        help.push(root);
        while (!help.isEmpty()) {
            Tree current = help.pop();
            while (current != null) {
                System.out.println(current.value);
                if (current.right != null) {
                    help.push(current.right);
                }
                current = current.left;
            }
        }
    }

    private static void InOrder(Tree root) {
        if (root != null) {
            InOrder(root.left);
            System.out.println(root.value);
            InOrder(root.right);
        }
    }
    private static void InOrderStack(Tree root) {
        if (root == null) {
            return;
        }
        Stack<Tree> help = new Stack<>();
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

    private static void PostOrder(Tree root) {
        if (root != null) {
            PostOrder(root.left);
            PostOrder(root.right);
            System.out.println(root.value);
        }
    }
    private static void PostOrderStack(Tree root) {

    }

}

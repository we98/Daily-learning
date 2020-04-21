package dahua_ds.tree;


public class Tree {
    int value;
    Tree left;
    Tree right;
    Tree() {}
    Tree(int value) {
        this.value = value;
    }
    Tree(int value, Tree left, Tree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

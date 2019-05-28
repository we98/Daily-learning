/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null){
            return false;
        }
        return has(root1, root2) || has(root1.left, root2) || has(root1.right, root2);
    }
    //用来判断以root1为根的树是否含有root2
    private boolean has(TreeNode root1, TreeNode root2){
        //当root2遍历到最后一层时，这一层直接返回true
        if(root2 == null){
            return true;
        }
        //root2遍历到最后一层但是root1已经没东西了，则返回false
        else if(root1 == null){
            return false;
        }
        else{
            //正式情况先比较值是否相等，再比较左子树和右子树
            return root1.val == root2.val && has(root1.left, root2.left) && has(root1.right, root2.right);
        }
    }
}
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	//两种方法殊途同归，第一个没有使用全局变量，参数的计算较为复杂，第二个则没有这么复杂
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null || pre.length != in.length || pre.length < 1){
            return null;
        }
        //return reConstructBinaryTree1(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return reConstructBinaryTree2(pre, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree1(int[] pre, int startPre, int endPre, 
                                           int[] in, int startIn, int endIn){
        if(startPre > endPre || startIn > endIn){
            return null;
        }
        int currentVal = pre[startPre];
        TreeNode currentRoot = new TreeNode(currentVal);
        for(int i = startIn; i <= endIn; ++i){
            if(in[i] == currentVal){
                currentRoot.left = reConstructBinaryTree1(pre, startPre + 1, startPre + i - startIn , in, startIn, i - 1);
                currentRoot.right = reConstructBinaryTree1(pre, startPre + i + 1 - startIn, endPre, in, i + 1, endIn);
            }
        }
        return currentRoot;
    }
    
    
    
    int preIndex = 0;
    private TreeNode reConstructBinaryTree2(int[] pre, int[] in, int startIn, int endIn){
        if(startIn > endIn){
            return null;
        }
        int currentVal = pre[preIndex++];
        TreeNode currentRoot = new TreeNode(currentVal);
        for(int i = startIn; i <= endIn; ++i){
            if(in[i] == currentVal){
                currentRoot.left = reConstructBinaryTree2(pre, in, startIn, i - 1);
                currentRoot.right = reConstructBinaryTree2(pre, in, i + 1, endIn);
            }
        }
        return currentRoot;
    }
    
}
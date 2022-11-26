package treesAndGraphs;

import java.io.IOException;


public class BinaryTreeMaximumPathSum {
    public static int maxSum = Integer.MIN_VALUE;


    public BinaryTreeMaximumPathSum() throws IOException {
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static int findMaxSum (TreeNode node) throws IOException {
        if (node == null) {
            return 0;
        }

        int gainFromLeft = Math.max(findMaxSum(node.left), 0); //0
        int gainFromRight = Math.max(findMaxSum(node.right), 0); //0

        int currentPathSum = node.val + gainFromLeft + gainFromRight; //-1
        maxSum = Math.max(currentPathSum, maxSum); //this is starting from the bottom of the tree

        return node.val + Math.max(gainFromLeft, gainFromRight);
        //BECAUSE: at the end we can only take one side for the node on the layer above this current node!
    }

    public static void main(String[] args) throws IOException {
//        TreeNode root = new TreeNode(-10,
//                new TreeNode(9, null, null), //left
//                new TreeNode(20, new TreeNode(15), new TreeNode(7))); //right
        TreeNode root = new TreeNode(-10,
                //left
                new TreeNode(-1, //1st layer
                        new TreeNode(-1, new TreeNode(-1), new TreeNode(-1)), //2nd and 3rd layers left
                        new TreeNode(-1, new TreeNode(-1), new TreeNode(-1))), //2nd and 3rd layers right

                //right
                new TreeNode(1, //1st layer
                        new TreeNode(1, new TreeNode(1), new TreeNode(-1)), // 2nd and 3rd layers left
                        new TreeNode(1, new TreeNode(1), new TreeNode(1)))); //2nd and 3rd layers right

        findMaxSum(root);
        System.out.println(maxSum);
    }

}


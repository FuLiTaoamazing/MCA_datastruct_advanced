package class03;

/**
 * @ClassName: TreeEquip.java
 * @author: FLT
 * @description:判定T2是否是T1的一颗完全结构相同个二叉树
 * @createTime: 2021年11月24日 21:31:00
 */
public class TreeEquip {
    static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, TreeNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", val=" + val +
                    '}';
        }
    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(new TreeNode(2), new TreeNode(3), 1);
        TreeNode t2 = new TreeNode(new TreeNode(2), new TreeNode(3), 1);
        System.out.println(containsTree1(t1, t2));
        System.out.println(containsTree1ForKMP(t1, t2));



    }

    //暴力解法
    //这个方法是选中一个节点作为头 来进行比较
    public static boolean containsTree1(TreeNode big, TreeNode small) {

        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        //这里的情况是当前作为头结点的节点 t2不满足 就去他的左子树上看 和右子树上看

        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    //这个方法是从选中的头结点往下依次左比较
    public static boolean isSameValueStructure(TreeNode head1, TreeNode head2) {
        // 过滤条件
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        //当 head1和head2都不为空的时候比较这节点的值不相同直接retun false；
        if (head1.val != head2.val) {
            return false;
        }


        //表示上面的条件都满足 即 head1！=null&&head2!=null&&head1.val==head2.val 就要去看
        //这两个节点的左边的节点是否满足 和右边的节点是否满足 都满足才是结构完全相同的树
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }


    public static boolean containsTree1ForKMP(TreeNode big, TreeNode small) {
        if (big == null) {
            return false;
        }
        if (small == null) {
            return true;
        }
        String str1 = preOrder(big);
        String str2 = preOrder(small);
        //这里调的是KMP算法
        return IsRotationStrDemo.getIndexOf(str1, str2)!=-1;
    }


    //利用KMP算法
    public static String preOrder(TreeNode head) {
        StringBuilder res = new StringBuilder("");
        process(head, res);

        return res.toString();
    }

    public static void process(TreeNode head, StringBuilder res) {
        if (head == null) {
            res.append("null");
            return;
        }
        res.append(head.val);
        process(head.left, res);
        process(head.right, res);
    }
}

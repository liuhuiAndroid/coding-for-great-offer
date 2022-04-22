package class08;

import java.util.LinkedList;

/**
 * 本题测试链接 : https://leetcode.com/problems/basic-calculator-iii/
 * 本题测试链接 : https://leetcode-cn.com/problems/basic-calculator-iii/
 * 给定一个字符串str，str表示一个公示
 * 公示里可能有整数，加减乘除符号和左右括号
 * 返回公示的计算结果，难点在于括号可能嵌套很多层
 * str="48*((70-65)-43)+8*1"返回-1816
 * str="3+1+4"返回7
 * str="3+(1*4)"返回7
 * 说明1：一定是正确的公式，不用检查有效性
 * 说明2：负数需要括号括起来，但是作为开头不需要
 * 说明3：不需要考虑溢出
 */
public class Code01_ExpressionCompute {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] f(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();
        int cur = 0;
        int[] bra = null;
        // 从i出发，开始撸串
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号了
                bra = f(str, i + 1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

}

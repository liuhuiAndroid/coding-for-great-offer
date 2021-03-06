package class10;

/**
 * 本题测试链接 : https://leetcode.com/problems/jump-game-ii/
 * 本题测试链接 : https://leetcode-cn.com/problems/jump-game-ii/
 * 思路是
 */
public class Code01_JumpGame {

    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 最好的方式：目前为止跳了几步？
        int step = 0;
        // 最好的方式：如果你不增加步数，最远能到哪
        int cur = 0;
        // 允许我多跳一步，最远能到哪
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

}

package partone;

/**
 * 请对类注释说明.
 *
 * @author wang_fei
 * @since 2022/5/18 9:20
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(dc(30));
    }

    // 0、1、1、2、3、5、8、13
    private static int dc(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;
        return dc(n - 1) + dc(n - 2);
    }

    // 0、1、1、2、3、5、8、13
    private static int dp(int n) {
        int[] arr = new int[n+1];
        int f1 = 0, f2 = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = f1 + f2;
            f1 = f2;
            f2 = arr[i];
        }
        return arr[n];
    }

}


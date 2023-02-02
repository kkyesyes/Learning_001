import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int arr[] = new int[15];

        System.out.println("你要输入几个数字来进行排序：(<15)");
        int n = myScanner.nextInt();

        for (int i = 0; i < n; ++i) {
            arr[i] = myScanner.nextInt();
        }

        for (int i = 0; i < (n - 1); ++i) {
            for (int j = 0; j < (n - i - 1); ++j) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
    }
}
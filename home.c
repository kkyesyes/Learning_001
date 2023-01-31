/////////////////////////////////////////////////
//algorithm file                               //
//homework_230131                              //
/////////////////////////////////////////////////
#include <stdio.h>

int main() {
    /*变量初始化0*/
    int a1 = 0;
    int a2 = 0;
    int n = 0;
    int arr[120] = {0};

    /*自定义赋值*/
    scanf("%d %d %d", &a1, &a2, &n);

    /*数组初始化*/
    arr[0] = a1;
    arr[1] = a2;

    /*循环计算填入*/
    int j = 0;
    int temp = 2;
    int multi = 0;  //乘积初始化0
    for (int i = 2; i < n; ++i) {
        multi = arr[i - 1] * arr[i - 2];  //乘积计算
        for (int j = temp; ; ++j) {  //检测空项填入
            if (arr[j] == 0) {
                if ( (multi / 10) == 0) {  //结果为一位数时赋值
                    arr[j] = multi;
                    temp = ++j;
                    break;
                }
                else {  //结果为两位数时赋值
                    arr[j] = multi / 10;
                    arr[j + 1] = multi % 10;
                    temp = j + 2;
                    break;
                }
            }
        }
    }

    /*打印输出*/
    for (int i = 0; i < n; ++i) {
        if (i !=  (n - 1) ) {
            printf("%d ", arr[i]);
        }
        else {
            printf("%d", arr[i]);  //行末无空格
        }
    }

    return 0;
}
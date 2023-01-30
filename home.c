

#include <stdio.h>
#include <string.h>

int main() {
    int arr[7][10] = {{0,0,0,0,0,0,0,0,0,0}, {0,1,0,0,0,0,0,0,0,0}, {0,1,1,0,0,0,0,0,0,0}};

    for (int i = 3; i <= 6; ++i) {
        for (int j = 1; j <= (i + 1); ++j) {
            arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
        }
    }    

    int space = 13;

    for (int i = 1; i <= 6; ++i) {
        for (int j = 0; j < space; ++j) {
            printf(" ");
        }
        for (int k = 1; k <= i; ++k) {
            printf("%-4d", arr[i][k]);
        }
        printf("\n");
        space -= 2;
    }

    return 0;
}
#include <stdio.h>
#include <string.h>

int main() {
    char word[25][12] = {0};
    int counter = 0;
    char temp[12] = {0};
    int judge = 0;

    for (int i = 0; ; ++i) {
        scanf("%s", word[i]);
        if ('#' == word[i][0]) {
            break;
        }
        else {
            ++counter;
        }
    }

    int n = counter;
    for (int i = 0; i < (n - 1); ++i) {
        for (int j = 0; j < (counter - 1); ++j) {
            int _j = strlen(word[j]);
            int j_ = strlen(word[j + 1]);
            if (_j > j_) {
                for (int k = 0; k <= (_j); ++k) {
                    temp[k] = word[j][k];
                    word[j][k] = word[j + 1][k];
                    word[j + 1][k] = temp[k];
                    judge = 1;
                }
            }
        }
        if (judge) {
            --counter;
        }
        else {
            break;
        }
    }

    for (int i = 0; i < n; ++i) {
        printf("%s ", word[i]);
    }

    return 0;
}
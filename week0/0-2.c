#include <stdio.h>

int main(void){
    int i;
    int n = 9;
    for(i = 1; i<=n;i++){
        int two = i*2;
        int three = i*3;
        printf("2 x %d = %d\t", i, two);
        printf("3 x %d = %d\n", i, three);
    }
    return 0;
}

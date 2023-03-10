#include <stdio.h>

#define MAX_ARR_SIZE 10

void printArray(int *arr){
    for(int i = 0; i< MAX_ARR_SIZE;i++){
        printf("%d ",*arr+i);
    }
    printf("\n");
}

int main(){
    int MyArr[MAX_ARR_SIZE];
    for(int i = 0; i<MAX_ARR_SIZE;i++){
        MyArr[i] = 100 + i;
    }

    printArray(MyArr);
    return 0;
}



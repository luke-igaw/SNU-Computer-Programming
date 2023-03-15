#include <iostream>
using namespace std;

void swap(int *a, int *b);
void swap(char *a, char *b);

int main(){
    int a, b;
    char c, d;
    cout << "put two numbers for swapping" << endl;
    cin >> a >> b;
    cout << "put two characters for swapping" << endl;
    cin >> c >> d;
    swap(&a,&b);
    swap(&c,&d);
    cout << "result!" << "\n" << a << " " << b << "\n" << c << " " << d << endl;
    return 0;
}

void swap(int *a, int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
    cout << "number swap!!!" << endl;
}

void swap(char *a, char *b){
    char temp = *a;
    *a = *b;
    *b = temp;
    cout << "character swap!!!" << "\n";
}
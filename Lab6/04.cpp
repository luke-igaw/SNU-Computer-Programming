#include <iostream>
#define min(a,b) ((a)<(b) ? (a) : (b))
using namespace std;

int gcd(int x, int y){
    int r;
    int small = min(x,y);
    for(int i = small; i>=1;i--){
        if(x%i == 0 && y%i == 0){
            r = i;
            break;
        }
    }
    return r;
}

int main(){
    int a,b;
    cin >> a >> b;
    int gc = gcd(a,b);
    int lc = gc * (a/gc) * (b/gc);
    cout << gc << "\n" << lc << endl;
    return 0;
}
#include <vector>
#include <iostream>
using namespace std;

int main(){
    vector<int> v;
    for(int i = 0; i<4;i++){
        v.push_back(1);
    }
    cout << &v << endl;
    cout << v.capacity() << "\n" << v.size() << endl;
    v.push_back(1);
    cout << v.capacity() << "\n" << v.size() << endl;
    v.pop_back();
    cout << v.capacity() << "\n" << v.size() << endl;
    return 0;
}
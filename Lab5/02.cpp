#include <iostream>
#include <list>
using namespace std;

bool isdiv3(const int i){
    if(i%3 == 0) return true;
    else return false;
}
int main(){
    list<int> mylist;
    for(int i = 0; i<100;i++){
        mylist.push_back(i+1);
    }
        mylist.remove_if(isdiv3);
    cout << mylist.size() << endl;
    return 0;
}
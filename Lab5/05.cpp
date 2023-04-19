#include <iostream>
#include <list>

using namespace std;

int main(){
    int N = 7, K = 3;
    list<int> mylist;

    for(int i = 1; i <= N; i++){
        mylist.push_back(i);
    }
    list<int>::iterator itr = mylist.begin();

    while(!mylist.empty()){
        for(int i = 0; i<K-1;i++){
            itr++;
            if(itr == mylist.end()) itr = mylist.begin();
        }
        cout << *itr << " ";
        mylist.erase(itr++);
        if(itr == mylist.end()) itr = mylist.begin();
    }
    cout << "\n";
    return 0;
}
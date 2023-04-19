/*
Josephus Problem
if you want to make the code more general
you can replace int n = 7, k = 3 with cin >> n >> k;
*/

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

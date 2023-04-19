#include <iostream>
#include <vector>
#include <random>
#include <algorithm>
#include <list>

using namespace std;

int main(){
    vector<int> v(100);
    for(int i = 0; i<100;i++){
        v[i] = i+1;
    }
    random_device rd;
    mt19937 g(rd());
    shuffle(v.begin(), v.end(), g);

    list<int> mylist;
    list<int>::iterator it;

    for(int num: v){
        if(mylist.empty()){
            mylist.push_back(num);
            continue;
        }
        if(num < mylist.front()){
            mylist.push_front(num);
            continue;
        }
        if(num > mylist.back()){
            mylist.push_back(num);
            continue;
        }
        for(it = mylist.begin(); it != mylist.end(); it++){
            if(num < *it){
                mylist.insert(it, num);
                break;
            }
        }
    }
    for(int e: mylist){
        cout << e << " ";
    }
    cout << "\n";
}
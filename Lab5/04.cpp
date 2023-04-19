#include <iostream>
#include <set>
#include <vector>

using namespace std;

int main(){
    vector<int> v = {9, 7, 2, 3, 4, 2, 2, 4, 8, 1, 9};
    set<int> s;
    pair<set<int>::iterator,bool> ret;

    for(int i : v){
        ret = s.insert(i);
        if(ret.second == 0) cout << "중복된 값 : " << i << endl;
    }
    cout << "set : ";
    for(int i : s){
        cout << i << " ";
    }
    cout << "\n";
}
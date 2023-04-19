#include <iostream>
#include <list>
using namespace std;

int main(){
    list<int> mylist = {1,2,3,4,5};
    list<int>::iterator itr;
    itr = mylist.begin();
    mylist.push_back(6);
    mylist.push_front(0);
    ++itr;
    mylist.insert(itr,10);
    cout << "Size of mylist: " << mylist.size() << endl;
    for(itr =  mylist.begin(); itr != mylist.end(); itr++){
        cout << ' ' << *itr;
    }
    cout << '\n';
    return 0;
}
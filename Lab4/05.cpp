#include <iostream>
#include <vector>
using namespace std;

int main(){
    vector<int> v(4); // Creates a vector of size 4 with default value 0
    v[0] = 10;
    v[1] = 11;
    v[2] = 9;
    v[3] = 5;

    vector <int>::iterator itr;
    for(itr = v.begin(); itr != v.end();itr++){
        cout << *itr << endl;
    }
    cout << "size: " << v.size() << endl;
    cout << "capacity: " << v.capacity() << endl;
    
    return 0;
}
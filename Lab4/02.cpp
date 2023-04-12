#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(){
    ifstream myFile("example01.txt");
    string s;

    cout << "Read File: " <<endl;
    if(myFile.is_open()){
        while(getline(myFile,s)){
            cout << s << endl;
        }
    }
    return 0;
}
#include <fstream>
#include <iostream>
using namespace std;

int main(){
    ifstream myFile("Example01.txt");
    ofstream outFile("Excercise01.txt");
    string s;

    if(myFile.is_open()){
        while(getline(myFile, s)){
            outFile << s << endl;
        }
    }
    return 0;
}
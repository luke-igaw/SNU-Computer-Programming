#include <iostream>
#include <fstream>
using namespace std;

int main(){
    ofstream myFile("Example01.txt");

    myFile << "writing this to a file.\n";
    myFile.close();
    return 0;
}
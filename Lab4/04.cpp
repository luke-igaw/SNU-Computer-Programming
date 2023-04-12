#include <fstream>
#include <iostream>
using namespace std;

int main(){
    ofstream ofs("example02.txt");
    ofs << "0123456789";
    ofs.close();
    ifstream ifs("example02.txt");
    int start = ifs.tellg();
    ifs.seekg(0, ios::end);
    int end = ifs.tellg();
    cout << end-start << endl;
    ifs.close();
    return 0;
}
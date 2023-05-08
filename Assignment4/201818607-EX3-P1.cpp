#include <iostream>
#include <string>
#include <fstream>
using namespace std;

int main(){
    ifstream input("p1input.txt");
    ofstream output("2018-18607-p1output.txt");
    int T;
    input >> T;
    int S[T+1];
    int index = -1;

    for(int i = 0; i<T; i++){
        string cmd;
        input >> cmd;
        int num;
        if(cmd.compare("push") == 0) {
            index++;
            input >> num;
            S[index] = num;
        }
        else if(cmd.compare("pop") == 0){
            if(index == -1) output << "-1" << endl;
            else{
                output << S[index] << endl;
                S[index] = 0;
                index--;
            }
        }
        else if(cmd.compare("size") == 0) output << index+1 << endl;
        else if(cmd.compare("empty") == 0){
            if(index == -1) output << 1 << endl;
            else output << 0 << endl;
        }
        else if(cmd.compare("top") == 0){
            if(index == -1) output << -1 << endl;
            else output << S[index] << endl;
        }
    }
    input.close();
    output.close();
    return 0;
}
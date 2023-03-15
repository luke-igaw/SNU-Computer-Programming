#include <iostream>
using namespace std;

void convertAlphabet(char alphabet){
    cout << "input: " << alphabet << endl;
    if(alphabet >= 'a' && alphabet <= 'z'){
        alphabet = alphabet -'a' + 'A';
    } 
    else{
        alphabet = alphabet -'A' + 'a';
    }
    cout << "output: " << alphabet << endl;
}

int main(){
    char alphabet;
    cout << "Enter Capaital or small letter: " << endl;
    cin >> alphabet;
    convertAlphabet(alphabet);
    return 0;
}
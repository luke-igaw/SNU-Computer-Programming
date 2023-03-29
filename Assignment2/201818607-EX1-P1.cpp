#include <iostream>
#include <string>
#include <cstring>
using namespace std;

class Equation{
public:
    int x = 0;
    int y = 0;
    int z = 0;
    Equation(int a){
        z = a;
    }
    Equation(int a, int b){
        y = a;
        z = b;
    }
    Equation(int a, int b, int c){
        x = a;
        y = b;
        z = c;
    }
};

class EquationUtility{
public:
    Equation add(Equation e1, Equation e2){
        int a = e1.x + e2.x;
        int b = e1.y + e2.y;
        int c = e1.z + e2.z;
        Equation sum(a,b,c);
        return sum;
    }
    string output(Equation e){
        string str;
        if (e.x != 0){
            str = to_string(e.x) + "x^2";
        }
        if (e.x != 0 && e.y > 0) {
            str += "+" + to_string(e.y) + "x";
        } else if (e.y < 0 || e.y > 0) {
            str += to_string(e.y) + "x";
        }
        if ((e.x != 0 || e.y != 0) && e.z > 0) {
            str += "+" + to_string(e.z);
        } else if (e.z < 0 || e.z > 0) {
            str += to_string(e.z);
        }
        return str;
    }
};

int main(){
    Equation e1(2);
    Equation e2(4,-5);
    EquationUtility a;
    Equation result = a.add(e1,e2);
    cout << a.output(result) << endl;
    Equation e3(3,0,5);
    result = a.add(e1, e3);
    cout << a.output(result) << endl;
    return 0;
}
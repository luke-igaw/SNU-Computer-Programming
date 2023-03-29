#include <iostream>
#include <string>
#include <cstring>
using namespace std;

class Salad{
protected:
    int price;
public:
    Salad(string what){
        if(what.compare("chicken") == 0){
            price = 8500;
        }
        else{
            price = 9000;
        }
    }
    void addSomething(string food){
        if(food.compare("avocado") == 0) price += 2000;
        else price += 1000;
    }
    void showPrice(){
        cout << "price : " << price << "원" << endl; 
    }
};

class Sandwich : public Salad{
public:
    Sandwich(int cm, string what) : Salad(what){
        if(what.compare("chicken") == 0){
            price = 7500;
        }
        else{
            price = 8000;
        }
        price *= (cm/30);
    }
};

int main(){
    int num;
    cout << "샐러드 주문은 1, 샌드위치 주문은 2" << endl;
    cin >> num;
    if(num == 1){
        Salad salad1("chicken");
        salad1.addSomething("cheese");
        salad1.showPrice();
    }
    else if(num == 2){
        Sandwich sandwich1(30,"turkey");
        sandwich1.addSomething("avocado");
        sandwich1.showPrice();
    }
}
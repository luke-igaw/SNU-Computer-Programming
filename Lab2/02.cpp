#include <iostream>
using namespace std;

class Animal{
public:
    virtual void speak(){
        cout << "can't speak" << endl;
    }
};

class Cat : public Animal{
public:
    void speak(){
        cout << "meow" << endl;
    }    
};

class Dog : public Animal{
public:
    void speak(){
        cout << "woof" << endl;
    }
};

class Fish : public Animal{};

int main(){
    Animal* a = new Animal();
    int n;
    while(true){
        cout << "1.Cat 2.Dog 3.Fish 4.Exit\n Select:";
        cin >> n;
        if(n == 1) a = new Cat();
        else if(n == 2) a = new Dog();
        else if(n == 3) a = new Fish();
        else if(n == 4) return 0;
        else{
            cout << "Wrong input" << endl;
            return 0;
        }
        a->speak();
    }
}
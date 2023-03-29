#include <iostream>
using namespace std;

class Car{
protected:
    string name;
    int speed = 0, totaldistance = 0, hour = 0, totalhour = 0;
public:
    Car(string _name){
        name = _name;
    }
    void drive(int _speed, int _hour){
        speed = _speed;
        hour = _hour;
        totalhour += hour;
        totaldistance += (speed*hour);
    }
    void showDistance(){
        cout << name << " has driven " << totaldistance << "km" << endl;
    }
    void showHourDriven(){
        cout << name << " has driven " << totalhour << "hour" << endl;
    }
};

class Avante : public Car{
private:
    int remainingdistance = 500;
public:
    Avante() : Car("Avante"){}
    void status(){
        cout << name << " can drive " << (remainingdistance - totaldistance) << "km more" << endl;
    }
};

int main(){
    Avante c;
    c.drive(40,2);
    c.showHourDriven();
    c.drive(80,1);
    c.showDistance();
    c.status();
    return 0;
}
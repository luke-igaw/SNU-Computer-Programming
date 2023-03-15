#include "Fishbread.cpp"
using namespace std;

int main(){
    Fishbread fish1;
    Fishbread fish2("white");
    Fishbread fish3(300);
    Fishbread fish4(500, "read bean");

    cout << "how much and what fishbraed1? " << fish1.getCost() << " " << fish1.getContent() << endl;
    fish1.setCost(800);
    cout << "how much and what fishbraed1? " << fish1.getCost() << " " << fish1.getContent() << endl;
    cout << "how much and what fishbraed2? " << fish2.getCost() << " " << fish2.getContent() << endl;
    cout << "how much and what fishbraed3? " << fish3.getCost() << " " << fish3.getContent() << endl;
    cout << "how much and what fishbraed4? " << fish4.getCost() << " " << fish4.getContent() << endl;
    cout << endl;
    return 0;
}
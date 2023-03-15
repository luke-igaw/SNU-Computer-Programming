#include "Fishbread.h"
using std::cout;
using std::cin;
using namespace std;

Fishbread::Fishbread(){
    cost = 100;
    content = "nobrand fishbread";
}

Fishbread::Fishbread(int argCost, string argContent){
    cost = argCost;
    content = argContent;
}

Fishbread::Fishbread(int argCost){
    cost = argCost;
    content = "nobrand fishbread";
}

Fishbread::Fishbread(string argContent){
    cost = 100;
    content = argContent;
}

Fishbread::~Fishbread(){
    //empty
}

int Fishbread::getCost(){
    return cost;
}

void Fishbread::setCost(int c){
    cost = c;
}

string Fishbread::getContent(){
    return content;
}
#include "monster.h"
#include "monster.cpp"
#include <string>
#include <stdio.h>

int main(){
    Monster m1 = Monster(100, 8, 10, "꼬북");
    cout << "현재 몬스터 수는" << Monster::get_num() << endl;
    Monster *m2;
    m2 = new Monster(200, 5, 7, "피카츄");
    cout << "현재 몬스터 수는" << Monster::get_num() << endl;
    Monster *m3;
    m3 = new Monster(150, 3, 20, "이상해씨");
    cout << "현재 몬스터 수는" << Monster::get_num() << endl;
    m1.attack(m2);
    m2->critical_attack(&m1);
    m2->critical_attack(&m1);
    m2->critical_attack(m3);
    m2->critical_attack(&m1);
    m1.critical_attack(m2);
    m1.get_info();
    m2->get_info();
    m3->get_info();

    while(1){
        if(m2->get_hp() == 0 || m3->get_hp() == 0) break;
        m2->attack(m3);
        m3->attack(m2);
    }
    m2->get_info();
    m3->get_info();
}
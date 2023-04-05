#include "Monster.h"
int Monster::num_monsters = 0;

Monster::Monster(hp_t hp, hp_t damage, hp_t critical_damage, mon_name name)
    : hp(hp), damage(damage), critical_damage(critical_damage), name(name){
        num_monsters++;
    };

void Monster::decrease_hp(hp_t attack_damage){
    hp -= attack_damage;
    if(hp<0) hp = 0;
};

hp_t Monster::get_hp(){
    return hp;
};

void Monster::get_info(){
    if(hp>0){
        std::cout << name << "의 현재 체력은: " << hp << ", 남은 치명타는: " << critical_left << std::endl;
    }
    else{
        std:cout << name << "는 쓰러졌다" << std::endl;
    }
};

int Monster::get_num(){
    return num_monsters;
};

void Monster::attack(Monster *attacked_monster){
    attacked_monster -> decrease_hp(this -> damage);
    cout << this -> name << "가 " << attacked_monster -> name << "를 공격했다!" << endl;
    cout << attacked_monster -> name << "의 남은 체력은 " << attacked_monster -> hp << endl;
};

void Monster::critical_attack(Monster *attacked_monster){
    if(critical_left <= 0){
        cout << name << "은(는) 치명타를 모두 사용했다."<< endl;
    }
    else{
        attacked_monster -> decrease_hp(this -> critical_damage);
        cout << this -> name << "(이)가 " << attacked_monster -> name << "에게 치명타를 가했다!" << endl;
        if(attacked_monster -> hp > 0) cout << attacked_monster -> name << "의 남은 체력은 " << attacked_monster -> hp << "!" << endl;
        else cout << attacked_monster -> name << "가 쓰러졌다!" << endl;
        this -> critical_left--;
    }
};


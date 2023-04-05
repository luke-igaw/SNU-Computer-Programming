#ifndef POKEMON_MONSTER_H;
#define POKEMON_MONSTER_H;
#include <iostream>
using namespace std;
typedef int hp_t;
typedef string mon_name;

class Monster{
private:
    hp_t hp;
    hp_t damage;
    hp_t critical_damage;
    int critical_left = 3;
    static int num_monsters;

protected:
    mon_name name;
    void decrease_hp(hp_t attack_damage);

public:
    Monster(hp_t hp, hp_t damage, hp_t critical_damage, mon_name name);

    void attack(Monster *attacked_monster);
    void critical_attack(Monster *attacked_monster);
    hp_t get_hp();
    void get_info();
    static int get_num();
};
#endif
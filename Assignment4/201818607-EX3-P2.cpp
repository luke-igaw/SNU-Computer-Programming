#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <list>

using namespace std;

int main() {
    ifstream input("p2input.txt");
    ofstream output("2018-18607-p2output.txt");
    int N, M;
    input >> N >> M;
    list<int> stations;

    for (int i = 0; i < N; i++) {
        int station;
        input >> station;
        stations.push_back(station);
    }

    for (int i = 0; i < M; i++) {
        string op;
        int x, y;
        input >> op >> x;

        list<int>::iterator it;
        for (it = stations.begin(); it != stations.end(); it++) {
            if (*it == x) break;
        }

        if (op.compare("BN") == 0) {
            input >> y;
            list<int>::iterator next_it = (it == stations.end() || next(it) == stations.end()) ? stations.begin() : next(it);
            output << *next_it << endl;
            stations.insert(next_it, y);
        } 
        else if (op.compare("BP") == 0) {
            input >> y;
            list<int>::iterator prev_it = (it == stations.begin()) ? prev(stations.end()) : prev(it);
            output << *prev_it << endl;
            stations.insert(it, y);
        } 
        else if (op.compare("CN") == 0) {
            if (it != stations.end()) {
                list<int>::iterator next_it = (next(it) == stations.end()) ? stations.begin() : next(it);
                output << *next_it << endl;
                stations.erase(next_it);
            }
        } 
        else if (op.compare("CP") == 0) {
            if (it != stations.begin()) {
                list<int>::iterator prev_it = (it == stations.begin()) ? prev(stations.end()) : prev(it);
                output << *prev_it << endl;
                stations.erase(prev_it);
            }
        }
    }
    input.close();
    output.close();

    return 0;
}

/*
6 6
3 9 11 8 6 14
CN 9
BP 8 2
BP 3 7
CP 2
CN 2
BN 6 8
*/

/*
11
9
14
9
8
14
*/

//3 9 11 8 6 14
//3 9 8 6 14 -- 출력 11
//3 9 2 8 6 14 -- 출력 9
//7 3 9 2 8 6 14 -- 출력 14
//7 3 2 6 14 -- 출력 9 -> 출력 8
//7 3 2 6 8 14 -- 출력 14
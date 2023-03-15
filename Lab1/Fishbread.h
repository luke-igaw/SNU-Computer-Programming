#include <iostream>
#include <string>
using std::string;
class Fishbread{
    public:
    Fishbread();
    Fishbread(int argCost);
    Fishbread(string argContent);
    Fishbread(int argCost, string argContent);
    ~Fishbread();

    int getCost();
    void setCost(int c);
    string getContent();

    private:
        int cost;
        string content;
};
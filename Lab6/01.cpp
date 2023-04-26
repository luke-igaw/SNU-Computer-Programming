#include <iostream>

struct Base {
    std::string base_string;
    Base(std::string val) : base_string(val){
        std::cout << "Base : " << val << "\n"; 
    }
    virtual ~Base(){
        std::cout << "Base : " << base_string << "\n";
    }
};

struct Derived : public Base{
    std::string derived_string;
    Derived(std::string val) : Base(val), derived_string(val){
        std:: cout << "Derived : " << val << "\n";
    }
    virtual ~Derived(){
        std::cout << "~Derived : " << derived_string << "\n";
    }
};

int main(){
    Base thing1("thing");
    Derived* p;
    {
        p = new Derived("p_inside");
        Derived inner_thing("inside");
    }
    Derived inner_thing("inside");
    delete p;
}
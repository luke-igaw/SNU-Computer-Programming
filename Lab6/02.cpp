#include <iostream>
class C{
    public:
    int n;
    C(int n = 1): n(n) { }
    C(const C&c) : n(c.n) {
        std::cout << "copy con" << std::endl;
    }
};

void f_get_C(C c){
    std::cout << "function_f" << std::endl;
}

C f_return_C() {
    return C();
}

int main(){
    C c1(10);
    C c2(c1);
    C c3 = c1;
    f_get_C(c1);
    f_return_C();
}
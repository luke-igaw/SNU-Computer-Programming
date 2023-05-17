#ifndef BANKSYSTEM_H
#define BANKSYSTEM_H
#include "BankAccount.h"

class BankSystem {
    public:
        void transaction(BankAccount* from, BankAccount* to, float amount){
            string frombank = from->getBankname();
            string tobank = to->getBankname();
            if(strcmp(frombank.c_str(), tobank.c_str()) == 0){
                if(from -> withdraw(amount) == 1){
                    to -> deposit(amount);
                }
            }
            else{
                if(from -> withdraw(amount + 5.0) == 1){
                    to -> deposit(amount);
                }
            }
        } //electronic money transaction; there are sender and receiver 
        void deposit(BankAccount* b, float amount){
            b -> deposit(amount);
        } //person puts cash into his or her account
        void withdraw(BankAccount* b, float amount){
            b -> withdraw(amount);
        } //person takes cash out of his or her account
        void loan(BankAccount* b, float amount){
            if(b -> getcredit() == 1){
                cout << "The amount cannot be loaned" << endl;
                return;
            }
            else if(b -> getcredit()  == 2){
                if(amount > 500 || amount < 100){
                    cout << "The amount cannot be loaned" << endl;
                    return;
                }
                else{
                    b -> deposit(amount*0.9);
                    return;
                }
            }
            else if(b -> getcredit()  == 3){
                if(amount > 1000 || amount < 100){
                    cout << "The amount cannot be loaned" << endl;
                    return;
                }
                else{
                    b -> deposit(amount*0.95);
                    return;
                }
            }
        }//person takes out a loan
};

#endif
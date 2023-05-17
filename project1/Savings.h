#ifndef SAVINGS_H
#define SAVINGS_H

#include "BankAccount.h"

class Savings : public BankAccount {
    public:
        Savings(int num=0,float bal=0,float rate=3.5) : BankAccount(num, bal) {
            acctnum = num;
            this -> bal = bal;
            intrate = rate;
        }
        void interest(){
            bal += (bal*(intrate/100/12));
            updateCredit();
        } // add monthly interest to current balance
        int withdraw(float amount){
            if(amount >= bal){
                cout << "Cannot withdraw $" << amount << " on account #" << acctnum << " because the balance is low." << endl;
                return 0;
            }
            else{
                bal -= amount;
                updateCredit();
                return 1;
            }
        }
        virtual void print(){
            cout << "Savings Account: " << acctnum << endl;
            cout << "   BankName: " << bank_name << endl;
            cout << "   Credit: " << credit << endl;
            cout << "   Balance: " << bal << endl;
            cout << "   Interest: " << intrate << "%" << "\n" << endl;
        }
    protected:
        float intrate; //interest rate(이자율)
};

#endif
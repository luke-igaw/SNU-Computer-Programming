#ifndef CHECKING_H
#define CHECKING_H

#include "BankAccount.h"

class Checking : public BankAccount{
    public:
        Checking(int num=0,float bal=0,float min=1000,float chg=2) : BankAccount(num, bal) {
            acctnum = num;
            this -> bal = bal;
            minimum = min;
            charge = chg;
        }
        int withdraw(float amount){
            if(amount >= bal){
                cout << "Cannot withdraw $" << amount << " on account #" << acctnum << " because the balance is low." << endl;
                return 0;
            }
            else{
                if(bal < minimum){
                    bal -= (amount + charge);
                    updateCredit();
                }
                else{
                    bal -= amount;
                    updateCredit();
                }
                return 1;
            }
        }
        virtual void print(){
            cout << "Checking Account: " << acctnum << endl;
            cout << "   BankName: " << bank_name << endl;
            cout << "   Credit: " << credit << endl;
            cout << "   Balance: " << bal << endl;
            cout << "   Minimum to Avoid Charges: " << minimum << endl;
            cout << "   Charge per Check: " << charge << "\n" << endl;
        }
    protected:
        //minimum account balance to keep(유지되야 하는 최소한의 잔고)
        float minimum; 
        // amount of money charged when balance is below minimum(minimum이 유지안되었을 때 발생하는 벌금)
        float charge;
};

#endif
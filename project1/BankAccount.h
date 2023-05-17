#ifndef BANKACCOUNT_H
#define BANKACCOUNT_H
#include <string>
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

class BankAccount{
    public:
        BankAccount(int num,float bal){
            acctnum = num;
            this -> bal = bal;
            if(bal < 1000) credit = 1;
            else if(bal >= 1000 && bal < 2000) credit = 2;
            else if(bal >= 2000) credit = 3;
            int randnum = rand()%3;
            if(randnum == 0) bank_name = "하나";
            else if(randnum == 1) bank_name = "우리";
            else if(randnum == 2) bank_name = "신한";
        }
        void deposit(float amount){
            bal += amount;
            updateCredit();
        } //money getting into account
        int getAcctnum(){
            return acctnum;
        } // getter function
        float getBalance(){
            return bal;
        };// getter function
        int getcredit(){
            return credit;
        } // getter function
        string getBankname(){
            return bank_name;
        } // getter function
        virtual int withdraw(float amount){
            bal -= amount;
            updateCredit();
            return 0;
        } // money getting out of account
        void loan(float amount){
            if(credit == 1){
                cout << "The amount cannot be loaned" << endl;
                return;
            }
            else if(credit == 2){
                if(amount > 500 || amount < 100){
                    cout << "The amount cannot be loaned" << endl;
                    return;
                }
                else{
                    bal += (amount*0.9);
                    updateCredit();
                    return;
                }
            }
            else if(credit == 3){
                if(amount > 1000 || amount < 100){
                    cout << "The amount cannot be loaned" << endl;
                    return;
                }
                else{
                    bal += (amount*0.95);
                    updateCredit();
                    return;
                }
            }
        }
        void updateCredit() { 
            if (bal < 1000) {
                credit = 1;
            } else if (bal >= 1000 && bal < 2000) {
                credit = 2;
            } else if (bal >= 2000) {
                credit = 3;
            }
        } // for update credit when the account's balanec is updated.
        virtual void print() = 0;
    protected:
        int acctnum; // account number(계좌번호)
        float bal; //current balance of account(현재 잔고)
        string bank_name; 
        //%3 == 0 하나, == 1 우리, == 2 신한 
        //같은 은행이면 수수료 0, 다른 은행이면 5
        int credit;
};

#endif
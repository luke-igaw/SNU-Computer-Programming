#ifndef INTERESTCHECKING_H
#define INTERESTCHECKING_H

#include "BankAccount.h"
#include "Checking.h"

class InterestChecking : public Checking {
    public:
        InterestChecking(int num=0,float bal=0,float cmin=1000,float imin=2500,float chg=2,float rate=2.5,float monchg=10) : Checking(num, bal, cmin, chg) {
            acctnum = num;
            this -> bal = bal;
            minimum = cmin;
            minint = imin;
            charge = chg;
            intrate = rate;
            moncharge = monchg;
        }
        /*
        minint: minimum checking balance to get interest
        moncharge: monthly charge when balance is below minint
        minimin: minimum account balance to keep
        charge: amount of money charged when balance is below minimum (when withdrawing)
        */
        void interest(){
            if(bal < minint) {
                bal -= moncharge;
            }
            else{
                bal += (bal*(intrate/100/12));
            }
            updateCredit();
        } // add monthly interest to current balance
        virtual void print(){
            cout << "Checking Account: " << acctnum << endl;
            cout << "   BankName: " << bank_name << endl;
            cout << "   Credit: " << credit << endl;
            cout << "   Balance: " << bal << endl;
            cout << "   Minimum to Avoid Charges: " << minimum << endl;
            cout << "   Charge per Check: " << charge << endl;
            cout << "   Minimum balance for getting interest and No Monthly Fee: " << minint << endl;
            cout << "   Interest: " << intrate << "%" << endl;
            cout << "   Monthly Fee: " << moncharge << "\n" << endl;
        }
    protected:
        float intrate; // interest rate(이자율)
        float minint; // minimum checking balance to get interest(이자를 받는데 있어서 요구되는 최소한의 잔고)
        float moncharge; //monthly charge when balance is below minint(매월 이자 발생 시 최소한의 잔고가 없을 때 부과되는 벌금)
};

#endif
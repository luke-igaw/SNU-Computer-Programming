#include <stdio.h>

typedef struct Vehicle {    		// User-defined data type
    char name[10];
    int numOfWheels;
    void (*Setup)(struct Vehicle* veh, char* ch, int n);			// 'Setup' field
    void (*Stat)(struct Vehicle* veh);			// 'Stat' field
} myVehicle;    			

void mySetup(myVehicle* pv, char* name, int n);
void myStat(myVehicle* pv);
void initVehicle(myVehicle* pv);

void mySetup(myVehicle* pv, char* name, int n)
{
    int i = 0;

    printf("Setup: %s with %d wheels\n", name, n);

    while(name[i] != '\0' && i<10)
    {
    	pv->name[i] = name[i]; 			// Copy string to 'name' field
    	i++;
    }
    pv->name[i] ='\0'; //I added it to prevent � in name
    pv->numOfWheels = n;
}

void myStat(myVehicle* pv)
{
    printf("Stat: %s has %d wheels\n", pv->name, pv->numOfWheels);
}

void initVehicle(myVehicle* pv)
{
    pv -> Setup = mySetup;
    pv -> Stat = myStat;
}

int main()
{
    myVehicle Bus, Bike;

    initVehicle(&Bus);
    initVehicle(&Bike);

    Bus.Setup(&Bus, "Bus",4);
    Bus.Stat(&Bus);

    Bike.Setup(&Bike, "Bike", 2);
    Bike.Stat(&Bike);

    return 0;
}


//reference: https://stackoverflow.com/questions/17052443/c-function-inside-struct
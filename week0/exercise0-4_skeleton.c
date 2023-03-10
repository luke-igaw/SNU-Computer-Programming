#include <stdio.h>

... Vehicle {    		// User-defined data type
    char name[10];
    int numOfWheels;
    ...			// 'Setup' field
    ...			// 'Stat' field
} ... ;    			

void mySetup( ... );
void myStat( ... );
void initVehicle( ... );

void mySetup( ... )
{
    int i = 0;

    printf("Setup: %s with %d wheels\n", ... );

    while(name[i] != '\0' ... )
    {
    	pv->name[i] = ... 			// Copy string to 'name' field
    	i++;
    }
    ...
    pv->numOfWheels = n;
}

void myStat( ... )
{
    printf("Stat: %s has %d wheels\n", ... );
}

void initVehicle( ... )
{
    ...
}

int main()
{
    VEHICLE Bus, Bike;

    initVehicle( ... );
    initVehicle( ... );

    Bus.Setup( ... );
    Bus.Stat( ... );

    Bike.Setup( ... );
    Bike.Stat( ... );

    return 0;
}
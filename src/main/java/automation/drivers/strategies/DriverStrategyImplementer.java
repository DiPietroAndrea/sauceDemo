package automation.drivers.strategies;

import automation.utils.Constants;

public class DriverStrategyImplementer {
    public static DriverStategy chooseStrategy (String driver) {

        //Attraverso uno switch facciamo scegliere al programma quale tipo di driver utilizzare

        switch(driver){
            case Constants.CHROME:
                return new Chrome();

            case Constants.FIREFOX:
                return new Firefox();

            default:
                return null;
        }
    }
}

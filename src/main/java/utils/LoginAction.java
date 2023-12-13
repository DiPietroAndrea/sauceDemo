package utils;

import pages.*;

public class LoginAction extends Action {
    private static LoginInPage loginPage;
    private static HomePage homePage;

    public LoginAction() {
        super(); //Chiama il costruttore del padre

    }

    public void enterLoginPage() {
     Action.driver.get(Constants.URL);
    }


}



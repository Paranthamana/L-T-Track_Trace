package com.zebra.rfid.CommonFunctions;

public class Constants {


    private static final Constants commonLanguageInstance = new Constants();

    public static Constants getInstance() {
        return commonLanguageInstance;
    }

    //--- Dummy text ---> home = ((Language.equals("1")) ? "other lanugage text here" : "Home");
    //----All text Common class
    public static String RememberMe ="";
    public static String UserNameEmpty ="";
    public static String PasswordEmpty ="";
    public static String CheckInternet ="";
    public static String pleaseClickBackAgainToExit ="";
    public static String DBName = "Track";
    public static String checkUserNameAndPassword = "";
    public static String login_api_key = "";
    public static String user_id = "";
    public static String YouAreInOnline = "";

    public void languageConstants(){
        RememberMe = "Send an Email to the admin regrading reset of Password";
        UserNameEmpty = "Enter Correct User Name";
        PasswordEmpty = "Enter Correct Password";
        CheckInternet = "Check Your internet Connection";
        pleaseClickBackAgainToExit = "please Click Back Again To Exit";
        checkUserNameAndPassword = "Check username and password";
        YouAreInOnline = "You are in online..turn off your Internet connection";
    }

}

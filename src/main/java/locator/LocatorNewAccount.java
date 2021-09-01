package locator;

public class LocatorNewAccount {

    public String navLinkNewAccount = "//a[@href=\"addAccount.php\"]";

    //  Form elements - Add new account
    public String inputCustomerId = "//input[@type=\"text\"][@name=\"cusid\"]";
    public String selectAccountType = "//select[@name=\"selaccount\"]";
    public String initialDeposit = "//input[@type=\"text\"][@name=\"inideposit\"]";
    public String buttonSubmit = "//input[@type=\"submit\"]";

    public String messageSuccessAccountReg = "//p[contains(text(),\"Account Generated Successfully!!!\")]";
    public String accountId = "//table[@id=\"account\"]/tbody/tr[4]/td[2]";

}

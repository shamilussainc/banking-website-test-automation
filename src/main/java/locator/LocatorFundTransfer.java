package locator;

public class LocatorFundTransfer {
    public String navItemFundTransfer = "//a[@href=\"FundTransInput.php\"]";
    public String inputPayersAccountNo = "//input[@name=\"payersaccount\"]";
    public String inputPayeesAccountNo = "//input[@name=\"payeeaccount\"]";
    public String inputAmount = "//input[@name=\"ammount\"]";
    public String inputDescription = "//input[@name=\"desc\"]";
    public String buttonSubmit = "//input[@name=\"AccSubmit\"][@type=\"submit\"]";
    public String fundTransferDetails = "//p[text()=\"Fund Transfer Details\"]";

}

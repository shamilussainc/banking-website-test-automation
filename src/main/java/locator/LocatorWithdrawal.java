package locator;

public class LocatorWithdrawal {
    public String navIconWithdrawal = "//a[@href=\"WithdrawalInput.php\"]";
    public String inputAccountNo = "//input[@name=\"accountno\"]";
    public String inputAmount = "//input[@name=\"ammount\"]";
    public String inputDescription = "//input[@name=\"desc\"]";
    public String buttonSubmit = "//input[@name=\"AccSubmit\"]";

    public String titleWithdrawalDetails = "//p[@class = \"heading3\"]";
    public String transactionId = "//table[@id=\"withdraw\"]/tbody/tr[6]/td[2]";
}

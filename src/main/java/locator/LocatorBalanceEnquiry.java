package locator;

public class LocatorBalanceEnquiry {
    public String navItemBalanceEnquiry = "//a[@href=\"BalEnqInput.php\"]";
    public String inputAccountNum = "//input[@name=\"accountno\"]";
    public String buttonSubmit = "//input[@name=\"AccSubmit\"]";

    public String balance = "//table[@id=\"balenquiry\"]/tbody/tr[16]/td[2]";

}

package locator;

public class LocatorEditCustomer {
    public String navLinkEditCustomer = "//a[@href=\"EditCustomer.php\"]";
    public String inputCustomerId = "//input[@type=\"text\"][@name=\"cusid\"]";
    public String buttonSubmit = "//input[@type=\"submit\"][@name=\"AccSubmit\"]";

    public String inputAddress = "//textarea[@name=\"addr\"]";
    public String inputCity = "//input[@type=\"text\"][@name=\"city\"]";
    public String inputState = "//input[@type=\"text\"][@name=\"state\"]";
    public String inputPin = "//input[@type=\"text\"][@name=\"pinno\"]";
    public String inputMobileNumber = "//input[@type=\"text\"][@name=\"telephoneno\"]";
    public String inputEmail = "//input[@type=\"text\"][@name=\"emailid\"]";
    public String buttonSubmitEditCustomerForm = "//input[@type=\"submit\"]";

    public String successMessageEditCustomer = "//p[text()=\"Customer details updated Successfully!!!\"]";

}

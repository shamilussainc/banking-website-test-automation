package locator;

public class LocatorNewCustomer {
    public String navLinkNewCustomer = "//a[@href=\"addcustomerpage.php\"]";

    //  Form elements - Add new customer
    public String inputCustomerName = "//input[@type=\"text\"][@name=\"name\"]";
    public String inputRadioGenderMale = "//input[@type=\"radio\"][@value=\"m\"]";
    public String inputDateOfBirth = "//input[@type=\"date\"][@name=\"dob\"]";
    public String inputAddress = "//textarea[@name=\"addr\"]";
    public String inputCity = "//input[@type=\"text\"][@name=\"city\"]";
    public String inputState = "//input[@type=\"text\"][@name=\"state\"]";
    public String inputPin = "//input[@type=\"text\"][@name=\"pinno\"]";
    public String inputMobileNumber = "//input[@type=\"text\"][@name=\"telephoneno\"]";
    public String inputEmail = "//input[@type=\"text\"][@name=\"emailid\"]";
    public String inputPassword = "//input[@type=\"password\"][@name=\"password\"]";
    public String buttonSubmit = "//input[@type=\"submit\"]";

    public String messageSuccessCustomerReg = "//p[@class=\"heading3\"][@align=\"center\"]";
    public String customerId = "//table[@id=\"customer\"]/tbody/tr[4]/td[2]";
}

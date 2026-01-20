package Utils;

import Pages.*;
import org.openqa.selenium.WebElement;

import static Utils.CommonMethods.driver;

public class PageInitializer {

    public static LoginPage loginPage;
    public static AddEmployeePage addEmployeePage;
    public static DashboardPage dashboardPage;
    public static EmployeeSearchPage employeeSearchPage;
    public static DependantsPage dependantsPage;
    public static AdminPage adminPage;
    public static PimPage pimPage;
    public static UpdateContactPage updateContactPage;
    public static UploadEmployeeImagePage uploadEmployeeImagePage;
    public static EditEmployeePage editEmployeePage;

    public static void initializePageObjects(){
        loginPage=new LoginPage();
        addEmployeePage=new AddEmployeePage(driver);
        dashboardPage=new DashboardPage();
        employeeSearchPage=new EmployeeSearchPage();
        dependantsPage=new DependantsPage();
        adminPage=new AdminPage();
        pimPage=new PimPage();
        updateContactPage=new UpdateContactPage();
        uploadEmployeeImagePage=new UploadEmployeeImagePage();
        editEmployeePage=new EditEmployeePage();

    }
}

package Utils;

import Pages.*;

public class PageInitializer {

    public static LoginPage loginPage;
    public static AddEmployeePage addEmployeePage;
    public static DashboardPage dashboardPage;
    public static EmployeeSearchPage employeeSearchPage;
    public static ValidateCredentialsPage validateCredentialsPage;

    public static void initializePageObjects(){
        loginPage=new LoginPage();
        addEmployeePage=new AddEmployeePage();
        dashboardPage=new DashboardPage();
        employeeSearchPage=new EmployeeSearchPage();
        validateCredentialsPage=new ValidateCredentialsPage();

    }
}

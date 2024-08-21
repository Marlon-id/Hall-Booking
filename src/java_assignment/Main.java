package java_assignment;

import java.io.File;

public class Main {
    public static Login loginPage;
    public static Admin loginAdmin = null;
    public static PageAdmin pageadmin;

    public static void main(String[] args) {
        File adminFile = new File("admin.txt");
        
        if (!adminFile.exists()) {
            Admin superAdmin = new Admin("superadmin", "superadmin", 123);
            FileHandler.allAdmin.add(superAdmin);
            FileHandler.write();
        } else {
            FileHandler.read();
        }
        
        loginPage = new Login();
    }
}
package java_assignment;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FileHandler {
    public static ArrayList<Admin> allAdmin = new ArrayList<>();

    public static void write(){
        try{
            PrintWriter file = new PrintWriter("admin.txt");
            for(Admin x : allAdmin){
                file.println(x.getRole() + " " + x.getName() + " " + x.getPassword());
            }
            file.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error in write ..........");
        }
    }

    public static void read(){
        try{
            Scanner file = new Scanner(new File("admin.txt"));
            while(file.hasNextLine()){
                String[] data = file.nextLine().split(" ");
                String role = data[0];
                String name = data[1];
                int password = Integer.parseInt(data[2]);
                allAdmin.add(new Admin(role, name, password));
            }
            file.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error in read ..........");
        }
    }

    public static boolean isValidAdmin(String username, int password) {
        for (Admin admin : allAdmin) {
            if (admin.getName().equals(username) && admin.getPassword() == password) {
                return true; // Admin with matching username and password found
            }
        }
        return false; // No matching admin found
    }

    public static Admin getAdminByUsernameAndPassword(String username, int password) {
        for (Admin admin : allAdmin) {
            if (admin.getName().equals(username) && admin.getPassword() == password) {
                return admin; // Return the matching Admin object
            }
        }
        return null; // No matching admin found
    }
    
}

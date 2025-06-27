package views;

import Model.Data;
import dao.DataDAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    UserView(String email){
        this.email= email;
    }
    public void home(){
        do{
            System.out.println("Welcome");
            System.out.println("Press 1 to show Hidded files");
            System.out.println("Press 2 to hide a new file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("Press 0 to exit");
            Scanner sc= new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch){
                case 1-> {
                    try {
                        List<Data> file= DataDAO.getAllFiles(email);
                        System.out.println("ID - File Name");
                        for(Data files: file){
                            System.out.println(files.getId() + " " + files.getFileName());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("Enter the file path");
                    String path = sc.nextLine();
                    File f= new File(path);
                    Data file = new Data(this.email,path,f.getName(),0);
                    try {
                        DataDAO.hideFile(file);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    List<Data> file= null;
                    try {
                        file = DataDAO.getAllFiles(email);

                    System.out.println("ID - File Name");
                    for(Data files: file){
                        System.out.println(files.getId() + " " + files.getFileName());
                    }
                    System.out.println("Enter the file id to Unhide");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isValidId = false;
                    for(Data files: file){
                        if(files.getId()==id){
                            isValidId= true;
                            break;
                        }
                    }
                    if(isValidId){
                        DataDAO.unhideFiles(id);
                    }else {
                        System.out.println("Wrong ID input");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                case 0-> {
                    System.exit(0);
                }
            }
        }while(true);


    }
}

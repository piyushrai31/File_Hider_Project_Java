package views;

import Model.User;
import Service.GenerateOTP;
import Service.SendOTPService;
import Service.UserService;
import dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Welcome {
    public static void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Application!");
        System.out.println("Press 1 to Login:");
        System.out.println("Press 2 to SignUp");
        System.out.println("Press 0 to Exit");
        int choice=0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (choice){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
            default -> System.out.println("Invalid Input");
        }
    }

    private static void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name= sc.nextLine();
        System.out.println("Enter your Email");
        String email= sc.nextLine();
        String OTP = GenerateOTP.getOTP();
        User user = new User(email,name);
        SendOTPService.sendOTP(email,OTP);
        System.out.println("Enter the OTP");
        String otp = sc.nextLine();
        if(OTP.equals(otp)){
            int response = UserService.saveUser(user);
            switch (response){
                case 1 -> System.out.println("User registerd");
                case 2 -> System.out.println("User already exists");
            }
        }
        else{
            System.out.println("Wrong OTP");
        }


    }

    private static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your mail");
        String email = sc.nextLine();
        try {
            if(UserDAO.isExists(email)){
                String OTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,OTP);
                System.out.println("Enter OTP");
                String otp = sc.nextLine();
                if(OTP.equals(otp)){
                    new UserView(email).home();
                }
                else{
                    System.out.println("Wrong OTP");
                }
            }
            else{
                System.out.println("User does not Exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

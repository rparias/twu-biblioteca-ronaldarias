package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Login;
import com.twu.biblioteca.interfaces.Printer;
import com.twu.biblioteca.interfaces.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class LoginImp implements Login, Printer {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private List<User> users;

    public LoginImp(PrintStream printStream, List<User> users, BufferedReader bufferedReader) {
        this.printStream = printStream;
        this.users = users;
        this.bufferedReader = bufferedReader;
    }

    public User enterUserNameAndPassword() {
        print("Please enter library number: ");
        String libraryNumber = readLine();
        print("Please enter your password: ");
        String password = readLine();
        return loginUser(libraryNumber, password);
    }

    @Override
    public boolean validateLibraryNumberFormat(String libraryNumber) {
        return libraryNumber.matches("^\\d{3}[-]{0,1}\\d{4}|\\d{9,11}$");
    }

    @Override
    public User loginUser(String libraryNumber, String password) {
        User foundCustomer = users.stream()
                .filter(customer -> libraryNumber.equals(customer.getLibraryNumber()))
                .filter(customer -> password.equals(customer.getPassword()))
                .findAny()
                .orElse(null);

        print(foundCustomer == null ? "Wrong library number or password" : "Welcome " + foundCustomer.getName());

        return foundCustomer;
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getName().equals("Librarian");
    }

    @Override
    public void print(String message) {
        printStream.println(message + "\n");
    }

    private String readLine() {
        String text = null;
        try {
            text = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}

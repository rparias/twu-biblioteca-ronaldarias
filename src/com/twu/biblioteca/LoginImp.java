package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Login;
import com.twu.biblioteca.interfaces.Printer;
import com.twu.biblioteca.interfaces.User;

import java.io.PrintStream;
import java.util.List;

public class LoginImp implements Login, Printer {

    private PrintStream printStream;
    private List<User> users;

    public LoginImp(PrintStream printStream, List<User> users) {
        this.printStream = printStream;
        this.users = users;
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
    public void print(String message) {
        printStream.println(message);
    }
}

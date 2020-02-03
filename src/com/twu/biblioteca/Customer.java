package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.User;

public class Customer implements User {

    private String name;
    private String email;
    private String phoneNumber;
    private String libraryNumber;
    private String password;

    public Customer(String name, String email, String phoneNumber, String libraryNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryNumber = libraryNumber;
        password = "123456";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getLibraryNumber() {
        return libraryNumber;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        User customer = (Customer) obj;
        return name.equals(customer.getName()) &&
                email.equals(customer.getEmail()) &&
                phoneNumber.equals(customer.getPhoneNumber()) &&
                libraryNumber.equals(customer.getLibraryNumber());
    }
}

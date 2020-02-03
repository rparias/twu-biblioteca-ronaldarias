package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Login;

public class LoginImp implements Login {

    @Override
    public boolean validateLibraryNumberFormat(String libraryNumber) {
        return libraryNumber.matches("^\\d{3}[-]{0,1}\\d{4}|\\d{9,11}$");
    }
}

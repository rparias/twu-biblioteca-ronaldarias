package com.twu.biblioteca.interfaces;

public interface Login {
    boolean validateLibraryNumberFormat(String libraryNumber);
    User loginUser(String libraryNumber, String password);
    boolean isAdmin(User user);
}

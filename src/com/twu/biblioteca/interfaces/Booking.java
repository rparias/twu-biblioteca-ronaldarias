package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.Customer;

import java.util.List;

public interface Booking {
    Composition getComposition();
    User getUser();
    String printCompositionAndUsername();
}

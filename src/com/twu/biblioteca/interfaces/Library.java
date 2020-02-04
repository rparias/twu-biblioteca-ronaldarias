package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.Customer;

import java.util.List;

public interface Library {

    List<Composition> listAllCompositions();
    List<Composition> listAvailableCompositions();
    List<Composition> listCheckedOutCompositions();
    void checkoutComposition(Composition composition, User user);
    void returnComposition(Composition composition, User user);
    Composition findCompositionByName(String name);
    List<Booking> listBookingCompositions();
}

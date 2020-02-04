package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Booking;
import com.twu.biblioteca.interfaces.Composition;
import com.twu.biblioteca.interfaces.User;

public class BookingImp implements Booking {

    Composition composition;
    User user;

    public BookingImp(Composition composition, User user) {
        this.composition = composition;
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        BookingImp booking = (BookingImp) obj;
        return composition.equals(booking.composition) &&
                user.equals(booking.user);
    }

    @Override
    public Composition getComposition() {
        return composition;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String printCompositionAndUsername() {
        return String.format("%-35s%-10s", composition.getTitle(), user.getName());
    }
}

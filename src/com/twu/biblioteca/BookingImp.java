package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Booking;
import com.twu.biblioteca.interfaces.Composition;
import com.twu.biblioteca.interfaces.User;

import java.util.List;

public class BookingImp implements Booking {

    Composition composition;
    User user;

    public BookingImp(Composition composition, User user) {
        this.composition = composition;
        this.user = user;
    }

    @Override
    public List<String> getAllBookings() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        BookingImp booking = (BookingImp) obj;
        return composition.equals(booking.composition) &&
                user.equals(booking.user);
    }
}

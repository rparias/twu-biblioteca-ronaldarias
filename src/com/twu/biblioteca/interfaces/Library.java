package com.twu.biblioteca.interfaces;

import java.util.List;

public interface Library {

    List<Composition> listAllCompositions();
    List<Composition> listAvailableCompositions();
    List<Composition> listCheckedOutCompositions();
    void checkoutComposition(Composition composition);
    void returnComposition(Composition composition);
    Composition findCompositionByName(String name);

}

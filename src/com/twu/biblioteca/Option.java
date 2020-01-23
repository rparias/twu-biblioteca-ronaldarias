package com.twu.biblioteca;

public class Option {
    private String titleOption;
    private int numberOption;

    public Option(int numberOption, String titleOption) {
        this.titleOption = titleOption;
        this.numberOption = numberOption;
    }

    @Override
    public String toString() {
        return numberOption + " --> " + titleOption;
    }

    public String getTitleOption() {
        return titleOption;
    }

    public int getNumberOption() {
        return numberOption;
    }
}

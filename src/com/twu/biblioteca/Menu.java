package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

public class Menu {

    private Map<String, Option> options;
    private PrintStream printStream;
    BufferedReader bufferedReader;

    public Menu(Map<String, Option> options, PrintStream printStream, BufferedReader bufferedReader) {
        this.options = options;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }


    public void listOptions() {
        String optionList = "";
        for (Option option: options.values()) {
            optionList += option + "\n";
        }
        printStream.println(optionList);
    }

    public Option findOptionFromNumber() {
        Option option = options.get(readLine());
        return option;
    }

    private String readLine() {
        String option = null;
        try {
            option = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return option;
    }
}

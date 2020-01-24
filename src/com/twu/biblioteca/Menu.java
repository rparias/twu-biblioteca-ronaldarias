package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Map;

public class Menu {

    private Map<Integer, String> options;
    private PrintStream printStream;
    BufferedReader bufferedReader;

    public Menu(Map<Integer, String> options, PrintStream printStream, BufferedReader bufferedReader) {
        this.options = options;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }


    public void listOptions() {
        String optionList = "";
        for (Map.Entry<Integer, String> entry : options.entrySet()) {
            optionList += entry.getKey() + " --> " + entry.getValue() + "\n";
        }
        printStream.println(optionList);
    }

    public int findOptionFromNumber() {
        return readNumber();
    }

    private Integer readNumber() {
        int option = 0;
        try {
            option = Integer.parseInt(bufferedReader.readLine());
        } catch (Exception ignored) {

        }
        return option;
    }
}

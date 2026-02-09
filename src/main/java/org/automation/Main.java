package org.automation;

public class Main {
    public static void main(String[] args) {
        String s = "^%^%^%^SSSelenium&^&&^&";
        String abc = s.replaceAll("^[^A-Za-z]", "");
        System.out.println(abc);
    }
}
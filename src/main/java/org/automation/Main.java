package org.automation;

public class Main {
    public static void main(String[] args) {
        secondLargestNumber();
    }

    private static void secondLargestNumber() {
        int[] arr = { 10, 190, 98, 8, 56, 75, 120 };
        int max = 0;
        int secondmax = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                secondmax = max;
                max = arr[i];
            } else if (arr[i] > secondmax) {
                secondmax = arr[i];
            }
        }
        System.out.println(secondmax + " is second largest Number");
    }
}
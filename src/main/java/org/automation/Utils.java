// package org.automation;

// import java.util.HashSet;
// import java.util.LinkedHashMap;
// import java.util.Map;
// import java.util.Set;
// import java.util.TreeMap;

// public class Utils {
// public static void sort() {
// int[] arr = { 190, 89, 6, 90, 8, 4 };
// for (int i = 0; i < arr.length; i++) {
// for (int j = 0; j < arr.length - 1 - i; j++) {
// if (arr[j] > arr[j + 1]) {
// int temp = arr[j + 1];
// arr[j + 1] = arr[j];
// arr[j] = temp;
// }

// }
// }
// for (int i : arr) {
// System.out.print(i + " ");
// }
// System.out.println();
// }

// public static void findSecondLargest() {
// int[] arr = { 1, 89, 6, 90, 8, 4, 190 };
// int max = 0;
// int secondMax = 0;
// for (int j = 0; j < arr.length; j++) {
// if (arr[j] > max) {
// secondMax = max;
// max = arr[j];
// } else if (arr[j] > secondMax) {
// secondMax = arr[j];
// }
// }
// System.out.println("second Larges Number is " + secondMax);
// }

// public static void findLargestObject() {
// Object[] arr = { 1, 89, 6, 90, 8, 4, 190, ' ', "anand" };
// int max = 0;
// for (int i = 0; i < arr.length; i++) {
// if (arr[i] instanceof Integer) {
// if ((Integer) arr[i] > max) {
// max = (Integer) arr[i];
// }
// }
// }
// System.out.println("max object is " + max);
// }

// public static void firstNonRepeatingChar() {
// String a = "aabbccddeffg";
// char[] b = a.toCharArray();
// Map<Character, Integer> map = new LinkedHashMap<>();
// for (char c : b) {
// map.put(c, map.getOrDefault(c, 0) + 1);
// }
// for (char c : b) {
// if (map.get(c) == 1) {
// System.out.println(c + " is first non Repeating char");
// return;
// }
// }
// }

// public static void reverseSentence() {
// String sentence = "My name is Anand";
// char[] arr = sentence.toCharArray();
// int left = 0;
// int right = arr.length - 1;
// while (left < right) {
// if (arr[left] == ' ') {
// left++;
// } else if (arr[right] == ' ') {
// right--;
// } else {
// char temp = arr[left];
// arr[left] = arr[right];
// arr[right] = temp;
// left++;
// right--;
// }
// }
// System.out.println(new String(arr));
// }

// public static void reverseString() {
// String a = "Nitin";
// char[] b = a.toCharArray();
// int left = 0;
// int right = b.length - 1;
// while (left < right) {
// char temp = b[left];
// b[left] = b[right];
// b[right] = temp;
// left++;
// right--;
// }

// String c = new String(b);
// System.out.println("reversed String is " + c);

// if (a.toLowerCase().equals(c.toLowerCase())) {
// System.out.println(a + " is palindrome");
// } else {
// System.out.println(a + " is not palindrome");
// }
// }

// public static void countCharacterOcc() {
// String a = "automation";
// char[] b = a.toCharArray();
// Map<Character, Integer> map = new TreeMap<>();
// for (char c : b) {
// map.put(c, map.getOrDefault(c, 0) + 1);
// }

// map.forEach((Key, Value) -> System.out.println(Key + " occured " + Value + "
// times"));

// }

// public static void removeArrayDuplicates() {
// int[] arr = { 1, 2, 2, 3, 4, 4 };
// Set<Integer> set = new HashSet<>();
// for (int a : arr) {
// set.add(a);
// }
// System.out.println(set);

// }

// }

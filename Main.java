package numbers;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.*;

public class Main {
    public static boolean checkNatural(long num) {
        return num > 0;
    }

    public static boolean checkEven(long num) {
        return num % 2 == 0;
    }

    public static boolean checkBuzz(long num) {
        StringBuilder number = new StringBuilder();
        number.append(num);
        char lastNum = number.charAt(number.length() - 1);

        return lastNum == '7' || num % 7 == 0;
    }

    public static boolean checkDuck(long num) {
        StringBuilder number = new StringBuilder();
        number.append(num);
        int zeroCount = 0;
        for (int i = 1; i < number.length(); i++) {
            if (number.charAt(i) == '0') {
                zeroCount++;
            }
        }

        return zeroCount != 0;
}

    public static boolean checkPalindrome(long num) {
        StringBuilder numString = new StringBuilder();
        numString.append(num);
        int notPalindrome = 0;

        for (int i = 0; i < numString.length(); i++) {
            if (numString.charAt(i) != numString.charAt(numString.length() -1 - i)) {
                notPalindrome++;
            }
        }

        return notPalindrome == 0;
    }

    public static String checkParameters(long num) {
        boolean buzz = checkBuzz(num);
        boolean duck = checkDuck(num);
        boolean palindrome = checkPalindrome(num);
        boolean gapful = checkGapful(num);
        boolean spy = checkSpy(num);
        boolean square = checkSquare(num);
        boolean sunny = checkSunny(num);
        boolean jumping = checkJumping(num);
        boolean even = checkEven(num);
        boolean happy = checkHappy(num);

        StringBuilder numString = new StringBuilder();
        numString.append(num).append(" is ");
        if (even) {
            numString.append("even, ");
        } else {
            numString.append("odd, ");
        }
        if (buzz) {
            numString.append("buzz, ");
        }
        if (duck) {
            numString.append("duck, ");
        }
        if (palindrome) {
            numString.append("palindromic, ");
        }
        if (gapful) {
            numString.append("gapful, ");
        }
        if (spy) {
            numString.append("spy, ");
        }
        if (square) {
            numString.append("square, ");
        }
        if (sunny) {
            numString.append("sunny, ");
        }
        if (jumping) {
            numString.append("jumping, ");
        }
        if (happy) {
            numString.append("happy");
        } else {
            numString.append("sad");
        }

        return numString.toString();
    }

    public static boolean checkGapful(long num) {
        boolean end = true;
        StringBuilder numString = new StringBuilder();
        numString.append(num);

        if (String.valueOf(num).length() < 3) {
            end = false;
        } else {
            int a = Character.getNumericValue(numString.charAt(0));
            int b = Character.getNumericValue(numString.charAt(numString.length()-1));
            int divider = a * 10 + b;

            if (num % divider != 0) {
                end = false;
            }
        }

        return end;
    }

    public static boolean checkSquare(long num) {
        double square = Math.floor(Math.sqrt(num));
        return square * square == (double) num;
    }

    public static void printInstructions() {
        System.out.println("\nSupported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "\t* the first parameter represents a starting number;\n" +
                "\t* the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    public static boolean checkSunny(long num) {
        long newNum = num + 1;

        return checkSquare(newNum);
    }

    public static boolean checkHappy(long num) {
        long newNum = num;
        while (true) {
            int n = 0;
            int sum = 0;
            StringBuilder numString = new StringBuilder();
            numString.append(newNum);
            for (int i = 0; i < numString.length(); i++) {
                n = numString.charAt(i) - '0';
                sum += n * n;
            }
            newNum = sum;
            if (newNum == 1) {
                return true;
            } else if (newNum == num || newNum == 4) {
                return false;
            }
        }
    }

    public static boolean checkSpy(long num) {
        StringBuilder numString = new StringBuilder();
        numString.append(num);
        int length = numString.length();
        int sum = 0;
        int product = 1;
        for (int i = 0; i < length; i++) {
            int digit = Character.getNumericValue(numString.charAt(i));
            sum += digit;
            product *= digit;
        }

        return sum == product;
    }

    public static boolean checkJumping(long num) {
        StringBuilder numString = new StringBuilder();
        numString.append(num);
        boolean returning = true;
        if (numString.length() < 2) {
            return true;
        }

        for (int i = 0; i < numString.length(); i++) {
            if (i == 0) {
                int current = numString.charAt(i) - '0';
                int next = numString.charAt(i+1) - '0';
                if (Math.abs(current - next) != 1) {
                    returning = false;
                }
            } else if (i == numString.length()-1) {
                int current = numString.charAt(i) - '0';
                int previous = numString.charAt(i-1) - '0';
                if (Math.abs(current - previous) != 1) {
                    returning =  false;
                }
            } else {
                int current = numString.charAt(i) - '0';
                int previous = numString.charAt(i-1) - '0';
                int next = numString.charAt(i+1) - '0';
                if (Math.abs(current - previous) != 1 || Math.abs(current - next) != 1) {
                    returning =  false;
                }
            }
        }
        return returning;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        printInstructions();

        boolean end = true;
        while (end) {
            System.out.print("\nEnter a request: ");
            String input = null;

            if (!(input = sc.nextLine()).isEmpty()) {
                String[] given = input.split(" ");

                if (given[0].equalsIgnoreCase("0")) {
                    end = false;
                } else if (Long.parseLong(given[0]) < 0) {
                    System.out.println("\nThe first parameter should be a natural number or zero.");
                } else {
                    if (given.length > 1 && Long.parseLong(given[1]) < 1) {
                        System.out.println("\nThe second parameter should be a natural number.");
                    } else {
                        System.out.println();
                        printManyValues(given);
                    }
                }
            } else {
                printInstructions();
            }
        }
        System.out.println("\nGoodbye!");
    }

    public static boolean printManyValues(String[] given) {
        if (given.length == 1) {
            long num = Long.parseLong(given[0]);
            printOneValue(num);
            return true;
        }
        String[] access = new String[]{"even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "happy", "sad"};
        int lengthOfGiven = given.length;
        long num = Long.parseLong(given[0]);
        long range = Long.parseLong(given[1]);
        StringBuilder typesString = new StringBuilder();
        String[] types = new String[0];
        StringBuilder wrongs = new StringBuilder();
        StringBuilder minuses = new StringBuilder();
        for (int i = 2; i < lengthOfGiven; i++) {
            if (Arrays.asList(access).contains(given[i].toLowerCase())) {
                typesString.append(given[i].toLowerCase() + " ");
            } else {
                if (given[i].charAt(0) == '-') {
                    String word = given[i].replace("-", "").toLowerCase();
                    if (!Arrays.asList(access).contains(word)) {
                        wrongs.append(word + " ");
                    } else {
                        minuses.append(word + " ");
                    }
                } else {
                    wrongs.append(given[i] + " ");
                }
            }
        }
        if (typesString.length() > 0) {
            types = typesString.toString().split(" ");
        }
        String[] wrongsArr = new String[0];
        if (wrongs.length() > 0) {
            wrongsArr = wrongs.toString().split(" ");
        }
        String[] minusesArr = new String[0];
        if (minuses.length() > 0) {
            minusesArr = minuses.toString().split(" ");
        }

        if (types.length == 0 && minusesArr.length == 0) {
            if (wrongsArr.length > 0) {
                if (wrongsArr.length == 1) {
                    System.out.println("The property " + Arrays.toString(wrongs.toString().split(" ")).toUpperCase() + " is wrong." +
                            "\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                } else {
                    System.out.println("The properties " + Arrays.toString(wrongs.toString().split(" ")).toUpperCase() + " are wrong." +
                            "\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                }
            } else {
                while (range > 0) {
                    System.out.println(checkParameters(num));
                    range--;
                    num++;
                }
            }
        } else {
            if (wrongsArr.length > 0) {
                if (wrongsArr.length == 1) {
                    System.out.println("The property " + Arrays.toString(wrongs.toString().split(" ")).toUpperCase() + " is wrong." +
                            "\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                } else {
                    System.out.println("The properties " + Arrays.toString(wrongs.toString().split(" ")).toUpperCase() + " are wrong." +
                            "\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                }
            } else {
                if (propertyCheck(types, minusesArr)) {
                    //
                } else {
                    System.out.println();
                    int[] index = new int[types.length];
                    int[] falseIndex = new int[minusesArr.length];

                    for (int i = 0; i < index.length; i++) {
                        if (Arrays.asList(access).contains(types[i])) {
                            index[i] = Arrays.asList(access).indexOf(types[i]);
                        }
                    }
                    for (int i = 0; i < falseIndex.length; i++) {
                        if (Arrays.asList(access).contains(minusesArr[i])) {
                            falseIndex[i] = Arrays.asList(access).indexOf(minusesArr[i]);
                        }
                    }

                    while (range > 0) {
                        int trues = 0;
                        int falses = 0;
                        boolean[] answers = new boolean[]{checkEven(num), !checkEven(num), checkBuzz(num), checkDuck(num), checkPalindrome(num), checkGapful(num), checkSpy(num), checkSquare(num), checkSunny(num), checkJumping(num), checkHappy(num), !checkHappy(num)};
                        for (int i = 0; i < index.length; i++) {
                            if (answers[index[i]]) {
                                trues++;
                            }
                        }

                        for (int i = 0; i < falseIndex.length; i++) {
                            if (!answers[falseIndex[i]]) {
                                falses++;
                            }
                        }

                        if (trues == index.length && falses == falseIndex.length) {
                            System.out.println(checkParameters(num));
                            range--;
                        }

                        num++;
                    }
                }
            }
        }
        return true;
    }

    public static boolean printOneValue(long num) {
        boolean natural = checkNatural(num);
        boolean end = true;
        if (num == 0) {
            System.out.println("Goodbye!");
            end = false;
        } else if (natural) {
            boolean even = checkEven(num);
            boolean buzz = checkBuzz(num);
            boolean duck = checkDuck(num);
            boolean palindrome = checkPalindrome(num);
            boolean gapful = checkGapful(num);
            boolean spy = checkSpy(num);
            boolean square = checkSquare(num);
            boolean sunny = checkSunny(num);
            boolean jumping = checkJumping(num);
            boolean happy = checkHappy(num);
            boolean sad = !checkHappy(num);

            System.out.println("Properties of " + num);
            System.out.println("buzz: " + buzz);
            System.out.println("duck: " + duck);
            System.out.println("palindromic: " + palindrome);
            System.out.println("gapful: " + gapful);
            System.out.println("spy: " + spy);
            System.out.println("square: " + square);
            System.out.println("sunny: " + sunny);
            System.out.println("jumping: " + jumping);
            System.out.println("happy: " + happy);
            System.out.println("sad: " + sad);
            System.out.println("even: " + even);
            System.out.println("odd: " + !even);
        } else {
            System.out.println("The first parameter should be a natural number or zero.");
        }
        return end;
    }

    public static boolean propertyCheck(String[] types, String[] minusesArr) {
        if ((Arrays.asList(types).contains("odd") && Arrays.asList(types).contains("even"))) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("duck") && Arrays.asList(types).contains("spy"))) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("sunny") && Arrays.asList(types).contains("square"))) {
            System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("happy") && Arrays.asList(types).contains("sad"))) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(minusesArr).contains("odd") && Arrays.asList(minusesArr).contains("even"))) {
            System.out.println("The request contains mutually exclusive properties: [-ODD, -EVEN]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(minusesArr).contains("duck") && Arrays.asList(minusesArr).contains("spy"))) {
            System.out.println("The request contains mutually exclusive properties: [-DUCK, -SPY]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(minusesArr).contains("happy") && Arrays.asList(minusesArr).contains("sad"))) {
            System.out.println("The request contains mutually exclusive properties: [-HAPPY, -SAD]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("even") && Arrays.asList(minusesArr).contains("even"))) {
            System.out.println("The request contains mutually exclusive properties: [EVEN, -EVEN]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("odd") && Arrays.asList(minusesArr).contains("odd"))) {
            System.out.println("The request contains mutually exclusive properties: [ODD, -ODD]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("buzz") && Arrays.asList(minusesArr).contains("buzz"))) {
            System.out.println("The request contains mutually exclusive properties: [BUZZ, -BUZZ]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("duck") && Arrays.asList(minusesArr).contains("duck"))) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, -DUCK]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("palindromic") && Arrays.asList(minusesArr).contains("palindromic"))) {
            System.out.println("The request contains mutually exclusive properties: [PALINDROMIC, -PALINDROMIC]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("gapful") && Arrays.asList(minusesArr).contains("gapful"))) {
            System.out.println("The request contains mutually exclusive properties: [GAPFUL, -GAPFUL]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("spy") && Arrays.asList(minusesArr).contains("spy"))) {
            System.out.println("The request contains mutually exclusive properties: [SPY, -SPY]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("square") && Arrays.asList(minusesArr).contains("square"))) {
            System.out.println("The request contains mutually exclusive properties: [SQUARE, -SQUARE]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("sunny") && Arrays.asList(minusesArr).contains("sunny"))) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, -SUNNY]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("jumping") && Arrays.asList(minusesArr).contains("jumping"))) {
            System.out.println("The request contains mutually exclusive properties: [JUMPING, -JUMPING]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("happy") && Arrays.asList(minusesArr).contains("happy"))) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, -HAPPY]\n" +
                    "There are no numbers with these properties.");
        } else if ((Arrays.asList(types).contains("sad") && Arrays.asList(minusesArr).contains("sad"))) {
            System.out.println("The request contains mutually exclusive properties: [SAD, -SAD]\n" +
                    "There are no numbers with these properties.");
        } else {
            return false;
        }
        return true;
    }
}


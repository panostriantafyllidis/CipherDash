package com.Byron.cipherdash;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




public class CryptoPOJO {

    //Setting the parameter of KEY and Cipher_end as private constants, to improve encapsulation.

    private static final int KEY = 300;
    private static final int CIPHER_END = -1;

    // The method in which encryption takes place.
    public static int[] encrypt(String message) {
        if (message == null || message.isEmpty()) {
            return null;
        }

        int[] cipher = new int[message.length() + 2];
        int pivot = 0;

        int codePoint = message.codePointAt(pivot);
        if (codePoint == '#') {
            return null;
        }

        int charCipher;
        if (Character.UnicodeBlock.of(codePoint) == Character.UnicodeBlock.BASIC_LATIN) {
            // encode as ASCII
            charCipher = codePoint;
        } else {
            // encode as Unicode
            charCipher = KEY + codePoint; // add KEY to distinguish from ASCII
        }
        cipher[pivot++] = charCipher;
        int previous = charCipher;

        while (pivot < message.length()) {
            codePoint = message.codePointAt(pivot);
            if (Character.UnicodeBlock.of(codePoint) == Character.UnicodeBlock.BASIC_LATIN) {
                // encode as ASCII
                charCipher = (codePoint + previous) % KEY;
            } else {
                // encode as Unicode
                charCipher = KEY + codePoint + previous;
            }
            cipher[pivot++] = charCipher;
            previous = charCipher;
        }

        cipher[pivot++] = CIPHER_END;
        return Arrays.copyOfRange(cipher, 0, pivot);
    }


    // The method in which the decryption takes place.
    public static String decrypt(int[] cipher) {
        if (cipher == null) {
            return null;
        }

        int pivot = 0;
        int current = cipher[pivot];
        if (current == CIPHER_END) {
            return null;
        }

        StringBuilder decipherBuilder = new StringBuilder();
        if (current < KEY) {
            // ASCII character
            decipherBuilder.appendCodePoint(current);
        } else {
            // Unicode character
            int codePoint = current - KEY;
            decipherBuilder.appendCodePoint(codePoint);
        }
        int previousCode = current;

        while ((current = cipher[++pivot]) != CIPHER_END) {
            int codePoint;
            if (current < KEY) {
                // ASCII character
                codePoint = (KEY + current - previousCode) % KEY;
            } else {
                // Unicode character
                codePoint = current - KEY - previousCode;
            }
            char charDecipher = (char) codePoint;
            decipherBuilder.append(charDecipher);
            previousCode = current;
        }

        return decipherBuilder.toString();
    }


    // The method which we use to split the encoded number-message, into smaller 'tokens'
    // which we'll use to translate into characters, after-which we'll merge into words.
    public static int parseInput(String input){
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    // A method to encode a text file and export the encoded text to a new file
    public static void encodeFile(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

        String line = reader.readLine();
        StringBuilder builder = new StringBuilder();
        while (line != null) {
            builder.append(line); // append the line to the StringBuilder
            builder.append(System.lineSeparator()); // add the line separator
            line = reader.readLine();
        }
        builder.append("#"); // add "#" at the end of the entire input text
        int[] encodedText = encrypt(builder.toString());
        if (encodedText != null) {
            StringBuilder encodedBuilder = new StringBuilder();
            for (int code : encodedText) {
                encodedBuilder.append(code).append(" ");
            }
            writer.write(encodedBuilder.toString());
        }

        reader.close();
        writer.close();
    }

    // A method to decode a text file and export the decoded text to a new file
    public static void decodeFile(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

        String line = reader.readLine();
        while (line != null) {
            String[] codes = line.split(" ");
            int[] intCodes = new int[codes.length];
            for (int i = 0; i < codes.length; i++) {
                intCodes[i] = parseInput(codes[i]);
            }
            String decodedLine = decrypt(intCodes);
            if (decodedLine != null) {
                writer.write(decodedLine.replaceAll("<tab>", "\t"));
                writer.newLine();
            }
            line = reader.readLine();
        }

        reader.close();
        writer.close();
    }


}
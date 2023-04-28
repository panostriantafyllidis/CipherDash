package java.Byron.myprojects;

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

        if (message == null || message.isEmpty() ) return null;

        int[] cipher = new int[100];
        int pivot = 0;

        char ch = message.charAt(pivot);
        if (ch == '#') {
            return null;
        }

        int charCipher = ch;
        cipher[pivot++] = charCipher;
        int previous = charCipher;

        while ((ch = message.charAt(pivot)) != '#') {
            charCipher = (ch + previous) % KEY;
            cipher[pivot++] = charCipher;
            previous = charCipher;
        }

        cipher[pivot++] = CIPHER_END;
        return Arrays.copyOfRange(cipher, 0, pivot);
    }

    // The method in which the decryption takes place.
    public static String decrypt(int[] cipher) {

        if (cipher==null) return null;
        StringBuilder decipherBuilder = new StringBuilder();
        int pivot = 0;
        char charDecipher;

        int current = cipher[pivot];
        if (current == CIPHER_END) {
            return null;
        }
        decipherBuilder.append((char) current);
        int previousCode = current;

        while ((current = cipher[++pivot]) != CIPHER_END) {

            charDecipher = (char) (KEY + current - previousCode);
            while (charDecipher > KEY) charDecipher -= KEY;
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
        while (line != null) {
            int[] encodedLine = encrypt(line);
            if (encodedLine != null) {
                for (int code : encodedLine) {
                    writer.write(code + " ");
                }
                writer.newLine();
            }
            line = reader.readLine();
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
                writer.write(decodedLine);
                writer.newLine();
            }
            line = reader.readLine();
        }

        reader.close();
        writer.close();
    }
}
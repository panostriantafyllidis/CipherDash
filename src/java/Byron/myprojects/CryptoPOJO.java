package java.Byron.myprojects;

import java.util.Arrays;



public class CryptoPOJO {
    //=======================
    // My solution
    //=======================


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
}
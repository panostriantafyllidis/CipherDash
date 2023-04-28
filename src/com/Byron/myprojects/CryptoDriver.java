package com.Byron.myprojects;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CryptoDriver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Crypto program!");

        boolean exit = false;

        while (!exit) {
            System.out.println("Please select an option:");
            System.out.println("1. Encrypt a message");
            System.out.println("2. Decrypt a message");
            System.out.println("3. Encrypt a file");
            System.out.println("4. Decrypt a file");
            System.out.println("0. Exit");

            String input = sc.nextLine();
            boolean validInput = false;
            switch (input) {

                case "1":
                    //Encryption
                    String message = null;

                    while(!validInput) {
                        System.out.println("Enter a message to encrypt (end with #): ");
                        message = sc.nextLine();
                        if (message.isEmpty()) {
                            System.out.println("Invalid input: message is empty.");
                        } else if (message.charAt(message.length() - 1) != '#') {
                            System.out.println("Invalid input: message must end with #.");
                        } else {
                            validInput = true;
                        }
                    }
                    int[] cipher = CryptoPOJO.encrypt(message);
                    System.out.println("Encrypted message: ");
                    for (int i : cipher) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    break;
                case "2":
                    //Decryption
                    String encMessage;
                    while (!validInput) {
                        System.out.println("Enter the encrypted message to decrypt: ");
                        encMessage = sc.nextLine();
                        if (encMessage.isEmpty()) {
                            System.out.println("Invalid input: message is empty.");
                        } else {
                            try {
                                int[] encIntArr = Arrays.stream(encMessage.split(" "))
                                        .mapToInt(CryptoPOJO::parseInput)
                                        .toArray();
                                String deciphered = CryptoPOJO.decrypt(encIntArr);
                                if (deciphered == null){
                                    System.out.println("Invalid input: cannot decrypt message.");
                                } else {
                                    System.out.println("Decrypted message: ");
                                    System.out.println(deciphered);
                                    validInput = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input: message contains non-numeric characters.");
                            }
                        }
                    }
                    break;
                case "3":
                    // Encrypt file
                    System.out.println("Enter the path of the file to encrypt: ");
                    String fileToEncrypt = sc.nextLine();
                    System.out.println("Enter the path of the encrypted file: ");
                    String encryptedFile = sc.nextLine();
                    try {
                        CryptoPOJO.encodeFile(fileToEncrypt, encryptedFile);
                        System.out.println("File encrypted successfully.");
                    } catch (IOException e) {
                        System.out.println("Error encrypting file: " + e.getMessage());
                    }
                    break;

                case "4":
                    // Decrypt file
                    System.out.println("Enter the path of the encrypted file to decrypt: ");
                    String fileToDecrypt = sc.nextLine();
                    System.out.println("Enter the path of the decrypted file: ");
                    String decryptedFile = sc.nextLine();
                    try {
                        CryptoPOJO.decodeFile(fileToDecrypt, decryptedFile);
                        System.out.println("File decrypted successfully.");
                    } catch (IOException e) {
                        System.out.println("Error decrypting file: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from 0-4.");
                    break;
            }
        }
    }
}
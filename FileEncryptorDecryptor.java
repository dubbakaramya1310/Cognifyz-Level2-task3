import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptorDecryptor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose an action (encrypt/decrypt): ");
        String action = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine().trim();

        System.out.print("Enter the encryption/decryption key (an integer): ");
        int key = scanner.nextInt();

        switch (action) {
            case "encrypt":
                encryptFile(filePath, key);
                System.out.println("File encrypted successfully.");
                break;
            case "decrypt":
                decryptFile(filePath, key);
                System.out.println("File decrypted successfully.");
                break;
            default:
                System.out.println("Invalid action. Please choose encrypt or decrypt.");
        }

        scanner.close();
    }

    private static void encryptFile(String filePath, int key) {
        processFile(filePath, key, true);
    }

    private static void decryptFile(String filePath, int key) {
        processFile(filePath, key, false);
    }

    private static void processFile(String filePath, int key, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String outputFilePath;
            if (encrypt) {
                outputFilePath = filePath + ".encrypted";
            } else {
                outputFilePath = filePath + ".decrypted";
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                int currentChar;
                while ((currentChar = reader.read()) != -1) {
                    char encryptedChar = (char) (currentChar + key);
                    writer.write(encryptedChar);
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }
}
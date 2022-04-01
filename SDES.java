package sdes;

public class SDES {

    public static void main(String[] args) {

        // Initial 10 bit key
        String initialKey = "0010011011";

        // Initial 8 bit plain and ciphertext
        String initialPlainText = "01011100";
        String initialCipherText = "01011100";

        // Encrypt plaintext with the encrypt method in the Encryption class
        Encryption.encrypt(initialPlainText, initialKey);

        // Decrypt ciphertext with the decrypt method in the Decryption class
        Decryption.decrypt(initialCipherText, initialKey);

        // These are output in the console through the relevant classes
    }

}

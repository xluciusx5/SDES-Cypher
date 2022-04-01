package sdes;

public class Encryption {

    public static int[] encrypt(String plainText, String initialKey) {
        System.out.println("Encryption:");
        
        // Instantiate the Util class and generate the two subkeys
        int[] key1 = Utilities.generateKeys(initialKey, false);
        int[] key2 = Utilities.generateKeys(initialKey, true);

        // Initial permutation of the plaintext
        int[] IP = Utilities.initialPermutation(plainText);

        // Apply bg1
        int[] bg1 = Utilities.BG(key1, IP);

        // Switch Bg1
        int[] SW = Utilities.SW(bg1);

        // Apply Bg2
        int[] bg2 = Utilities.BG(key2, SW);

        // Apply the inverse initial permutation to output the final ciphertext
        int[] encryptedText = Utilities.inverseIP(bg2);

        // Output encrypted plaintext
        System.out.println("Encrypted plaintext:");
        for (int i = 0; i < encryptedText.length; i++) {
            System.out.println(encryptedText[i]);
        }
        System.out.println("------------------------------");
        
        // Return encryptedText
        return encryptedText;
    }

}

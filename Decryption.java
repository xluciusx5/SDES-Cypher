/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdes;

public class Decryption {

    //= IP B SW B IP
    public static int[] decrypt(String cipherText, String initialKey) {
        System.out.println("Decryption:");
        
        // Generate Subkeys
        int[] key1 = Utilities.generateKeys(initialKey, false);
        int[] key2 = Utilities.generateKeys(initialKey, true);

        // Initial permutation of the ciphertext
        int[] IP = Utilities.initialPermutation(cipherText);

        // Apply bg2
        int[] bg2 = Utilities.BG(key2, IP);

        // Switch bg2
        int[] SW = Utilities.SW(bg2);

        // Apply bg1
        int[] bg1 = Utilities.BG(key1, SW);

        // Apply inverse initial permutation to output the decrypted bits
        int[] decryptedText = Utilities.inverseIP(bg1); //ip-1

        // Output encrypted plaintext
        System.out.println("Decrypted ciphertext:");
        for (int i = 0; i < decryptedText.length; i++) {
            System.out.println(decryptedText[i]);
        }
        // Return decryptedText
        return decryptedText;
    }

}

package sdes;

public class Utilities {

    // -----------------------
    // Method for subkey generation
    public static int[] generateKeys(String key, boolean key2) {
        // Parameter key takes in the initial 10 bit key as a string
        // If key2 == true, key 2 will be returned - Key 1 is returned by default

        // Creating array of key string length
        int[] k = new int[key.length()];

        // Push 10 bit key string to array k[]
        for (int i = 0; i < key.length(); i++) {
            k[i] = Integer.parseInt(key.split("")[i]);
        }

        // int k[] = {0, 0, 1, 0, 0, 1, 1, 0, 1, 1}; // For testing
        // First permutation
        int s[] = {k[2], k[4], k[1], k[6], k[3], k[9], k[0], k[8], k[7], k[5]};
        // Second permutation (LS1)
        int t[] = {s[1], s[2], s[3], s[4], s[0], s[6], s[7], s[8], s[9], s[5]};
        // Split into 8 part key (sub-key 1)
        int u[] = {t[5], t[2], t[6], t[3], t[7], t[4], t[9], t[8]};
        // Second shift for second key (LS2)
        int v[] = {t[2], t[3], t[4], t[0], t[1], t[7], t[8], t[9], t[5], t[6]};
        // Final permutation (sub-key 2)
        int w[] = {v[5], v[2], v[6], v[3], v[7], v[4], v[9], v[8]};

        // Output initial 10 bit key
        for (int i = 0; i <= 9; i++) {
            System.out.print(k[i]);
        }

        System.out.println(" - 10bit Initial Key");

        // RETURN KEY 2
        // If key 2 is true, return key 2
        if (key2 == true) {

            // Output key 2 to console
            for (int i = 0; i <= 7; i++) {
                System.out.print(w[i]);
            }

            System.out.println(" - Key 2");

            // Return key 2
            return w;
        }

        // RETURN KEY 1
        for (int i = 0; i <= 7; i++) {

            // Output key 1 to console
            System.out.print(u[i]);
        }

        System.out.println(" - Key 1");

        // Return key 1 by default
        return u;
    }

    // -----------------------
    // Initial Permutation method
    public static int[] initialPermutation(String plainText) {
        // Takes in the 8 bit plaintext string

        // Create an array of int[] of plaintext length
        int[] plainTextArray = new int[plainText.length()];

        // Push 8 bit key string to array plainText[]
        for (int i = 0; i < plainText.length(); i++) {
            plainTextArray[i] = Integer.parseInt(plainText.split("")[i]);
        }

        // Create initial permutation array of the same length as plaintext
        int initialPermutation[] = new int[plainText.length()];

        // Perform initial permutation
        initialPermutation[0] = plainTextArray[1];
        initialPermutation[1] = plainTextArray[5];
        initialPermutation[2] = plainTextArray[2];
        initialPermutation[3] = plainTextArray[0];
        initialPermutation[4] = plainTextArray[3];
        initialPermutation[5] = plainTextArray[7];
        initialPermutation[6] = plainTextArray[4];
        initialPermutation[7] = plainTextArray[6];

        // Output to console
        for (int i = 0; i < initialPermutation.length; i++) {
            System.out.print(initialPermutation[i]);
        }

        System.out.println(" - Initial Permutation");

        // Return initial permutation int[]
        return initialPermutation;
    }

    // -----------------------
    // BG generation method
    public static int[] BG(int[] key, int[] IP_Switch) {
        // Depending on the situation, this generates BG1 or 2
        // First parameters takes in the needed key
        // Second parameter takes in either the Initial Permutation or the Switch

        // Create P coordinates of length 8
        int[] PCoordinatesArray = new int[8];

        // Array for p coordinates
        PCoordinatesArray[0] = IP_Switch[7] ^ key[0]; //p00
        PCoordinatesArray[1] = IP_Switch[4] ^ key[1]; //p01
        PCoordinatesArray[2] = IP_Switch[5] ^ key[2]; //p02
        PCoordinatesArray[3] = IP_Switch[6] ^ key[3]; //p03
        PCoordinatesArray[4] = IP_Switch[5] ^ key[4]; //p10
        PCoordinatesArray[5] = IP_Switch[6] ^ key[5]; //p11
        PCoordinatesArray[6] = IP_Switch[7] ^ key[6]; //p12
        PCoordinatesArray[7] = IP_Switch[4] ^ key[7]; //p13

        // Coordinates output
        // for(int i = 0; i < key.length; i++) {
        //    System.out.println(PCoordinatesArray[i]);
        // }
        // SBox 0 is an 2 dimensional array
        int[][] SBoxZero = {
            {01, 00, 11, 10},
            {11, 10, 01, 00},
            {00, 10, 01, 11},
            {11, 01, 11, 10}
        };

        // SBox 1 is also a 2 dimensional array
        int[][] SBoxOne = {
            {00, 01, 10, 11},
            {10, 00, 01, 11},
            {11, 00, 01, 00},
            {10, 01, 00, 11}
        };

        // Finding out row & column results
        // SBox 0 coordinates
        int sZeroRow = PCoordinatesArray[0] + PCoordinatesArray[3];
        int sZeroColumn = PCoordinatesArray[1] + PCoordinatesArray[2];

        // Getting q1 and q2 together and individually
        int q1AndQ2 = SBoxZero[sZeroRow][sZeroColumn];
        int q1 = (int) q1AndQ2 / 10;
        int q2 = (int) q1AndQ2 % 10;

        // Testing outputs
        // System.out.println(q1);
        // System.out.println(q2);
        // System.out.println(q1AndQ2);
        // SBox 1 coordinates
        int sOneRow = PCoordinatesArray[4] + PCoordinatesArray[7];
        int sOneColumn = PCoordinatesArray[5] + PCoordinatesArray[6];

        // Getting q3 and q4 together and individually
        int q3AndQ4 = SBoxOne[sOneRow][sOneColumn];
        int q3 = (int) q3AndQ4 / 10;
        int q4 = (int) q3AndQ4 % 10;

        // Testing
        // System.out.println(q3);
        // System.out.println(q4);
        // System.out.println(q3AndQ4);
        //Permutation 4 array - Testing
        //int[] p4QArray = new int[4];
        // p4QArray[0] = justQ2;
        // p4QArray[1] = justQ4;
        // p4QArray[2] = justQ3;
        // p4QArray[3] = justQ1;
        // Inititalising BG array
        int[] BGArray = new int[8];

        // Testing output
        // for (int i = 0; i < p4QArray.length; i++) {
        //   System.out.println(p4QArray[i]);
        // }
        // Expand and permutate
        BGArray[4] = q2;
        BGArray[5] = q4;
        BGArray[6] = q3;
        BGArray[7] = q1;

        // XOR addition
        BGArray[0] = IP_Switch[0] ^ q2;
        BGArray[1] = IP_Switch[1] ^ q4;
        BGArray[2] = IP_Switch[2] ^ q3;
        BGArray[3] = IP_Switch[3] ^ q1;

         //Testing output
//         System.out.println("Here is BG:");
//         for (int i = 0; i < BGArray.length; i++) {
//             System.out.println(BGArray[i]);
//         }
         //Return BG array
        return BGArray;
    }

    // -----------------------
    // Switch method
    public static int[] SW(int[] bg1) {
        // Takes in bg1

        // Setting the array length
        int arrayLength = bg1.length;

        // Initialize switched array
        int switchedArray[] = new int[arrayLength];

        // Swap first 4 and last four digits
        switchedArray[0] = bg1[4];
        switchedArray[1] = bg1[5];
        switchedArray[2] = bg1[6];
        switchedArray[3] = bg1[7];

        switchedArray[4] = bg1[0];
        switchedArray[5] = bg1[1];
        switchedArray[6] = bg1[2];
        switchedArray[7] = bg1[3];

        // Output result
//         System.out.println("Here is SW:");
//         for (int i = 0; i < switchedArray.length; i++) {
//             System.out.println(switchedArray[i]);
//         }
        // Return result
        return switchedArray;
    }

    // -----------------------
    // Inverse Initial Permutation method
    public static int[] inverseIP(int[] bg2) {
        // ADD BG2 AS PARAMETER
        int arrayLength = bg2.length;

        // Initialise array length
        int[] finalResult = new int[arrayLength];

        // Change the position of bg2 to display inverse IP
        finalResult[0] = bg2[3];
        finalResult[1] = bg2[0];
        finalResult[2] = bg2[2];
        finalResult[3] = bg2[4];
        finalResult[4] = bg2[6];
        finalResult[5] = bg2[1];
        finalResult[6] = bg2[7];
        finalResult[7] = bg2[5];

        // Output result
//         System.out.println("Here is the inverse initial permutation:");
//        for (int i = 0; i < finalResult.length; i++) {
//            System.out.println(finalResult[i]);
//         }

        // Return inverse initial permutation
        return finalResult;
    }

}

public class BruteForce {


    static void bruteForceDecoder(String cipherText) {
        for (int key = 0; key < 1071; key++) {
            String decryptedText = CaesarCipher.decrypt(cipherText, key);
            if (isMessageValid(decryptedText))
                System.out.println("Decrypted Text Using key"+key+":"+decryptedText);
        }

    }
    public static boolean isLatinLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static boolean isMessageValid(String decryptedText) { //does not work with spaces or symbols or numbers or russian
        for (char c : decryptedText.toCharArray())
            if (!isLatinLetter(c))
                return false;
        return true;
    }
}
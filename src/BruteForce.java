import java.util.ArrayList;
import java.util.List;

public class BruteForce {


    public static List<String> bruteForceDecoder(String encryptedText) {


        String decryptedText;
        List<String> result = new ArrayList<>();
        for (int key = 0; key < 156; key++) {
            decryptedText = CaesarCipher.decrypt(encryptedText, key);
            char mostUsed = mostCommonCharacter(decryptedText);
//            if (isMessageValid(decryptedText) && (mostUsed == ' ')) {
            if (mostUsed == ' ') {
//            if (isMessageValid(decryptedText)) {
//                decryptedText = "Расшифрованный текст используя КЛЮЧ: " + key + "\n" + decryptedText + "\n"; //in case we show options to user for him to pick a key
                result.add(decryptedText);
            }
        }
//        System.out.println(result);
        return result;

    }
//isValidSymbol and isMessageValid are redundant and can cause problems because we now have a limited alphabet to work with
//    public static boolean isValidSymbol(char c) {
//
//        return (c >= ' ' && c <= '“');
//
//    }
//
//    private static boolean isMessageValid(String decryptedText) {
//        for (char currentChar : decryptedText.toCharArray())
//            if (!isValidSymbol(currentChar)) {
//                return false;
//            }
//        return true;
//    }

    public static char mostCommonCharacter(String UsersText) {
        char mostCommonChar;
        String mostUsedLetter = "";
        int count = 0;

        String[] array = UsersText.split("");

        for (int i = 0; i < array.length; i++) {
            int tempCount = 0;

            for (int j = 0; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    tempCount++;
                }
                if (tempCount > count) {
                    count = tempCount;
                    mostUsedLetter = array[i];
                }
            }
        }
        mostCommonChar = mostUsedLetter.charAt(0);

//        System.out.println("Most used char: " + "'" + mostCommonChar + "'");
//        System.out.println("Char count: " + count);

        return mostCommonChar;
    }

}
import java.util.ArrayList;
import java.util.List;

public class BruteForce {


    public static List<String> bruteForceDecoder(String encryptedText) {


        String decryptedText;
        List<String> result = new ArrayList<>();
        for (int key = 0; key < 156; key++) {
            decryptedText = CaesarCipher.decrypt(encryptedText, key);
            char mostUsed = mostCommonCharacter(decryptedText);
            if (mostUsed == ' ') {
                result.add(decryptedText);
            }
        }
        return result;

    }

    public static char mostCommonCharacter(String usersText) {
        char mostCommonChar;
        String mostUsedLetter = "";
        int count = 0;

        String[] array = usersText.split("");

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
        return mostCommonChar;
    }
}
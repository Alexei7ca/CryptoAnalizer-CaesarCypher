import java.util.ArrayList;
import java.util.List;

public class BruteForce {


    public static List<String> bruteForceDecoder(String encryptedText) {


        String decryptedText;
        List<String> result = new ArrayList<>();
        for (int key = 0; key < 157; key++) {
            decryptedText = CaesarCipher.decrypt(encryptedText, key);
            if (isMessageValid(decryptedText)) {
                decryptedText = "Расшифрованный текст используя КЛЮЧ: " + key + "\n" + decryptedText + "\n";
                result.add(decryptedText);
            }
        }
        System.out.println(result);
        return result;

    }

    public static boolean isValidSymbol(char c) {

//        return (c >= '\n' && c <= 'ї');  //literally every symbol
        return (c >= ' ' && c <= '“');  //testing new

    }

    private static boolean isMessageValid(String decryptedText) {
        for (char currentChar : decryptedText.toCharArray())
            if (!isValidSymbol(currentChar)) {
                return false;
            }
        return true;
    }

}
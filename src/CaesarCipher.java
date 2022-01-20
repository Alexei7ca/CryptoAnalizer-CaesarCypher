public class CaesarCipher {

    public static String encrypt(String message, int key) {
        String encryptedMessage = "";
        char currentChar;
        for (int i = 0; i < message.length(); ++i) {
            currentChar = message.charAt(i);
            if (currentChar >= ' ' && currentChar <= 'я') { //"space" is 32 in ASCII(first character latin symbols) and "я" 044f (last character of basic cyrillic symbols) (in between are all punctuation symbols as well as latin and russian alphabetises)
                currentChar = (char) (currentChar + key);

                if (currentChar > 'я') {
                    currentChar = (char) (currentChar - 'я' + 'a' - 1);
                }

                encryptedMessage += currentChar;
            }
        }
        return encryptedMessage;
    }

    public static String decrypt(String message, int key) {
        String decryptedMessage = "";
        char currentCharacter;
        for(int i = 0; i < message.length(); ++i){
            currentCharacter = message.charAt(i);
            if(currentCharacter >= ' ' && currentCharacter <= 'я'){
                currentCharacter = (char)(currentCharacter - key);

                if(currentCharacter < ' '){
                    currentCharacter = (char)(currentCharacter + 'я' - 'a' + 1);
                }

                decryptedMessage += currentCharacter;
            }
        }
        return decryptedMessage;
    }

}

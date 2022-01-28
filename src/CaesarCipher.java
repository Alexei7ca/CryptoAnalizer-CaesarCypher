// changed ' ' for '\n'  and  'я' for 'ї'

public class CaesarCipher {

    static String neededSymbols = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZzАаБбВвГгҐґДдЕеЄєЖжЗзИиІіЇїЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЬьЮюЯяЪъэЭыЫёЁ.?“‘,-–—)!:;)()[]/1234567890«» ";


    public static String encrypt(String message, int key) { // max key is neededCharacters.length == 156

        char[] usersCharacters = message.toCharArray();
        char[] neededCharacters = neededSymbols.toCharArray();
        char[] result = new char[usersCharacters.length];

        for (int i = 0; i < usersCharacters.length; i++) {
            char usersCharacter = usersCharacters[i];
            for (int j = 0; j < neededCharacters.length; j++) {
                char ch = neededCharacters[j];
                if (usersCharacter == ch) {
                    result[i] = neededCharacters[(j + key) % neededCharacters.length];
                }
            }
        }
        return new String(result);
    }

    public static String decrypt(String message, int key) {

        char[] usersCharacters = message.toCharArray();

        char[] neededCharacters = neededSymbols.toCharArray();
        char[] result = new char[usersCharacters.length];

        int decryptKey = neededCharacters.length - key;
        for (int i = 0; i < usersCharacters.length; i++) {
            char usersCharacter = usersCharacters[i];
            for (int j = 0; j < neededCharacters.length; j++) {
                char ch = neededCharacters[j];
                if (usersCharacter == ch) {
                    result[i] = neededCharacters[(j + decryptKey) % neededCharacters.length];
                }
            }
        }
        return new String(result);
    }

}

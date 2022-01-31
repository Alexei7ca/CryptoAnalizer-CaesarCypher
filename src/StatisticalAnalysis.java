public class StatisticalAnalysis {

/*
     this one is for finding the char' ' (space) in the encrypted text then extract char ' ' - the one you found == key
     or just get the most common char in both, provided by user and encrypted texts, the difference is the key.
*/

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
        return mostCommonChar;
    }

    public static int keyFinder(char mostCommonChar) {

        char charNeeded = mostCommonChar;
        char[] symbols = CaesarCipher.NEEDED_SYMBOLS.toCharArray();

        int found = -1;
        for (int i = 0; i < symbols.length; ++i) {
            if (symbols[i] == charNeeded) {
                found = i;
                break;
            }
        }
        return found;
    }
}

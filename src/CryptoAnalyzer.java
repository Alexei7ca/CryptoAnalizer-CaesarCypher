import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CryptoAnalyzer {
    public static void main(String[] args) {
        welcomeUser();

    }

    public static void welcomeUser() {

        System.out.println("Добро Пожаловать!");
        System.out.println("Чем мы можем сегодня помочь?");
        System.out.println("Для шифрования вашего файла, напишите \"1\"");
        System.out.println("Для расшифрования вашего файла, напишите \"2\"");
        System.out.println("Для криптоАнализа файла методом брутфорс, напишите \"3\"");
        System.out.println("Для криптоАнализа файла методом статистического анализа, напишите \"4\"");
        System.out.println("Чтобы выйти из программы напишите \"0\"");
        userSelection();
        System.out.println("До свидания!");
    }

    public static void userSelection() {
        while (true) {
            int selection = 0;
            try {
                Scanner console = new Scanner(System.in);
                selection = console.nextInt();

                if (selection == 1) {
                    caesarEncrypts();
                } else if (selection == 2) {
                    caesarDecrypts();
                } else if (selection == 3) {
                    caesarBruteForce();
                } else if (selection == 4) {
                    statisticalAnalysis();
                } else if (selection == 0) {
                    return;
                } else {
                    System.out.println("Неправильный ввод, попробуйте еще раз");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неправильный ввод, попробуйте еще раз");
            }
        }
    }


    // below are the calls for the encryption methods

    public static void caesarEncrypts() {
        String textFromUserFile = askUserForFilePath();
        int key = askForKey();

        String encryptedMessage = CaesarCipher.encrypt(textFromUserFile, key);
        String pathResultingFile = String.valueOf(createEncryptedFile(encryptedMessage));
        System.out.println("Ключ для расшифрованния данного файла: " + key + "\n Пожалуйста, не теряйте ключ.\n");
        System.out.println("Путь к зашифрованому файлу: \n" + pathResultingFile);
        System.exit(0);
    }

    public static void caesarDecrypts() {
        String textFromUserFile = askUserForFilePath();
        int key = askForKey();

        String decryptedMessage = CaesarCipher.decrypt(textFromUserFile, key);
        String pathResultingFile = String.valueOf(createDecryptedFile(decryptedMessage));
        System.out.println("Путь к расшифрованому файлу: \n" + pathResultingFile);
        System.exit(0);
    }

    public static void caesarBruteForce() {
        String textFromUserFile = askUserForFilePath();
        System.out.println("Идет процесс расшифровки, это может занять пару минут.");
        System.out.println();

        String result = String.join(", ", BruteForce.bruteForceDecoder(textFromUserFile));
        String pathResultingFile = String.valueOf(createDecryptedFile(result));
        System.out.println("Путь к расшифрованому файлу: \n" + pathResultingFile);
        System.exit(0);
    }

    public static void statisticalAnalysis() {
        System.out.println("""
                Для этого метода вам необходимо предоствить файл - эталон.
                Желательно такого же автора и стиля как ваш зашифрованный файл.
                Этот файл будет использован для сравнения со зашифрованным файлом и анализа.
                Предоставьте файл - эталон.""");
        String textExample = askUserForFilePath();
        System.out.println("Предоставьте файл для расшифровки");
        String encryptedText = askUserForFilePath();
        System.out.println("Идет процесс расшифровки, это может занять пару минут.");

        char mostCommonExample = StatisticalAnalysis.mostCommonCharacter(textExample);
        char mostCommonEncrypted = StatisticalAnalysis.mostCommonCharacter(encryptedText);
        int firstPiece = StatisticalAnalysis.keyFinder(mostCommonEncrypted);
        int secondPiece = StatisticalAnalysis.keyFinder(mostCommonExample);
        int key = firstPiece - secondPiece;

        String decryptedMessage = CaesarCipher.decrypt(encryptedText, key);
        String pathResultingFile = String.valueOf(createDecryptedFile(decryptedMessage));
        System.out.println("Путь к расшифрованому файлу: \n" + pathResultingFile);
        System.exit(0);
    }


// bellow are the methods who ask the user for paths and keys

    private static String askUserForFilePath() {
        Scanner scanner = new Scanner(System.in);
        String textFromUserFile = "";
        while (true) {
            System.out.println("Пожалуйста, введите путь к файлу: ");
            Path filePath = Path.of(scanner.nextLine());
            if (Files.isRegularFile(filePath)) {
                try {
                    textFromUserFile = Files.readString(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("Путь не ведет к файлу.");
            }
        }
        return textFromUserFile;
    }


    public static int askForKey() {  //if there's time add a check that max key is neededCharacters.length == 157 (157 and 0 give the same result)
        int key = 0;
        System.out.println("Пожалуйста, обратите внимание на то, ключ может быть любое целое число от 1 до 156.");
        System.out.println("Введите ваш ключ (любое целое число от 1 до 156):");
        try {
            Scanner console = new Scanner(System.in);
            key = console.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Неправильный ввод,");
            key = askForKey();
        }
        return key;
    }

/*
 below 2 methods(difference is only on the name of the file created) that creates a new file,
 writes on it the string it got from the other methods and return the path of the new file.
 System.getProperty("user.home") + "/Desktop/"; -> get the path of the user's computer
*/

    public static Path createDecryptedFile(String resultingText) {
        String usersPath = System.getProperty("user.home") + "/Desktop/";
        Path outputFile;
        File file = new File(usersPath + "Decrypted" + ".txt");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File(usersPath + "Decrypted" + increase + ".txt");
        }
        if (!file.exists()) {
            try {

                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(resultingText);
                bw.flush();
                bw.close();

                System.out.println("Готово");

            } catch (IOException ignored) {
            }
        }
        outputFile = Path.of(String.valueOf(file));
        return outputFile;
    }

    public static Path createEncryptedFile(String resultingText) {
        String usersPath = System.getProperty("user.home") + "/Desktop/";
        Path outputFile;
        File file = new File(usersPath + "Encrypted" + ".txt");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File(usersPath + "Encrypted" + increase + ".txt");
        }
        if (!file.exists()) {
            try {

                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(resultingText);
                bw.flush();
                bw.close();

                System.out.println("Готово");

            } catch (IOException ignored) {
            }
        }
        outputFile = Path.of(String.valueOf(file));
        return outputFile;
    }

}


/*
           /Users/alexei/desktop/.txt    /Users/alexei/desktop/Encrypted.txt
there's a bug that drops chars in the final file,
 if the text has more than 1300 chars the end file gets written without 2,6,etc chars (the longer the text the worse it gets)
 noticed that the missing chars are whatever char that replaces the space from the original text
*/

/*
if english text
with key 139 it's absolute chaos   --- russ txt also
with key 129 it mistakes "e" for "f" en the resulting text ---- russ text is fine
with key 119 it mistakes "k" for "h" en the resulting text
with key 109 it mistakes "o" for "p" en the resulting text
with key 99 it's absolute chaos --- russ txt is fine
with key 89 it mistakes "w" for "z" en the resulting text
something is really wrong with the "9"
 */
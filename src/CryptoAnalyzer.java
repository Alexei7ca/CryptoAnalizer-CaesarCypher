import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//           /Users/alexei/desktop/test.txt   /Users/alexei/desktop/testRuss.txt    /Users/alexei/desktop/Encrypted.txt


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
                    selection = 1;
                    caesarEncrypts();
                } else if (selection == 2) {
                    selection = 2;
                    caesarDecrypts();
                } else if (selection == 3) {
                    selection = 3;
                    caesarBruteForce();
                } else if (selection == 4) {
                    selection = 4;
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

    public static void caesarBruteForce() {    //this should return a string, so it works like the others
        String textFromUserFile = askUserForFilePath();
        int key;
        BruteForce.bruteForceDecoder(textFromUserFile);
        key = askForBruteForceKey();

        String decryptedMessage = CaesarCipher.decrypt(textFromUserFile, key);
        String pathResultingFile = String.valueOf(createDecryptedFile(decryptedMessage));
        System.out.println("Путь к расшифрованому файлу: \n" + pathResultingFile);
        System.exit(0);
    }

    public static void statisticalAnalysis() {
        System.out.println("Для этого метода вам необходимо предоствить файл - эталон.\n" +
                "Желательно такого же автора и стиля как ваш зашифрованный файл.\n" +
                "Этот файл будет использован для сравнения со зашифрованным файлом и анализа.\n" +
                "Предоставьте файл - эталон.");
        String textExample = askUserForFilePath();
        System.out.println("Предоставьте файл для расшифровки");
        String encryptedText = askUserForFilePath();

        char mostCommonExample = StatisticalAnalysis.mostCommonCharacter(textExample);
        char mostCommonEncrypted = StatisticalAnalysis.mostCommonCharacter(encryptedText);
        int key = mostCommonEncrypted - mostCommonExample;
        System.out.println("Сравнение этих двух файлов показал что, ключ для расшифровки = " + key);

        String decryptedMessage = CaesarCipher.decrypt(encryptedText, key);
        String pathResultingFile = String.valueOf(createDecryptedFile(decryptedMessage));
        System.out.println("Путь к расшифрованому файлу: \n" + pathResultingFile);

        System.exit(0);
    }


// bellow are the methods who ask the user for paths and keys

    private static String askUserForFilePath() {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String textFromUserFile = "";
        while (true) {
            System.out.println("Пожалуйста, введите путь к файлу: ");
            Path filePath = Path.of(scanner.nextLine());
            if (Files.isRegularFile(filePath)) {
                List<String> list = null;
                try {
                    list = Files.readAllLines(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (String str : list)
                    builder.append(str);
                textFromUserFile = builder.toString();
                System.out.println("Оригинальный текст из вашего файла: \n" + textFromUserFile);
                System.out.println();
                break;
            } else {
                System.out.println("Путь не ведет к файлу.");
            }
        }
        return textFromUserFile;
    }


    public static int askForKey() {  //if there's time add a check that the key should be >0 and <950
        int key = 0;
        System.out.println("Пожалуйста, обратите внимание на то, что ввести числа больше 950 может составит программу работать некорректно."); //added
        System.out.println("Введите целое число (ваш ключ):");
        try {
            Scanner console = new Scanner(System.in);
            key = console.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Неправильный ввод,");
            key = askForKey();
        }
        return key;
    }

    public static int askForBruteForceKey() {
        int key = 0;
        System.out.println("Сверху варианты расшифровки вашего файла,");
        System.out.print("пожалуйста, выбирите подходящий вам вариант и скопируйте его ключ здесь ->");
        System.out.println();
        try {
            Scanner console = new Scanner(System.in);
            key = console.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Неправильный ввод, вам нужна только цифра которая указана как ключ");
            key = askForBruteForceKey();
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
                bw.close();

                System.out.println("Готово");

            } catch (IOException e) {
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
                bw.close();

                System.out.println("Готово");

            } catch (IOException e) {
            }
        }
        outputFile = Path.of(String.valueOf(file));
        return outputFile;
    }
}

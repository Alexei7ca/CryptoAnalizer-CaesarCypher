import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CryptoAnalyzer {
    public static void main(String[] args) {
        welcomeUser();

    }

    public static void welcomeUser(){

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

    public static void userSelection(){
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

    public static void caesarEncrypts() {    //this should work with files
        String textFromUserFile = askUserForFilePath();
        int key = askForKey();

        String encryptedMessage = CaesarCipher.encrypt(textFromUserFile, key);
        System.out.println("Введите путь куда пишем засшифрованный текст: "); //need a method for this(currently working on it on "test")
        System.out.println("Засшифрованный текст: \n" + encryptedMessage);
        System.exit(0);
    }

    public static void caesarDecrypts(){
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a message: "); // should be Path
        String message = console.nextLine();
        int key = askForKey();

        String decryptedMessage = CaesarCipher.decrypt(message, key);
        System.out.println("Расшифрованный текст = \n" + decryptedMessage);
        System.exit(0);
    }

    public static void caesarBruteForce() {    //this should work with files
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the encrypted message: "); // should be Path
        String encryptedMessage = console.nextLine();

        BruteForce.bruteForceDecoder(encryptedMessage);
        System.out.println("");
        System.exit(0);
    }


// bellow are the methods who ask the user for paths and keys

    private static String askUserForFilePath()  {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String textFromUserFile = "";
        System.out.println("Пожалуйста, введите путь к файлу: ");
        while (true) {
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
                break;
            }else {
                System.out.println("Путь не ведет к файлу.");
                System.out.println("Пожалуйста, введите путь к файлу для зашифровки: ");
            }
        }
        return textFromUserFile;
    }


    public static int askForKey(){
            int key = 0;
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

    public static Path resultingFile(){  // this is being worked on it "test"
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Path filePath = Path.of(scanner.nextLine());
            if (Files.isRegularFile(filePath)) {
                List<String> list = null;
                try {
                    list = Files.readAllLines(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("Путь не ведет к файлу.");
                System.out.println("Пожалуйста, введите путь к файлу для зашифровки: ");
            }
        }
    }
}

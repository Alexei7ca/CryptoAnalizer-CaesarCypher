import java.util.InputMismatchException;
import java.util.Scanner;

public class CryptoAnalizer{
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


    public static void caesarEncrypts() {    //this should work with files
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a message: "); // should be Path
        String message = console.nextLine();
        int key = askForKey();

        String encryptedMessage = CaesarCipher.encrypt(message, key);
        System.out.println("Encrypted Message = " + encryptedMessage);
        System.out.println("Чтобы выйти из программы напишите \"0\"");
    }

    public static void caesarDecrypts(){
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a message: "); // should be Path
        String message = console.nextLine();
        int key = askForKey();

        String decryptedMessage = CaesarCipher.decrypt(message, key);
        System.out.println("Decrypted Message = " + decryptedMessage);
        System.out.println("Чтобы выйти из программы напишите \"0\"");
    }

    public static void caesarBruteForce() {    //this should work with files
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the encrypted message: "); // should be Path
        String encryptedMessage = console.nextLine();

        if (encryptedMessage.charAt(0) >= 'A' && encryptedMessage.charAt(0) <= 'z'){
        BruteForce.bruteForceLatin(encryptedMessage);
        }else if (encryptedMessage.charAt(0) >= 'А' && encryptedMessage.charAt(0) <= 'я'){
            BruteForce.bruteForceRussian(encryptedMessage);
        }
        System.out.println("");
        System.out.println("Чтобы выйти из программы напишите \"0\"");
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
}

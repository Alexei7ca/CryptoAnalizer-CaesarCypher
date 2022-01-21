public class BruteForce {


    static class allLettersAndSymbols {

        StringBuilder builder = new StringBuilder();
        String allChar = "";{
            for (int i = 32; i < 1103; i++) {
                char a = (char) i;
                builder.append(a);
            }
            allChar = builder.toString();
        }


        int indexOfChar(char c)
        {
            for(int i=0;i< allChar.length();i++)
            {
                if(allChar.charAt(i)==c)
                    return i;
            }
            return -1;
        }

        char charAtIndex(int pos)
        {
            return allChar.charAt(pos);
        }
    }
        static allLettersAndSymbols b= new allLettersAndSymbols();

        static void bruteForceDecoder(String cipherText)
        {
            cipherText=cipherText.toUpperCase();

            for(int k=0;k< 1071;k++)
            {
                String decryptedText="";
                int key=k;
                for(int i=0;i< cipherText.length();i++)
                {
                    int index= b.indexOfChar(cipherText.charAt(i));

                    if(index==-1)
                    {
                        decryptedText+=cipherText.charAt(i);
                        continue;
                    }
                    if((index-key)>=0)
                    {
                        decryptedText+= b.charAtIndex(index-key);
                    }
                    else
                    {
                        decryptedText+= b.charAtIndex((index-key)+1071);
                    }
                }

                System.out.println("Decrypted Text Using key"+key+":"+decryptedText);
            }
        }

    // this is the one for russian symbols////////// not needed anymore

//    static class russianLetters {
//        String allChar="АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
//        int indexOfChar(char c)
//        {
//            for(int i=0;i< allChar.length();i++)
//            {
//                if(allChar.charAt(i)==c)
//                    return i;
//            }
//            return -1;
//        }
//
//        char charAtIndex(int pos)
//        {
//            return allChar.charAt(pos);
//        }
//    }
//    static russianLetters a = new russianLetters();
//
//    static void bruteForceRussian(String cipherText)
//    {
//        cipherText=cipherText.toUpperCase();
//
//        for(int k=0;k< 32;k++)
//        {
//            String decryptedText="";
//            int key=k;
//            for(int i=0;i< cipherText.length();i++)
//            {
//                int index= a.indexOfChar(cipherText.charAt(i));
//
//                if(index==-1)
//                {
//                    decryptedText+=cipherText.charAt(i);
//                    continue;
//                }
//                if((index-key)>=0)
//                {
//                    decryptedText+= a.charAtIndex(index-key);
//                }
//                else
//                {
//                    decryptedText+= a.charAtIndex((index-key)+32);
//                }
//            }
//
//            System.out.println("Decrypted Text Using key"+key+":"+decryptedText);
//        }
//    }
    }

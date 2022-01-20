public class BruteForce {


    // this is the one for latin symbols//////////
    static class latinLetters {
        String allChar="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
        static latinLetters b= new latinLetters();

        static void bruteForceLatin(String cipherText)
        {
            cipherText=cipherText.toUpperCase();

            for(int k=0;k< 26;k++)
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
                        decryptedText+= b.charAtIndex((index-key)+26);
                    }
                }

                System.out.println("Decrypted Text Using key"+key+":"+decryptedText);
            }
        }

    // this is the one for russian symbols//////////

    static class russianLetters {
        String allChar="АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
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
    static russianLetters a = new russianLetters();

    static void bruteForceRussian(String cipherText)
    {
        cipherText=cipherText.toUpperCase();

        for(int k=0;k< 32;k++)
        {
            String decryptedText="";
            int key=k;
            for(int i=0;i< cipherText.length();i++)
            {
                int index= a.indexOfChar(cipherText.charAt(i));

                if(index==-1)
                {
                    decryptedText+=cipherText.charAt(i);
                    continue;
                }
                if((index-key)>=0)
                {
                    decryptedText+= a.charAtIndex(index-key);
                }
                else
                {
                    decryptedText+= a.charAtIndex((index-key)+32);
                }
            }

            System.out.println("Decrypted Text Using key"+key+":"+decryptedText);
        }
    }
    }

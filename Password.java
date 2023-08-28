import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Password {

    public char[] ApplyAppleStyle(char[] apple_password, int indexOfNumber){
        Random random = new Random();

        int[] possibleIndexes = {0, 5, 7, 11, 13, 19};
        int indexToSwap = possibleIndexes[random.nextInt(possibleIndexes.length)];

        if (indexOfNumber == - 1) {
            int indexOfUpperCase = 0;
            for (int i = 0; i < apple_password.length; i++) {
                if (Character.isUpperCase(apple_password[i])) {
                    indexOfUpperCase = i;
                    break;
                }
            }
            while (indexOfUpperCase == indexToSwap) {
                indexToSwap = possibleIndexes[random.nextInt(possibleIndexes.length)];
            }
            apple_password[indexToSwap] = (char) random.nextInt(9);
        }
        else{
            char tmp = apple_password[indexOfNumber];
            apple_password[indexOfNumber] = apple_password[indexToSwap];
            apple_password[indexToSwap] = tmp;
        }

    return apple_password;

    }


    public char[] BuildApplePassword(char[] lowercase_letters, char[] uppercase_letters, char[] numbers) {
        char[] apple_password = new char[20];
        Random random = new Random();

        boolean is_number_entered = false;
        boolean is_upper_entered = false;
        int indexOfNumber = -1;
        for (int i = 0; i < 20; i++) {
            int char_type = random.nextInt(3);

            if (i == 6 || i == 12) {
                apple_password[i] = '-';
            } else {
                switch (char_type) {
                    case 0:
                        apple_password[i] = lowercase_letters[random.nextInt(lowercase_letters.length)];
                        break;
                    case 1:
                        if (!is_upper_entered) {
                            apple_password[i] = uppercase_letters[random.nextInt(uppercase_letters.length)];
                            is_upper_entered = true;
                        } else {
                            i--;
                        }
                        break;
                    case 2:
                        if (!is_number_entered) {
                            apple_password[i] = numbers[random.nextInt(numbers.length)];
                            indexOfNumber = i;
                            is_number_entered = true;
                        } else {
                            i--;


                        }
                        break;
                }
            }
        }
        return CheckForSimilarCharacters(ApplyAppleStyle(apple_password, indexOfNumber), uppercase_letters);
    }

    public char[] CheckForSimilarCharacters(char[] apple_password, char[] uppercase_letters){

        HashMap<Character, Character> similarCharacters = new HashMap<>();
        Random random = new Random();

        similarCharacters.put('I', 'l');
        similarCharacters.put('O', '0');
        similarCharacters.put('B', '8');
        similarCharacters.put('Z', '2');

        int indexOfSimilarChar;
        for (Map.Entry<Character, Character> entry : similarCharacters.entrySet()){

                if (Arrays.toString(apple_password).contains(entry.getKey().toString()) && Arrays.toString(apple_password).contains(entry.getValue().toString())){
                    indexOfSimilarChar = String.valueOf(apple_password).indexOf(entry.getKey());
                    apple_password[indexOfSimilarChar] = uppercase_letters[random.nextInt(uppercase_letters.length)];
                }
            }
//        System.out.println(apple_password);
        return apple_password;
    }


    public char[] BuildSecurePassword(char[] lowercase_letters, char[] uppercase_letters, char[] numbers, char[] special_characters, int passwordLength){
        Random random = new Random();
        char [] securePassword = new char [passwordLength];
        for (int i = 0; i < passwordLength; i++){
            int char_type = random.nextInt(4);

            switch (char_type) {
                case 0 -> securePassword[i] = lowercase_letters[random.nextInt(lowercase_letters.length)];
                case 1 -> securePassword[i] = uppercase_letters[random.nextInt(uppercase_letters.length)];
                case 2 -> securePassword[i] = numbers[random.nextInt(numbers.length)];
                case 3 -> securePassword[i] = special_characters[random.nextInt(special_characters.length)];
            }
        }


        return securePassword;
    }


    public static void main(String[] args) {

        char[] lowercase_letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] uppercase_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] numbers = "0123456789".toCharArray();
        char[] special_characters = "!@#$%^&*()_+{}[]|\\;:'\",.<>?/-~`".toCharArray();
        Password mypassword = new Password();
        for (int i = 0; i < 999999; i ++){
            char[] applePassword = mypassword.BuildApplePassword(lowercase_letters, uppercase_letters, numbers);
            char[] securePassword = mypassword.BuildSecurePassword(uppercase_letters,lowercase_letters,numbers,special_characters,16);
            System.out.println(String.valueOf(applePassword));
            System.out.println(String.valueOf(securePassword));
        }

    }
}

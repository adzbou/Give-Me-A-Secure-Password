import java.util.Random;

public class Password {


    public void BuildApplePassword(char[] lowercase_letters, char[] uppercase_letters, char[] numbers){
        char[] apple_password = new char[20];
        Random random = new Random();

        boolean is_number_entered = false;
        boolean is_uppper_entered = false;
        for (int i = 0; i < 20; i ++){
            int char_type = random.nextInt(3);

            if (i == 6 || i == 12){
                apple_password[i] = '-';
            }

            else if (!is_number_entered){
                switch (char_type) {
                    case 0 -> {
                        apple_password[i] = lowercase_letters[random.nextInt(lowercase_letters.length)];
                    }

                    case 1 -> {
                        apple_password[i] = uppercase_letters[random.nextInt(uppercase_letters.length)];
                        is_uppper_entered = true;
                    }

                    case 2 -> {
                        apple_password[i] = numbers[random.nextInt(numbers.length)];
                        is_number_entered = true;
                    }

                }

            }
            else {
                apple_password[i] = lowercase_letters[random.nextInt(lowercase_letters.length)];
            }

        }System.out.println(apple_password);
    }


    


    public static void main(String[] args) {

        char[] lowercase_letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] uppercase_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] numbers = "0123456789".toCharArray();
        char[] special_characters = "!@#$%^&*()_+{}[]|\\;:'\",.<>?/-~`".toCharArray();
        Password mypassword = new Password();
        for (int i = 0; i < 1000; i ++){
            mypassword.BuildApplePassword(lowercase_letters, uppercase_letters, numbers);


        }

    }
}

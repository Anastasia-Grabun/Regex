package org.example;

import java.util.regex.Pattern;

public class Tasks {
/** Задание 1
    Написать метод, который проверяет, является ли строка валидным номером телефона в формате +7(XXX)XXX-XX-XX.
**/
    public boolean isValidPhoneNumber(String phone) {
        if (phone == null /*|| phone.isEmpty()*/) {
            return false;
        }

        return phone.matches("^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$");
    }

/**Задание 2
    Создать метод для валидации email адреса.
    Email должен содержать латинские буквы, цифры,
    точки и дефисы в локальной части, символ @, и домен из букв и точек.**/
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches("^[a-zA-Z0-9.-]+@[a-zA-Z.]+$");
    }

   /** Задание 3
    Написать метод, который извлекает все числа из строки и возвращает их сумму.**/
   public int extractAndSumNumbers(String text) {
       if (text == null || text.isEmpty()) {
           return 0;
       }

       int sum = Pattern.compile("\\d+")
               .matcher(text)
               .results()
               .mapToInt(m -> Integer.parseInt(m.group()))
               .sum();

       return sum;
   }

    /**Задание 4
    Создать метод, который заменяет все последовательности пробелов (один или более)
     на один пробел и удаляет пробелы в начале и конце строки.**/
    public String normalizeSpaces(String text) {
        if (text == null) {
            return null;
        }

        return text.trim().replaceAll(" +", " ");
    }

    /**Задание 5
    Написать метод, который проверяет силу пароля. Пароль считается сильным, если:

    Длина от 8 до 20 символов
    Содержит хотя бы одну заглавную букву
    Содержит хотя бы одну строчную букву
    Содержит хотя бы одну цифру
    Содержит хотя бы один специальный символ (!@#$%^&*)**/
    public boolean isStrongPassword(String password) {
        if(password == null){
            return false;
        }

        return password.matches(
                " ^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,20}$"
        );
    }


}

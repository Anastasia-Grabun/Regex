package org.example;

import java.util.regex.Pattern;

public class Tasks {
/** Задание 1
    Написать метод, который проверяет, является ли строка валидным номером телефона в формате +7(XXX)XXX-XX-XX.

    Примеры:

            +7(123)456-78-90 → true
            +7123456-78-90 → false
            +8(123)456-78-90 → false**/
    public boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        return phone.matches("^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$");
    }

/**
    Задание 2
    Создать метод для валидации email адреса.
    Email должен содержать латинские буквы, цифры,
    точки и дефисы в локальной части, символ @, и домен из букв и точек.


    Примеры:

    user@example.com → true
    user.name@domain.co.uk → true
    user@ → false
    @domain.com → false
**/
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches("^[a-zA-Z0-9.-]+@[a-zA-Z.]+$");
    }

   /** Задание 3
    Написать метод, который извлекает все числа из строки и возвращает их сумму.

    Примеры:

            "У меня есть 5 яблок и 10 груш" → 15
            "Нет чисел в тексте" → 0
            "123abc456def789" → 1368
**/
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
}

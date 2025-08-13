package org.example;

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

}

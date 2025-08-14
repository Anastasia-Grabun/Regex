package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
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

   /* Задание 7
    Написать метод, который форматирует номер банковской карты, разбивая его на группы по 4 цифры, разделенные пробелами. Входная строка может содержать пробелы и дефисы.

    Примеры:

            "1234567890123456" → "1234 5678 9012 3456"
            "1234-5678-9012-3456" → "1234 5678 9012 3456"
            "1234 56" → null*/
    public String formatCardNumber(String cardNumber) {
        if(cardNumber == null || cardNumber.isEmpty()){
            return null;
        }

        String number = cardNumber.replaceAll("[^0-9]", "");
        if(number.length() != 16){
            return null;
        }

        return number.replaceAll("(.{4})(?=.)", "$1 ").trim();
    }

  /*  Задание 8
    Создать метод, который парсит лог-файл и извлекает IP адреса,
    временные метки и HTTP статус коды.
    Формат лога: IP - - [timestamp] "METHOD /path HTTP/1.1" status size

    Пример лога:

            192.168.1.1 - - [10/Oct/2023:13:55:36 +0000] "GET /index.html HTTP/1.1" 200 1234
            10.0.0.1 - - [10/Oct/2023:13:55:37 +0000] "POST /api/data HTTP/1.1" 404 567
*/
  public List<LogEntry> parseLogFile(String logContent) {
      List<LogEntry> entries = new ArrayList<>();

      String logPattern = "(\\S+) - - \\[(.*?)\\] \".*?\" (\\d{3}) \\d+";
      Pattern pattern = Pattern.compile(logPattern);
      Matcher matcher = pattern.matcher(logContent);

      while (matcher.find()) {
          LogEntry entry = new LogEntry();
          entry.ip = matcher.group(1);
          entry.timestamp = matcher.group(2);
          entry.statusCode = Integer.parseInt(matcher.group(3));
          entries.add(entry);
      }

      return entries;
  }

   /** Задание 9
    Написать метод, который проверяет правильность
    написания HTML тегов в строке. Нужно проверить:

    Все открывающие теги имеют соответствующие закрывающие
    Теги правильно вложены
    Самозакрывающиеся теги корректны

    Примеры:

            "<div><p>Text</p></div>" → true
            "<div><p>Text</div></p>" → false
            "<img src='test.jpg' />" → true
            "<div><span>Text</div>" → false**/
   public boolean isValidHtml(String html) {
       Stack<String> stack = new Stack<>();

       String tagPattern = "<(/?)(\\w+)([^>]*)>";
       Matcher matcher = Pattern.compile(tagPattern).matcher(html);

       while (matcher.find()) {
           String slash = matcher.group(1);
           String tagName = matcher.group(2);
           String rest = matcher.group(3);

           boolean selfClosing = rest.endsWith("/");

           if (slash.equals("/")) {
               if (stack.isEmpty() || !stack.pop().equals(tagName)) {
                   return false;
               }
           } else if (!selfClosing) {
               stack.push(tagName);
           }
       }

       return stack.isEmpty();
   }

/*    Задание 10
    Создать метод, который извлекает и валидирует
    JSON объекты из текста, используя только регулярные выражения.
    Нужно найти все JSON объекты и
     проверить базовую корректность структуры (парные скобки, кавычки для ключей).



    Пример:

    Данные: {"name": "John", "age": 30} и {"invalid": json} и {"valid": "data", "number": 42}

    Должен вернуть: ["{"name": "John", "age": 30}", "{"valid": "data", "number": 42}"]*/
    public List<String> extractValidJsonObjects(String text) {
        List<String> result = new ArrayList<>();

        String jsonPattern = "\\{\\s*(\"[^\"]+\"\\s*:\\s*(\"[^\"]*\"|\\d+)(\\s*,\\s*\"[^\"]+\"\\s*:\\s*(\"[^\"]*\"|\\d+))*)?\\s*\\}";
        Pattern pattern = Pattern.compile(jsonPattern);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String json = matcher.group();
            result.add(json);
        }

        return result;
    }

   /* Задание 6
    Создать метод, который извлекает все URL из текста.
    URL должен начинаться с http:// или https://.
    Примеры:

            "Посетите https://example.com и http://test.org" → ["https://example.com", "http://test.org"]
            "Ссылка: https://domain.co.uk/path?param=value" → ["https://domain.co.uk/path?param=value"]
*/
   public List<String> extractUrls(String text) {
       List<String> urls = new ArrayList<>();

       String urlPattern =  "https?://.*?(?=\\s|$|https?://)";
       Pattern pattern = Pattern.compile(urlPattern);
       Matcher matcher = pattern.matcher(text);

       while(matcher.find()){
           urls.add(matcher.group());
       }

       return urls;
   }

}

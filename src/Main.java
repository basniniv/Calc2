import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(calc(System.in.toString()));

    }

    public static String calc(String input) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите арефметическое выражение из двух чисел от 1 до 10 типа: a + b, a - b, a * b, " +
                "a / b , арабскими или римскими цифрами");
        String in = sc.nextLine();

        if (!in.contains("+") && !in.contains("-")
                && !in.contains("*") && !in.contains("/")) {
            throw new IOException("не является математической операцией");
            //Если нет нужного оператора выбросить исключение
        }

        //разделяем строку на 2 операнда и 1 оператор
        //Удоляем пробелы из строки, добовляем по одному пробелу с кажд. стороны оператора.
        // Для корр. чтения "a+b" и "a + b"
        in = in.replaceAll("\\s+", "");
        in = in.replaceAll("\\*", " * ");
        in = in.replaceAll("\\/", " / ");
        in = in.replaceAll("\\+", " + ");
        in = in.replaceAll("\\-", " - ");

        if (in.length() - in.replaceAll(" ", "").length() > 2) {
            //если в строке больше двух пробелов после замены регульрного выражения на такое же с пробелом
            throw new IOException("Выражение должно состоять только из двух операндов и одного опертаора");
        }
//        System.out.println(in);


        String[] parts = in.split(" ");
        String part1 = parts[0];// первый операнд
        String sign2 = parts[1];// оператор
        String part3 = parts[2];// второй операнд

        if (part1.contains(".") || part3.contains(".")
                || part1.contains(",") || part3.contains(",")) {
            throw new IOException("Калькулятор работает только с целыми числами"); //Если ввели не целое число
        }

        if ((RomanNumeral.contains((part1.toUpperCase()))
                && !RomanNumeral.contains(part3.toUpperCase()))) {
            //Если только первый операнд римская цифра
            throw new IOException("Калькулятор может работать только с римскими или только с арабскими цифрами");
        }

        if ((!RomanNumeral.contains((part1.toUpperCase()))
                && RomanNumeral.contains(part3.toUpperCase()))) {
            //Если только второй операнд римская цифра
            throw new IOException("Калькулятор может работать только с римскими  или только арабскими цифрами");
        }

        if (part1.length() >= 4
                && !RomanNumeral.contains((part1.toUpperCase()))) {
            //Если операнд больше 3 символов и его нет в енам
            throw new IOException("Число должно быть не больше 10");
        }
        if (part3.length() >= 4 && !RomanNumeral.contains((part1.toUpperCase()))) {
            //Если операнд больше 3 символов и его нет в енам
            throw new IOException("Число должно быть не больше 10");
        }

        if ((RomanNumeral.contains((part1.toUpperCase()))
                && RomanNumeral.contains(part3.toUpperCase()))) { //Если оба операнда содержит только римские цифры то:

            RomanNumeral rn1 = RomanNumeral.valueOf(part1.toUpperCase());
            RomanNumeral rn3 = RomanNumeral.valueOf(part3.toUpperCase());

            int a = rn1.getArabic();
            int b = rn3.getArabic();

            if (a > 10 || b > 10 || a < 1 || b < 1) {
                throw new IOException("Число должно быть не менее 1 и не больше 10");
            }

            int res = switch (sign2) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;
                default -> throw new IOException();
            };

            return RomanNumeral.contains(res);

        }


        int a = Integer.parseInt(part1); //перевод строки a в инт
        int b = Integer.parseInt(part3); //перевод b в инт

        //Если один из операндов болеше 10 или меньеше 1
        if (a > 10 || b > 10 || a < 1 || b < 1) {
            throw new IOException("Числа должны быть от 1 до 10");
        }

        int res = switch (sign2) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IOException();
        };
        return Integer.toString(res);

    }


}
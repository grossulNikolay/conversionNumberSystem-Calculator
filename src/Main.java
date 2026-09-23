import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { //цикл для всей программы, чтобы после подсчета можно было перевести другие числа
            String numberA = ""; //строчная запись для всех переменных
            String systemB = "";
            String systemC = "";
            String result = "";

            while (true) {
                System.out.println("\nВведите Ваше число: ");
                String inputA = scanner.nextLine();
                numberA = inputA;

                System.out.println("\nУкажите систему счисления этого числа: ");
                String inputB = scanner.nextLine();
                systemB = inputB;

                try { //первая проверка, значения основания systemB
                    if (!systemB.equalsIgnoreCase("Факт") && !systemB.equalsIgnoreCase("Фак") &&
                            !systemB.equalsIgnoreCase("Фиб") && !systemB.toUpperCase().endsWith("C") &&
                            !systemB.toUpperCase().endsWith("С")) {
                        int firstCheck = Integer.parseInt(systemB);
                        if (Math.abs(firstCheck) < 2 || Math.abs(firstCheck) > 36) {
                            System.out.println("\nИзвините, работаю только с основаниями, " +
                                    "находящимися в диапазоне от 2 до 36 по модулю");
                            continue;
                        }
                    }
                } catch (Exception error) {
                }

                boolean isValid = ValidityCheck.validityCheck(numberA, systemB); //метод полной проверки валидности
                // введённых numberA и systemB
                if (isValid) {
                    System.out.println("\nВаше число корректно и соответствует указанной системе счисления. Идём дальше!");
                    break;
                } else {
                    System.out.println("\nОШИБКА! Число " + numberA + " не подходит для системы счисления по основанию "
                            + systemB + ". Попробуйте ввести корректные числа A и B");
                }
            }

            while (true) { //проверка, значения основания systemC
                System.out.println("\nУкажите систему счисления числа, которое хотите получить: ");
                String inputC = scanner.nextLine();
                systemC = inputC;

                try {
                    if (!systemC.equalsIgnoreCase("Факт") && !systemC.equalsIgnoreCase("Фак") &&
                            !systemC.equalsIgnoreCase("Фиб") && !systemC.toUpperCase().endsWith("C") &&
                            !systemC.toUpperCase().endsWith("С")) {
                        int firstCheck = Integer.parseInt(systemC);
                        if (Math.abs(firstCheck) < 2 || Math.abs(firstCheck) > 36) { //дефолт СС от 2 до 36
                            // +нега-позиционные от 2 до 36
                            System.out.println("\nИзвините, работаю только с основаниями, " +
                                    "находящимися в диапазоне от 2 до 36 по модулю");
                            continue;
                        }
                    }
                } catch (Exception error) {
                }

                boolean isValid = IsValidC.isValidC(systemC); //метод полной проверки валидности systemC
                if (isValid) {
                    System.out.println("\nУказанная система счисления корректрна! СЧИТАЮ!");
                    break;
                } else {
                    System.out.println("\nОШИБКА! Я не знаю такой системы счисления. :( Попробуйте ввести ещё раз!");
                }
            }

            long decimalNumber = ConverterToDecimal.convertAtoDecimal(numberA, systemB); //универсальная конвертация
            // numberA по основанию systemB в 10-ричную СС

            result = ConverterFromDecimal.convertFromDecimal(decimalNumber, systemC); //конвертация 10-ричной записи
            // с прошлого шага в искомый ответ

            System.out.println("\nВаше число " + numberA + " в системе счисления " + systemB + " будет равно " + result +
                    " в системе счисления " + systemC);

            while (true) { //для перевода других чисел
                System.out.println("\nХотите перевести другие числа? (y/n):");
                String reload = scanner.nextLine();

                if (reload.equalsIgnoreCase("n") || reload.equalsIgnoreCase("н")) {
                    System.out.println("\nЛадно... До свидания!");
                    return; //окончание программы
                } else if (reload.equalsIgnoreCase("y") || reload.equalsIgnoreCase("д")) {
                    System.out.println("\nХорошо. Переведём ещё несколько чисел!");
                    break; //перезапуск программы
                } else {
                System.out.println("\nПожалуйста, введите 'y' ('д') или 'n' ('н'). Я не понимаю другие символы :(");
                } //этот цикл начинается с начала, пока в консоль не введут 'y' ('д') или 'n' ('н')
            }
        }
    }
}

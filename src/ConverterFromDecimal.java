public class ConverterFromDecimal {
    public static final int[] fibNumbers = new int[]{1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
            2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269,
            2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141,
            267914296, 433494437, 701408733, 1134903170, 1836311903};//задали неизменяемый массив для Фиб СС

    public static String convertFromDecimal(long decimalNumber, String systemC) { //мб любой ответ,
        // поэтому работаем со строками

        if (systemC.equalsIgnoreCase("Фиб")) {
            if (decimalNumber < 0) { //для Фиб СС только неотрицательные
                return "ОШИБКА!!!";
            }
            if (decimalNumber == 0) {
                return "0";
            }

            int index = 0;
            while (index < fibNumbers.length && fibNumbers[index] <= decimalNumber) {
                index++; //жадный метод, сначала найдем максимально возможный индекс
            }
            index--; //имхо проще так после цикла вернуться на шаг назад

            String result = "";

            while (index >= 0) {
                if (decimalNumber >= fibNumbers[index]) {
                    decimalNumber -= fibNumbers[index];
                    result += "1";
                } else {
                    result += "0";
                }
                index--;
            }
            return result;
        }

        if (systemC.equalsIgnoreCase("Факт") || systemC.equalsIgnoreCase("Фак")) {
            if (decimalNumber < 0) { //для Факт СС только неотрицательные
                return "ОШИБКА!!!";
            }
            if (decimalNumber == 0) {
                return "0";
            }

            String result = "";
            int divider = 2;

            while (decimalNumber > 0) {
                long remainder = decimalNumber % divider;
                result = remainder + result;
                decimalNumber /= divider;
                divider++;
            }
            return result;
        }

        String literalPartC = systemC.toUpperCase(); //Симм СС
        if (literalPartC.endsWith("C") || literalPartC.endsWith("С")) {
            if (decimalNumber == 0) {
                return "0";
            }

            String digitPartC = literalPartC.substring(0, literalPartC.length() - 1);
            int radix = Integer.parseInt(digitPartC);
            int halfOfRadix = radix / 2;
            String result = "";

            while (decimalNumber != 0) {
                long remainder = decimalNumber % radix; //long decimalNumber
                decimalNumber /= radix;

                if (remainder > halfOfRadix) {
                    remainder -= radix;
                    decimalNumber++;
                } else if (remainder < -halfOfRadix) {
                    remainder += radix;
                    decimalNumber--;
                }

                if (remainder < 0) {
                    remainder += radix;
                }

                char ch = Character.forDigit((int) remainder, radix); //remainder был long
                result = ch + result;
            }
            return result.toUpperCase();
        }

        int radix = Integer.parseInt(systemC); //числовые СС (2-36) +нега-позиционные

        if (decimalNumber == 0) {
            return "0";
        }

        if (radix < 0) { //нега-позиционные
            String result = "";

            while (decimalNumber != 0) {
                long remainder = decimalNumber % radix;
                decimalNumber /= radix;

                if (remainder < 0) {
                    remainder -= radix;
                    decimalNumber++;
                }
                char ch = Character.forDigit((int) remainder, Math.abs(radix));
                result = ch + result;
            }
            return result.toUpperCase();
        }

        String result = Long.toString(decimalNumber, radix).toUpperCase();
        return result.toUpperCase();
    }
}

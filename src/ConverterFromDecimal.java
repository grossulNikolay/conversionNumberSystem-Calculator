public class ConverterFromDecimal {
    public static final int[] fibNumbers = new int[]{1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
            2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269,
            2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141,
            267914296, 433494437, 701408733, 1134903170, 1836311903};//задали неизменяемый массив для Фиб СС

    public static String convertFromDecimal(int decimalNumber, String systemC) { //мб любой ответ,
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
                index++;
            }
            index--;

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
                int remainder = decimalNumber % divider;
                result = remainder + result;
                decimalNumber /= divider;
                divider++;
            }
            return result;
        }
        return "."; //пока затычка для Симм СС, нега-позиционных и дефолтных
    }
}

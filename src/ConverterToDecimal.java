public class ConverterToDecimal {
    public static final int[] fibNumbers = new int[]{1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
            2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269,
            2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141,
            267914296, 433494437, 701408733, 1134903170, 1836311903};
    public static final int[] factNumbers = new int[]{1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800,
            39916800, 479001600}; //задали 2 неизменяемых массива для Факт и Фиб СС

    public static long convertAtoDecimal(String numberA, String systemB) { //10-ричные можно в int

        if (systemB.equalsIgnoreCase("Фиб")) { //Фиб СС
            long decimalNumber = 0;
            int length = numberA.length();
            for (int index = length - 1; index >= 0; index--) {
                char ch = numberA.charAt(index);
                if (ch == '1') {
                    int fibIndex = length - 1 - index;
                    decimalNumber += fibNumbers[fibIndex];
                }
            }
            return decimalNumber;
        }

        if (systemB.equalsIgnoreCase("Факт") || systemB.equalsIgnoreCase("Фак")) { //Факт СС
            long decimalNumber = 0;
            int length = numberA.length();
            for (int index = length - 1; index >= 0; index--) {
                char ch = numberA.charAt(index);
                int digit = Character.digit(ch, 10);
                int factIndex = length - 1 - index;
                decimalNumber += digit * factNumbers[factIndex];
            }
            return decimalNumber;
        }

        String literalPartB = systemB.toUpperCase(); //Симм СС
        if (literalPartB.endsWith("C") || literalPartB.endsWith("С")) {
            String digitPartB = literalPartB.substring(0, literalPartB.length() -1);
            int radix = Integer.parseInt(digitPartB);
            long decimalNumber = 0;
            long power = 1;
            int halfRadix = radix / 2;
            int length = numberA.length();

            for (int i = length - 1; i >=0; i--) {
                char ch = numberA.charAt(i);
                int digit = Character.digit(ch, radix); //radix != 10, долго искал баг после копипаста

                if (digit > halfRadix) {
                    digit -= radix;
                }

                decimalNumber += digit * power;
                power *= radix;
            }
            return decimalNumber;
        }

        try { //числовые СС (2-36) +нега-позиционные(поэтому try даже после валидации)
            int radix = Integer.parseInt(systemB);

            if (radix < 0) {
                long decimalNumber = 0;
                int length = numberA.length();
                long power = 1;
                boolean isNegative = false;

                for (int i = length - 1; i >= 0; i--) {
                    char ch = numberA.charAt(i);

                    //обработка знака минус в начале числа для нега-позиционных
                    if (i == 0 && ch == '-') {
                        isNegative = true;
                        continue;
                    }

                    int digit = Character.digit(ch, Math.abs(radix));
                    decimalNumber += digit * power;
                    power *= radix;
                }

                if (isNegative) {
                    return -decimalNumber;
                } else {
                    return decimalNumber;
                }
            }

        long decimalNumber = Long.parseLong(numberA, radix); //числовые СС (2-36) !!!Long.parseLong()
        return decimalNumber;

        } catch (Exception error) {
            return 0;
        }
    }
}

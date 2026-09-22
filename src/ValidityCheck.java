public class ValidityCheck {
    public static boolean validityCheck(String numberA, String systemB) { //валидность только true/false

        if (systemB.equalsIgnoreCase("Факт") || systemB.equalsIgnoreCase("Фак")) { //Факт СС
            int length = numberA.length();
            int index = length - 1;

            while (index >= 0) {
                char ch = numberA.charAt(index);
                int digitValue = Character.digit(ch, 10);

                if (digitValue == -1) {
                    return false;
                }
                int positionFromRight = (length - 1) - index;
                int maxAllowed = positionFromRight + 1;
                if (digitValue > maxAllowed) {
                    return false;
                }
                index--;
            }
            return true;
        }

        if (systemB.equalsIgnoreCase("Фиб")) { //Фиб СС
            int index = numberA.length() - 1;

            while (index>=0) {
                char ch = numberA.charAt(index);
                if (ch != '0' && ch != '1') {
                    return false;
                }
                index--;
            }
            if (numberA.contains("11")) {
                return false;
            }
            if (numberA.endsWith("0")) {
                return false;
            }
            return true;
        }

        String literalPartB = systemB.toUpperCase(); //Симм СС с основанием от 3 до 73
        // ДОПИСАТЬ ОГРАНИЧЕНИЕ И ЛОГИКУ 3-73 ДЛЯ ВСЕГО КОДА ПРО СИММ СС
        if (literalPartB.endsWith("C") || literalPartB.endsWith("С")) {
            String digitPartB = literalPartB.substring(0, literalPartB.length() -1);
            try {
                int radix = Integer.parseInt(digitPartB);

                if (radix >= 3 && radix % 2 == 1) {
                    int index = numberA.length() - 1;
                    while (index >= 0) {
                        char ch = numberA.charAt(index);
                        int digitValue = Character.digit(ch, 10);
                        if (digitValue == -1 || Math.abs(digitValue) > radix / 2) {
                            return false;
                        }
                        index--;
                    }
                    return true;
                }
            } catch (Exception error) {
                    return false;
            }
        }

        try { //числовые СС (2-36)
            int radix = Integer.parseInt(systemB);
            if (Math.abs(radix) < 2 || Math.abs(radix) > 36) {      //+нега-позиционные
                return false;
            }

            int index = numberA.length() - 1;
            while (index >= 0) {
                char ch = numberA.charAt(index);

                if (index == 0 && ch == '-' && numberA.length() > 1) {
                    index--;
                    continue;
                } //для нега-позиционных

                if (Character.digit(ch, Math.abs(radix)) == -1) {
                    return false; //еще проверка (F подойдёт для 16-ричной, но не для 10-ричной)
                }
                index--;
            }
            return true;

        } catch (Exception error) {
            return false;
        }
    }
}

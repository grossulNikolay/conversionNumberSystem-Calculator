public class IsValidC {
    public static boolean isValidC(String systemC) { //валидность только true/false

        if (systemC.equalsIgnoreCase("Факт") || systemC.equalsIgnoreCase("Фак")) { //Факт СС
            return true;
        }

        if (systemC.equalsIgnoreCase("Фиб")) { //Фиб СС
            return true;
        }

        String letterPartB = systemC.toUpperCase(); //Симм СС
        if (letterPartB.endsWith("C") || letterPartB.endsWith("С")) {
            String digitPartB = letterPartB.substring(0, letterPartB.length() -1);
            try {
                int radix = Integer.parseInt(digitPartB);
                if (radix >= 74 || radix <= 2 || radix % 2 == 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception error) {
                return false;
            }
        }

        try { //числовые СС (2-36)
            int radix = Integer.parseInt(systemC);
            if (Math.abs(radix) < 2 || Math.abs(radix) > 36) { //+нега-позиционные
                return false;
            }
            return true;

        } catch (Exception error) {
            return false;
        }
    }
}

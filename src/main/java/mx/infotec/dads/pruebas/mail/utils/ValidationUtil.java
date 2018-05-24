package mx.infotec.dads.pruebas.mail.utils;

import java.util.regex.Pattern;

public final class ValidationUtil {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private ValidationUtil() {
    }

    public static boolean isMailValid(String email) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }

}

package io.github.magonxesp.authorization.utils;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CertUtils {

    public static String trimKey(String key) {
        Pattern pattern = Pattern.compile("^-+[A-Z ]+-+$", Pattern.CASE_INSENSITIVE);

        return Arrays.stream(key.split("\\n|\\r"))
                .filter(line -> !pattern.matcher(line).matches())
                .collect(Collectors.joining());
    }

}

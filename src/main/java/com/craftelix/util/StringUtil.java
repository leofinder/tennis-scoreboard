package com.craftelix.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@UtilityClass
public class StringUtil {

    public static String capitalizeFirstLetters(String original) {
        return Arrays.stream(original.strip().split("\\s+"))
                .map(word -> word.matches("\\p{L}.*")
                        ? word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1).toLowerCase(Locale.ROOT)
                        : word)
                .collect(Collectors.joining(" "));
    }
}

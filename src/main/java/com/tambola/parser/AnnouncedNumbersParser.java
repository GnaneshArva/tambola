package com.tambola.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnouncedNumbersParser {

    //    Example input: "90, 4, 46, 63, 89, 16, 76, 48"
    public List<Integer> parse(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Announced numbers string cannot be blank.");
        }
        return Arrays.stream(raw.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}

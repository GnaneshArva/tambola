package com.tambola.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {

    public static final int TOTAL_ROWS = 3;
    public static final int TOTAL_COLS = 9;

    private final List<List<Integer>> rows; // 3 rows x 9 cols; 0 = blank slot

    public Ticket(List<List<Integer>> rows) {
        if (rows == null) {
            throw new NullPointerException("Rows cannot be null");
        }

        if (rows.size() != TOTAL_ROWS) {
            throw new IllegalArgumentException("Ticket must have exactly 3 rows");
        }

        this.rows = rows.stream()
                .map(Collections::unmodifiableList)
                .toList();
    }

    public List<Integer> getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public List<Integer> getNumbersInRow(int rowIndex) {
        return rows.get(rowIndex).stream()
                .filter(n -> n != 0)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> getAllNumbers() {
        return rows.stream()
                .flatMap(row -> row.stream())
                .filter(n -> n != 0)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return rows.stream()
                .map(row -> row.stream().map(n -> n == 0 ? "_" : String.valueOf(n))
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
    }
}

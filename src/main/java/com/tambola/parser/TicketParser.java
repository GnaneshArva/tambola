package com.tambola.parser;

import com.tambola.model.Ticket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TicketParser {

    private static final String ROW_DELIMITER = "\n";
    private static final String COL_DELIMITER = ",";
    private static final String BLANK_SLOT = "_";
    private static final int BLANK_SLOT_VALUE = 0;

    /*
    Raw ticket string - 3 rows, 9 columns

        4,16,_,_,48,_,63,76,_
        7,_,23,38,_,52,_,_,80
        9,_,25,_,_,56,64,_,83
     */
    public Ticket parseTicket(String rawTicket) {
        String[] rawRows = rawTicket.trim().split(ROW_DELIMITER);
        if (rawRows.length != Ticket.TOTAL_ROWS) {
            throw new IllegalArgumentException(
                    "Ticket must have exactly " + Ticket.TOTAL_ROWS + " rows, got: " + rawRows.length);
        }

        List<List<Integer>> rows = Arrays.stream(rawRows)
                .map(this::parseRow)
                .collect(Collectors.toList());

        return new Ticket(rows);
    }

    private List<Integer> parseRow(String rawRow) {
        String[] columns = rawRow.trim().split(COL_DELIMITER);
        if (columns.length != Ticket.TOTAL_COLS) {
            throw new IllegalArgumentException(
                    "Each row must have " + Ticket.TOTAL_COLS + " columns, got: " + columns.length + " in row: " + rawRow);
        }
        return Arrays.stream(columns)
                .map(cell -> cell.trim().equals(BLANK_SLOT) ? BLANK_SLOT_VALUE : Integer.parseInt(cell.trim()))
                .collect(Collectors.toList());
    }
}

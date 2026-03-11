package com.tambola.game.strategy;

import com.tambola.model.Ticket;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

// Base strategy for row-line games (top, middle, bottom).
@RequiredArgsConstructor
abstract class RowLineStrategy implements WinningStrategy {

    private final int rowIndex;

    @Override
    public Set<Integer> getWinningNumbers(Ticket ticket) {
        return new HashSet<>(ticket.getNumbersInRow(rowIndex));
    }
}

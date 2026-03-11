package com.tambola.game.strategy;

import com.tambola.model.Ticket;

import java.util.HashSet;
import java.util.Set;

public class EarlyFiveStrategy implements WinningStrategy {

    @Override
    public Set<Integer> getWinningNumbers(Ticket ticket) {
        return new HashSet<>(ticket.getAllNumbers());
    }
}

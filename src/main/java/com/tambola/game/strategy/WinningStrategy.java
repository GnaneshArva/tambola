package com.tambola.game.strategy;

import com.tambola.model.Ticket;

import java.util.Set;

public interface WinningStrategy {

    Set<Integer> getWinningNumbers(Ticket ticket);
}

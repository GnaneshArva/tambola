package com.tambola.game.rule;

import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import com.tambola.model.Ticket;

import java.util.List;
import java.util.Set;

public class EarlyFiveGameRule implements GameRule {

    private static final int REQUIRED_COUNT = 5;

    @Override
    public ClaimResult validate(ClaimRequest request) {
        Ticket ticket = request.getTicket();
        List<Integer> announced = request.getAnnouncedNumbers();
        int lastNumber = request.getLastAnnouncedNumber();

        Set<Integer> ticketNumbers = Set.copyOf(ticket.getAllNumbers());

        // Count how many ticket numbers appear in announced list
        long matchCount = announced.stream()
                .filter(ticketNumbers::contains)
                .distinct()
                .count();

        boolean hasEarlyFive = matchCount >= REQUIRED_COUNT;
        boolean lastIsOnTicket = ticketNumbers.contains(lastNumber);

        // The last number must be the one that completed the 5th cross
        long matchCountWithoutLast = announced.subList(0, announced.size() - 1)
                .stream()
                .filter(ticketNumbers::contains)
                .distinct()
                .count();

        boolean lastCompletedFifth = lastIsOnTicket && matchCountWithoutLast == REQUIRED_COUNT - 1;

        if (hasEarlyFive && lastCompletedFifth) {
            return ClaimResult.accepted();
        }

        if (!hasEarlyFive) {
            return ClaimResult.rejected(
                    "Only " + matchCount + " of " + REQUIRED_COUNT + " required numbers announced.");
        }

        return ClaimResult.rejected(
                "Claim not made immediately after the 5th number was crossed. " +
                        "Last announced number '" + lastNumber + "' was not the 5th cross.");
    }
}

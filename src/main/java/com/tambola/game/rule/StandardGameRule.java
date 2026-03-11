package com.tambola.game.rule;

import com.tambola.game.strategy.WinningStrategy;
import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class StandardGameRule implements GameRule {

    private final WinningStrategy winningStrategy;

    @Override
    public ClaimResult validate(ClaimRequest request) {
        Set<Integer> required = winningStrategy.getWinningNumbers(request.getTicket());
        Set<Integer> announced = Set.copyOf(request.getAnnouncedNumbers());
        int lastNumber = request.getLastAnnouncedNumber();

        boolean allCrossed = announced.containsAll(required);
        boolean lastCompletesWin = required.contains(lastNumber);

        if (allCrossed && lastCompletesWin) {
            return ClaimResult.accepted();
        }

        if (!allCrossed) {
            return ClaimResult.rejected("Not all required numbers have been announced yet.");
        }

        return ClaimResult.rejected(
                "Claim not made immediately after completing the winning sequence. " +
                        "Last announced number '" + lastNumber + "' did not complete the pattern.");
    }
}

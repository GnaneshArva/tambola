package com.tambola.validator;

import com.tambola.game.rule.GameRule;
import com.tambola.game.rule.GameRuleFactory;
import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClaimValidator {

    private final GameRuleFactory gameRuleFactory;

    public ClaimResult validate(ClaimRequest request) {
        if (request == null) {
            throw new NullPointerException("ClaimRequest cannot be null");
        }

        if (request.getAnnouncedNumbers().isEmpty()) {
            return ClaimResult.rejected("No numbers have been announced yet.");
        }
        GameRule rule = gameRuleFactory.getRuleFor(request.getGameType());
        return rule.validate(request);
    }
}

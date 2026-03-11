package com.tambola.game.rule;

import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;

public interface GameRule {

    ClaimResult validate(ClaimRequest request);
}

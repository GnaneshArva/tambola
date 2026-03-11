package com.tambola;

import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import com.tambola.model.GameType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Bottom Line")
class BottomLineTest extends TambolaClaimValidatorBaseTest {

    @Test
    @DisplayName("accepted — last number completes the bottom row")
    void accepted_lastNumberCompletesBottomRow() {
        List<Integer> announced = numbersParser.parse("9, 25, 56, 64, 83");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.BOTTOM_LINE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("rejected — all bottom numbers announced but extra number followed")
    void rejected_lateClaim_extraNumberAfterCompletion() {
        List<Integer> announced = numbersParser.parse("9, 25, 56, 64, 83, 100");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.BOTTOM_LINE));
        assertFalse(result.isAccepted(), "Should be rejected: claim made too late");
    }

    @Test
    @DisplayName("rejected — only one number missing from bottom row")
    void rejected_oneNumberStillMissing() {
        List<Integer> announced = numbersParser.parse("9, 25, 56, 64");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.BOTTOM_LINE));
        assertFalse(result.isAccepted(), "Should be rejected: 83 not yet announced");
    }
}
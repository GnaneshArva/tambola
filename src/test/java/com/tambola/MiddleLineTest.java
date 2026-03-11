package com.tambola;

import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import com.tambola.model.GameType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Middle Line")
class MiddleLineTest extends TambolaClaimValidatorBaseTest {

    @Test
    @DisplayName("accepted — last number completes the middle row")
    void accepted_lastNumberCompletesMiddleRow() {
        List<Integer> announced = numbersParser.parse("5, 7, 23, 38, 52, 80");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.MIDDLE_LINE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("accepted — many unrelated numbers mixed in sequence")
    void accepted_withManyExtraNoiseNumbers() {
        List<Integer> announced = numbersParser.parse("1, 2, 7, 3, 23, 5, 38, 6, 52, 8, 80");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.MIDDLE_LINE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("rejected — not all middle row numbers announced")
    void rejected_notAllMiddleNumbersAnnounced() {
        List<Integer> announced = numbersParser.parse("5, 7, 23, 38, 52");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.MIDDLE_LINE));
        assertFalse(result.isAccepted(), "Should be rejected: 80 not announced");
    }

    @Test
    @DisplayName("rejected — only top line numbers announced, middle line claimed")
    void rejected_topLineCompleteButMiddleLineClaimed() {
        List<Integer> announced = numbersParser.parse("4, 16, 48, 63, 76");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.MIDDLE_LINE));
        assertFalse(result.isAccepted(), "Should be rejected: middle row incomplete");
    }
}
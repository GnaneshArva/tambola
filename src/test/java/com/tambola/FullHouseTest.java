package com.tambola;

import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import com.tambola.model.GameType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Full House")
class FullHouseTest extends TambolaClaimValidatorBaseTest {

    @Test
    @DisplayName("accepted — all 15 numbers announced and last completes the ticket")
    void accepted_allFifteenNumbersAnnounced() {
        List<Integer> announced = numbersParser.parse(
                "4, 16, 48, 63, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64, 83");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.FULL_HOUSE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("rejected — one number still missing")
    void rejected_notAllFifteenAnnounced() {
        List<Integer> announced = numbersParser.parse(
                "4, 16, 48, 63, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.FULL_HOUSE));
        assertFalse(result.isAccepted(), "Should be rejected: not all 15 announced");
    }

    @Test
    @DisplayName("rejected — all 15 announced but non-ticket number came after")
    void rejected_lateClaim_nonTicketNumberAfterWin() {
        List<Integer> announced = numbersParser.parse(
                "4, 16, 48, 63, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64, 83, 55");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.FULL_HOUSE));
        assertFalse(result.isAccepted(), "Should be rejected: extra number after win");
    }

    @Test
    @DisplayName("rejected — only top line complete, full house claimed")
    void rejected_onlyTopLineComplete_fullHouseClaimed() {
        List<Integer> announced = numbersParser.parse("4, 16, 48, 63, 76");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.FULL_HOUSE));
        assertFalse(result.isAccepted(), "Should be rejected: only 5 of 15 crossed");
    }
}
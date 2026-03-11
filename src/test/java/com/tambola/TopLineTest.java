package com.tambola;

import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import com.tambola.model.GameType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Top Line")
class TopLineTest extends TambolaClaimValidatorBaseTest {

    @Test
    @DisplayName("PDF Ex1: accepted — last number completes the top row")
    void accepted_lastNumberCompletesTopRow() {
        List<Integer> announced = numbersParser.parse("90, 4, 46, 63, 89, 16, 76, 48");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.TOP_LINE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("PDF Ex2: rejected — claim made one number too late")
    void rejected_claimMadeOneNumberLate() {
        List<Integer> announced = numbersParser.parse("90, 4, 46, 63, 89, 16, 76, 48, 12");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.TOP_LINE));
        assertFalse(result.isAccepted(), "Should be rejected: claim was late");
    }

    @Test
    @DisplayName("accepted — numbers announced in reverse order")
    void accepted_numbersAnnouncedInReverseOrder() {
        List<Integer> announced = numbersParser.parse("76, 63, 48, 16, 4");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.TOP_LINE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("rejected — last announced number is not on ticket at all")
    void rejected_lastNumberNotOnTicket() {
        List<Integer> announced = numbersParser.parse("4, 16, 63, 76, 48, 99");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.TOP_LINE));
        assertFalse(result.isAccepted(), "Should be rejected: last number not on ticket");
    }

    @Test
    @DisplayName("rejected — no numbers announced")
    void rejected_noNumbersAnnounced() {
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, List.of(), GameType.TOP_LINE));
        assertFalse(result.isAccepted(), "Should be rejected: nothing announced");
    }
}
package com.tambola;

import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import com.tambola.model.GameType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Early Five")
class EarlyFiveTest extends TambolaClaimValidatorBaseTest {

    @Test
    @DisplayName("accepted — 5th ticket match is the last announced number")
    void accepted_fifthMatchIsLastAnnounced() {
        List<Integer> announced = numbersParser.parse("90, 4, 46, 16, 63, 76, 48");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.EARLY_FIVE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("accepted — first 5 announced are all on ticket")
    void accepted_firstFiveAnnouncedAllOnTicket() {
        List<Integer> announced = numbersParser.parse("4, 16, 48, 63, 76");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.EARLY_FIVE));
        assertTrue(result.isAccepted(), result::getReason);
    }

    @Test
    @DisplayName("rejected — claim made after the 6th ticket number announced")
    void rejected_lateClaim_sixthTicketNumberAnnounced() {
        List<Integer> announced = numbersParser.parse("90, 4, 46, 16, 63, 76, 48, 7");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.EARLY_FIVE));
        assertFalse(result.isAccepted(), "Should be rejected: claim was late");
    }

    @Test
    @DisplayName("rejected — only 4 ticket numbers announced")
    void rejected_onlyFourTicketNumbersAnnounced() {
        List<Integer> announced = numbersParser.parse("90, 4, 46, 16, 63, 76");
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, GameType.EARLY_FIVE));
        assertFalse(result.isAccepted(), "Should be rejected: only 4 matches");
    }

    @Test
    @DisplayName("rejected — zero numbers announced")
    void rejected_zeroNumbersAnnounced() {
        ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, List.of(), GameType.EARLY_FIVE));
        assertFalse(result.isAccepted(), "Should be rejected: nothing announced");
    }
}
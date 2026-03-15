package com.tambola;

import com.tambola.exception.UnsupportedGameTypeException;
import com.tambola.model.ClaimRequest;
import com.tambola.model.ClaimResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Unsupported Game Type Test")
class UnsupportedGameTypeTest extends TambolaClaimValidatorBaseTest {

    @Test
    @DisplayName("Exception thrown for unsupported game type.")
    void exception_WhenUnsupportedGameTypeGiven() {
        Assertions.assertThrows(UnsupportedGameTypeException.class, () -> {
            List<Integer> announced = numbersParser.parse("9, 25, 56, 64, 83");
            ClaimResult result = claimValidator.validate(new ClaimRequest(ticket, announced, null));
            assertTrue(result.isAccepted(), result::getReason);
        });

    }
}
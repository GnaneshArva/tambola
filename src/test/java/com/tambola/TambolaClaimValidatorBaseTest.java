package com.tambola;

import com.tambola.game.rule.GameRuleFactory;
import com.tambola.model.Ticket;
import com.tambola.parser.AnnouncedNumbersParser;
import com.tambola.parser.TicketParser;
import com.tambola.validator.ClaimValidator;
import org.junit.jupiter.api.BeforeEach;

public abstract class TambolaClaimValidatorBaseTest {

    protected TicketParser ticketParser;
    protected AnnouncedNumbersParser numbersParser;
    protected ClaimValidator claimValidator;
    protected Ticket ticket;

    @BeforeEach
    void setUp() {
        ticketParser   = new TicketParser();
        numbersParser  = new AnnouncedNumbersParser();
        claimValidator = new ClaimValidator(new GameRuleFactory());

        ticket = ticketParser.parseTicket(
                "4,16,_,_,48,_,63,76,_\n" +
                        "7,_,23,38,_,52,_,_,80\n" +
                        "9,_,25,_,_,56,64,_,83");
    }
}
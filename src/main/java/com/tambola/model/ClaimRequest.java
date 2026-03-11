package com.tambola.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClaimRequest {

    private Ticket ticket;
    private List<Integer> announcedNumbers;
    private GameType gameType;


    public int getLastAnnouncedNumber() {
        if (announcedNumbers.isEmpty()) {
            throw new IllegalStateException("No numbers announced yet.");
        }
        return announcedNumbers.get(announcedNumbers.size() - 1);
    }
}

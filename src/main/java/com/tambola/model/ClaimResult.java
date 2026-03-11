package com.tambola.model;

import lombok.Data;


@Data
public final class ClaimResult {

    private final Status status;
    private final String reason;

    public static ClaimResult accepted() {
        return new ClaimResult(Status.ACCEPTED, "Claim is valid.");
    }

    public static ClaimResult rejected(String reason) {
        return new ClaimResult(Status.REJECTED, reason);
    }

    public boolean isAccepted() {
        return status == Status.ACCEPTED;
    }

    public enum Status {
        ACCEPTED, REJECTED
    }
}

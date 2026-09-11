package service.factory;

import enums.ATMStatus;
import service.AtmService;
import service.state.AtmState;
import service.state.AuthenticatedState;
import service.state.CardInsertedState;
import service.state.DispenseCashState;
import service.state.IdleState;

public class AtmStateFactory {
    public static AtmState getState(ATMStatus status, AtmService machine) {
        return switch (status) {
            case IDLE -> new IdleState(machine);
            case CARD_INSERTED -> new CardInsertedState(machine);
            case AUTHENTICATED -> new AuthenticatedState(machine);
            case DISPENSE_CASH -> new DispenseCashState(machine);
            default -> throw new IllegalArgumentException("Unknown ATM status: " + status);
        };
    }
}

package service.state;

import dto.Card;
import enums.ATMStatus;
import service.AtmService;

import static service.factory.AtmStateFactory.getState;

public class CardInsertedState implements AtmState{

    private AtmService atmMachine;
    public CardInsertedState(AtmService machine) {
        atmMachine = machine;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Invalid Action Performed");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Validating Entered Pin against Card's PIN");
        if(pin.equals(atmMachine.getCurrentCard().getPin())) {
            System.out.println("Welcome! , PIN Validated Successfully");
            System.out.println("Please Select ONE of the Options");
            System.out.println("---------------");
            System.out.println("CHECK_BALANCE");
            System.out.println("CASH_WITHDRAW");
            System.out.println("CASH_DEPOSIT");
            System.out.println("MINI_STATEMENT");
            System.out.println("---------------");
            atmMachine.setAtmState(new AuthenticatedState(atmMachine));
        } else {
            System.out.println("Opps! , Wrong PIN Entered");
            ejectCard();
        }
    }

    @Override
    public void selectOption(String option) {

    }

    @Override
    public void dispenseCash(int amount) {

    }

    @Override
    public void ejectCard() {
        atmMachine.setCurrentCard(null);
        System.out.println("Card ejected.");
        atmMachine.setAtmState(getState(ATMStatus.IDLE,atmMachine));
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.CARD_INSERTED;
    }
}

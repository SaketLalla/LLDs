package service.state;

import dto.Card;
import enums.ATMStatus;
import service.AtmService;

import static service.factory.AtmStateFactory.getState;

public class IdleState implements AtmState{
    private AtmService atmMachine;
    public IdleState(AtmService machine) {
        atmMachine = machine;
    }
    @Override
    public void insertCard(Card card) {
        atmMachine.setCurrentCard(card);
        System.out.println("Card Inserted Successfully for User : "+ card.getAccount().getName());
        atmMachine.setAtmState(new CardInsertedState(atmMachine));
    }

    @Override
    public void enterPin(String pin) {

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
        return ATMStatus.IDLE;
    }
}

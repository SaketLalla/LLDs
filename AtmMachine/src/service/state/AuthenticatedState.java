package service.state;

import dto.Card;
import enums.ATMStatus;
import service.AtmService;

import static service.factory.AtmStateFactory.getState;

public class AuthenticatedState implements AtmState{

    private AtmService atmMachine;
    public AuthenticatedState(AtmService machine) {
        atmMachine = machine;
    }


    @Override
    public void insertCard(Card card) {

    }

    @Override
    public void enterPin(String pin) {

    }

    @Override
    public void selectOption(String option) {
        if(option.equals("CASH_WITHDRAW")) {
            System.out.println("Cash WithDrawl Option Selected , Please enter the amount");
            atmMachine.setAtmState(new DispenseCashState(atmMachine));
        } else {
            System.out.println("Invalid Option Selected");
            ejectCard();
      ;
    }
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
        return ATMStatus.AUTHENTICATED;
    }
}

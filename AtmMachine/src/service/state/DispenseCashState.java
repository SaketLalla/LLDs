package service.state;

import dto.Card;
import enums.ATMStatus;
import service.AtmService;
import service.dispenser.CashDispenser;

import static service.factory.AtmStateFactory.getState;
import static utils.DispenserBuilder.buildChain;

public class DispenseCashState implements AtmState{

    private AtmService atmMachine;
    public DispenseCashState(AtmService machine) {
        atmMachine = machine;
    }

    private final CashDispenser chain = buildChain();

    @Override
    public void insertCard(Card card) {

    }

    @Override
    public void enterPin(String pin) {

    }

    @Override
    public void selectOption(String option) {

    }

    @Override
    public void dispenseCash(int amount) {
        double atmBalance = atmMachine.getAtm().getCashAvailable();
        double accountBalance = atmMachine.getCurrentCard()
                .getAccount()
                .getBalance();

        if (amount > atmBalance) {
            System.out.println("ATM has insufficient cash. Cannot dispense " + amount);
            ejectCard();
            return;
        }

        if (amount > accountBalance) {
            System.out.println("Insufficient account balance.");
            ejectCard();
            return;
        }

        if(chain.canDispense(atmMachine.getAtm(), amount)) {
            //Sufficient Notes Exist for Cash Withdraw , proceeding w withdrawing
            chain.dispense(atmMachine.getAtm(), amount);
            //once that is done , maintain ATM & user balance
            atmMachine.getAtm().setCashAvailable(atmBalance - amount);
            atmMachine.getCurrentCard().getAccount().setBalance(accountBalance - amount);

            System.out.println("Cash dispensed: " + amount + " for user : "+ atmMachine.getCurrentCard().getAccount().getName());
            ejectCard();

        } else {
            System.out.println("Cannot dispense requested amount with available denominations.");
            ejectCard();
        }
    }

    @Override
    public void ejectCard() {
        atmMachine.setCurrentCard(null);
        System.out.println("Card ejected.");
        atmMachine.setAtmState(getState(ATMStatus.IDLE,atmMachine));
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.DISPENSE_CASH;
    }
}

package service;

import dto.Atm;
import dto.Card;
import service.state.AtmState;

import static service.factory.AtmStateFactory.getState;

public class AtmService {
    private Atm atm;
    private AtmState atmState;
    private Card currentCard;

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public AtmState getAtmState() {
        return atmState;
    }

    public void setAtmState(AtmState atmState) {
        this.atmState = atmState;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public AtmService(Atm atm) {
        this.atm = atm;
        this.atmState = getState(atm.getStatus(), this);
    }

    public void insertCard(Card card) {
        atmState.insertCard(card);
    }

    public void enterPin(String pin) {
        atmState.enterPin(pin);
    }

    public void selectOption(String option) {
        atmState.selectOption(option);
    }

    public void dispenseCash(int amount) {
        atmState.dispenseCash(amount);
    }

    public void ejectCard() {
        atmState.ejectCard();
    }

}

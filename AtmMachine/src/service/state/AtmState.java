package service.state;

import dto.Card;
import enums.ATMStatus;

public interface AtmState {

    void insertCard(Card card);
    void enterPin(String pin);
    void selectOption(String option);
    void dispenseCash(int amount);
    void ejectCard();
    ATMStatus getStatus();
}

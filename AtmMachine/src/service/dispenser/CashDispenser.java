package service.dispenser;

import dto.Atm;

public interface CashDispenser {
    void setNextDispenser(CashDispenser next);
    boolean canDispense(Atm atm, int amount);
    void dispense(Atm atm, int amount);
}

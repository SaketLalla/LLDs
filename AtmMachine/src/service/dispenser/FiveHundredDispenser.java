package service.dispenser;

import dto.Atm;

public class FiveHundredDispenser implements CashDispenser{
    private CashDispenser dispenser;

    @Override
    public void setNextDispenser(CashDispenser next) {
        dispenser = next;
    }

    @Override
    public boolean canDispense(Atm atm, int amount) {
        int count = atm.getFiveHundredCount();
        int notes = Math.min(amount / 500, count);
        int remainder = amount - notes * 500;
        if(remainder == 0) return true;
        return dispenser != null && dispenser.canDispense(atm,remainder);
    }

    @Override
    public void dispense(Atm atm, int amount) {
        int count = atm.getFiveHundredCount();
        int notes = Math.min(amount / 500, count);
        atm.setFiveHundredCount(count - notes);

        int remainder = amount - notes * 500;

        if(notes > 0) System.out.println("Dispensed " + notes + " x 500 notes");

        if (remainder > 0 && dispenser != null) {
            dispenser.dispense(atm, remainder);
        }
    }
}

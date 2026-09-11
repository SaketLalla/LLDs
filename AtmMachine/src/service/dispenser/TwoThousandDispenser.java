package service.dispenser;

import dto.Atm;

public class TwoThousandDispenser implements CashDispenser{
    private CashDispenser dispenser;

    @Override
    public void setNextDispenser(CashDispenser next) {
        dispenser = next;
    }

    @Override
    public boolean canDispense(Atm atm, int amount) {
        int count = atm.getTwoThousandCount();
        int notes = Math.min(amount / 2000, count);
        int remainder = amount - notes * 2000;
        if(remainder == 0) return true;
        return dispenser != null && dispenser.canDispense(atm,remainder);
    }

    @Override
    public void dispense(Atm atm, int amount) {
        int count = atm.getTwoThousandCount();
        int notes = Math.min(amount / 2000, count);
        atm.setTwoThousandCount(count - notes);

        int remainder = amount - notes * 2000;

        if(notes > 0) System.out.println("Dispensed " + notes + " x 2000 notes");

        if (remainder > 0 && dispenser != null) {
            dispenser.dispense(atm, remainder);
        }
    }
}

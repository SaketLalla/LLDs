package service.LockerServiceState;

import dto.Locker;
import dto.LockerMachine;
import dto.LockerState;
import dto.Packagee;
import enums.LockerStatus;

public class CustomerPickupState implements LockerState {
    private final LockerMachine machine;

    public CustomerPickupState(LockerMachine machine) {
        this.machine = machine;
    }

    @Override
    public void touch() {

    }

    @Override
    public void validateCode(Packagee packagee, String lockerName) {

    }

    @Override
    public void closeDoor(Locker chosernLocker, String slotId, Packagee pkg) {

    }

    @Override
    public void selectCarrierEntry() {
        System.out.println("Carrier Entry Selected -> Switching to CARRIER ENTRY");
        machine.setState(new CarrierEntryState(machine));
    }

    @Override
    public void selectOption(String option) {

    }

    @Override
    public LockerStatus getLockerStatus() {
        return null;
    }
}

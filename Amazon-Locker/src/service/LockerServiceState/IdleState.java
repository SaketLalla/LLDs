package service.LockerServiceState;

import dto.Locker;
import dto.LockerMachine;
import dto.LockerState;
import dto.Packagee;
import enums.LockerStatus;

public class IdleState implements LockerState {
    private final LockerMachine machine;

    public IdleState(LockerMachine machine) {
        this.machine = machine;
    }

    @Override
    public void touch() {
        System.out.println("Login Success -> Switching to CUSTOMER PICKUP");
        machine.setState(new CustomerPickupState(machine));
    }

    @Override
    public void validateCode(Packagee packagee, String lockerName) {

    }

    @Override
    public void closeDoor(Locker chosernLocker, String slotId, Packagee pkg) {

    }

    @Override
    public void selectCarrierEntry() {

    }

    @Override
    public void selectOption(String option) {

    }

    @Override
    public LockerStatus getLockerStatus() {
        return null;
    }
}

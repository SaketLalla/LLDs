package service.LockerServiceState;

import dto.Locker;
import dto.LockerMachine;
import dto.LockerState;
import dto.Packagee;
import enums.LockerStatus;

public class CarrierEntryState implements LockerState {
    private final LockerMachine machine;

    public CarrierEntryState(LockerMachine machine) {
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

    }

    @Override
    public void selectOption(String option) {
        if("DROP_PACKAGE".equals(option)) {
            System.out.println("Agent came for Dropping the package");
            System.out.println("Drop Package Selected -> Switching to AGENT DELIVERY State");
            machine.setState(new AgentDeliveryState(machine));
        } else {
            System.out.println("Invalid Option");
            machine.setState(new IdleState(machine));
        }
    }

    @Override
    public LockerStatus getLockerStatus() {
        return null;
    }
}

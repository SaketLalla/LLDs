package service.LockerServiceState;

import dto.Locker;
import dto.LockerMachine;
import dto.LockerState;
import dto.OtpInfo;
import dto.Packagee;
import enums.LockerStatus;
import enums.PackageStatus;

public class AgentDeliveryState implements LockerState {

    private final LockerMachine machine;

    public AgentDeliveryState(LockerMachine machine) {
        this.machine = machine;
    }

    @Override
    public void touch() {

    }

    @Override
    public void validateCode(Packagee packagee, String lockerName) {
    // match packageId belongs to the locker or not
    // get the slot from packageId and open it
    // deliver the package
        System.out.println(packagee.getAgentId() +" opened slot : "+ packagee.getSlotId() +
            " at Locker : "+lockerName +" and delivered the package");
    }

    @Override
    public void closeDoor(Locker chosernLocker, String slotId, Packagee pkg) {
        pkg.setStatus(PackageStatus.STORED_IN_LOCKER);

        OtpInfo otpInfo = machine.getOtpService().generateOTP();
        System.out.println("Door is Closed & OTP has been sent");
        machine.getNotificationService().notfifyCustomer(otpInfo);
        System.out.println("Process Complete , Switching to Idle");
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

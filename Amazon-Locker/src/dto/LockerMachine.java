package dto;

import enums.LockerStatus;
import service.LockerService;
import service.LockerServiceState.IdleState;
import service.NotificationService;
import service.OtpService;

public class LockerMachine {
    private final LockerService lockerService;
    private final NotificationService notificationService;
    private final OtpService otpService;

    public LockerService getLockerService() {
        return lockerService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public OtpService getOtpService() {
        return otpService;
    }

    public Locker getLocker() {
        return locker;
    }

    public LockerState getLockerState() {
        return lockerState;
    }

    public void setLockerState(LockerState lockerState) {
        this.lockerState = lockerState;
    }

    private final Locker locker;

    private LockerState lockerState;

    public LockerMachine(Locker lkr , LockerService lockerService1 , NotificationService notifyService , OtpService OTPService) {
        locker = lkr;
        lockerService = lockerService1;
        notificationService = notifyService;
        otpService = OTPService;
        lockerState = new IdleState(this);
    }

    public void setState(LockerState newState) {
        lockerState = newState;
    }

    public void touch() {
        lockerState.touch();
    }

    public void validateCode(Packagee pkg , String lockerName) {
        lockerState.validateCode(pkg,lockerName);
    }

    public void closeDoor(Locker chosernLocker, String slotId, Packagee pkg){
        lockerState.closeDoor(chosernLocker,slotId,pkg);
    }

    public void selectCarrierEntry(){
        lockerState.selectCarrierEntry();
    }

    public void selectOption(String option){
        lockerState.selectOption(option);
    }

    public LockerStatus getLockerStatus(){
        return null;
    }
}

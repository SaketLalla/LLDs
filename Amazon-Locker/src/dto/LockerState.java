package dto;

import enums.LockerStatus;

public interface LockerState {

    void touch();

    void validateCode(Packagee packagee , String lockerName);

    void closeDoor(Locker chosernLocker, String slotId, Packagee pkg);

    void selectCarrierEntry();

    void selectOption(String option);

    LockerStatus getLockerStatus();
}

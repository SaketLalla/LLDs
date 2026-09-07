package service;

import dto.Locker;
import dto.Packagee;
import dto.Slot;

import java.util.List;

public class LockerService {
    public List<Locker> getLockerByZipCode() {
        Locker l1 = new Locker("Locker-123","123");
        Locker l2 = new Locker("Locker-456","456");
        return List.of(l1,l2);
    }

    public void reserveSlot(Locker chosenLocker , Packagee pkg) {
        //call a method to get all Slots
        List<Slot> availableSlots = List.of(new Slot(true,"SLOT123") , new Slot(false,"SL456"));

        //use strategy to resevre a slot
        Slot reservedSlot = availableSlots.stream().filter(Slot::isAvailable).findFirst().orElse(null);

        if(null == reservedSlot) {
            System.out.println("No Slots Available");
            return;
        }

        pkg.setLockerName(chosenLocker.getName());
        pkg.setSlotId(reservedSlot.getSlotId());

        System.out.println("Reserved Slot : "+ reservedSlot.getSlotId());
  }
}

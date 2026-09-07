import dto.Locker;
import dto.LockerMachine;
import dto.Packagee;
import enums.PackageStatus;
import service.AgentService;
import service.LockerService;
import service.NotificationService;
import service.OtpService;


void main() {
  LockerService lockerService = new LockerService();
  AgentService agentService = new AgentService();
  NotificationService notificationService = new NotificationService();
  OtpService otpService = new OtpService();
  //User Selects the Locker
  List<Locker> eligibleLockers = lockerService.getLockerByZipCode();
  if(eligibleLockers.isEmpty()) {
    throw new RuntimeException("No Lockers Found , Please try different location");
  }
  Locker chosenLocker = eligibleLockers.get(0);
  System.out.println("User Chose locker "+ chosenLocker.getName());

  //Take the package and reserve a SLOT
  Packagee pkg = new Packagee();
  lockerService.reserveSlot(chosenLocker , pkg);
  System.out.println("-----------------------------------");

  //Assign a Delivery Partner for
  agentService.assignAgentForDelivery(chosenLocker, pkg);

  System.out.println("-----------------------------------");

  //Locker MACHINE set up
  LockerMachine machine = new LockerMachine(chosenLocker, lockerService, notificationService , otpService);

  //Agent came to Deliver
  pkg.setStatus(PackageStatus.OUT_FOR_DELIVERY);
  machine.touch();
  machine.selectCarrierEntry();
  machine.selectOption("DROP_PACKAGE");
  machine.validateCode(pkg, chosenLocker.getName());
  machine.closeDoor(chosenLocker, pkg.getSlotId(), pkg);
  System.out.println("-----------------------------------");



}

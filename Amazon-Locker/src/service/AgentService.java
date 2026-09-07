package service;

import dto.DeliveryAgent;
import dto.Locker;
import dto.Packagee;
import enums.PackageStatus;

public class AgentService {
    NotificationService notificationService = new NotificationService();
    public void assignAgentForDelivery(Locker chosenLocker , Packagee pkg) {
        //Can use Strategy pattern here to assign a driver
        String zipCode = chosenLocker.getZipCode();

        //fetch Delivery Agents based on that ZipCode & fetch the appropriate one using stragegy
        // can be no. of hours , location closeness etc
        DeliveryAgent agent = new DeliveryAgent("AgentVinod");

//        if(null == agent) {
//            System.out.println("No Agents Available");
//            return;
//        }
        pkg.setAgentId(agent.getAgentName());
        pkg.setStatus(PackageStatus.ASSIGNED_TO_AGENT);

        notificationService.notifyAgent(agent,pkg);
    }
}

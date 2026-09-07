package service;

import dto.DeliveryAgent;
import dto.OtpInfo;
import dto.Packagee;

import java.time.format.DateTimeFormatter;

public class NotificationService {
    void notifyAgent(DeliveryAgent agent , Packagee pkg) {
        System.out.println(agent.getAgentName() + " has been notified to deliver the package : "+ pkg.getLockerName());
    }

    public void notfifyCustomer(OtpInfo otpInfo) {
    System.out.println("Please Use this OTP : "+ otpInfo.getOtp() +" .OTP will get expired on "+ otpInfo.getExpiryTime().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}

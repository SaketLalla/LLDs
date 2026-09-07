package service;

import dto.OtpInfo;

import java.time.LocalDateTime;

public class OtpService {
    public OtpInfo generateOTP() {
        OtpInfo otpInfo = new OtpInfo();
        otpInfo.setExpiryTime(LocalDateTime.now().plusDays(3));
        otpInfo.setOtp("123456");
        return  otpInfo;
    }
}

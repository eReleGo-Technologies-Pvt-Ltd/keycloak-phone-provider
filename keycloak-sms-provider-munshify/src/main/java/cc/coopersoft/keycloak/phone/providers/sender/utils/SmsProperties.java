package cc.coopersoft.keycloak.phone.providers.sender.utils;

public interface SmsProperties {
    
    public static final String USER_NAME = "7406339559";
    
    public static final String API_PASSWORD = "0b081sgm9wo858j0c";
    
    public static final String API_URL = "http://sms.egenius.in/pushsms.php";
    
    public static final String EXPIRY_TIME_IN_MINS = "15";
    
    public static final String ENTITY_ID = "1401431560000050930";
    
    public static final String TEMPLATE_ID = "1407166538256964841";
    
    public static final String SENDER_ID = "MNSHFY";
    
    public static final String DEFAULT_EMAILS = "gulshanmohanty007@gmail.com,sukshithashetty1998@gmail.com";
    
    public static final String DEFAULT_MOBILE_NUMBERS = "8249210815,7624841503";
    
    public static final String OTP_SMS_MSG = "Dear ${user}, ${otp} is the OTP to verify your mobile number on ${datetime}. OTP is valid for 15 minutes. DO NOT disclose it to anyone\n\n- Team Munshify";

}

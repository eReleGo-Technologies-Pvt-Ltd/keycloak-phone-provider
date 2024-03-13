package cc.coopersoft.keycloak.phone.providers.sender;

import cc.coopersoft.keycloak.phone.providers.constants.TokenCodeType;
import cc.coopersoft.keycloak.phone.providers.exception.MessageSendException;
import cc.coopersoft.keycloak.phone.providers.spi.MessageSenderService;
import org.keycloak.Config;
import org.keycloak.models.RealmModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import cc.coopersoft.keycloak.phone.providers.sender.utils.SmsProperties;
import cc.coopersoft.keycloak.phone.providers.sender.utils.Utils;
import cc.coopersoft.keycloak.phone.providers.sender.exceptions.IntegrationException;
import cc.coopersoft.keycloak.phone.providers.sender.utils.RestUtility;


public class MunshifySmsSenderService implements MessageSenderService {
	
	  private final Config.Scope config;
	  private final RealmModel realm;
	  
	  private static final Logger LOGGER = LoggerFactory.getLogger(MunshifySmsSenderService.class); 

	  public MunshifySmsSenderService(Config.Scope config, RealmModel realm) {
	    this.config = config;
	    this.realm = realm;
	  }

	
	  @Override
	  public void sendSmsMessage(TokenCodeType type, String phoneNumber, String code, int expires, String kind) throws MessageSendException {
	 
	      LOGGER.error("Inside sendSms");
				
	      Map<String, String> valuesMap = new HashMap<>();
      
	      valuesMap.put("user", "User");
      
	      valuesMap.put("otp", code);
      
	      valuesMap.put("datetime", LocalDateTime.now().atZone(ZoneId.of("Asia/Kolkata"))
              .format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
      
	      String response = Utils.convertTemplateToBody(SmsProperties.OTP_SMS_MSG, valuesMap);
      
      
	      LOGGER.error("response -- {}",response);
      
      
	      String priority = "11";
      
	      String charset = "UTF-8";
      
	      //String to = String.join(",", toList);
	      
	      LOGGER.error("Phone with country code :: {}",phoneNumber);
	      
	      phoneNumber = phoneNumberWithoutCountryCode(phoneNumber);
	      
	      LOGGER.error("Phone without country code :: {}",phoneNumber);
      
      
	      try {

		  LOGGER.error("smsProperties.getUserName() -- {}",SmsProperties.USER_NAME);
      	   
		  LOGGER.error("smsProperties.getApiPassword() -- {}",SmsProperties.API_PASSWORD);
      	   
		  LOGGER.error("smsProperties.getSenderId() -- {}",SmsProperties.SENDER_ID);
      	    
		  LOGGER.error("smsProperties.getEntityId() -- {}",SmsProperties.ENTITY_ID);
      	    
		  LOGGER.error("smsProperties.getTemplateId() -- {}",SmsProperties.TEMPLATE_ID);

		  String query = String.format( 
			  "username=%s&api_password=%s&sender=%s&to=%s&message=%s&priority=%s&e_id=%s&t_id=%s",
	                   URLEncoder.encode(SmsProperties.USER_NAME, charset),
	                   URLEncoder.encode(SmsProperties.API_PASSWORD, charset),
	                   URLEncoder.encode(SmsProperties.SENDER_ID, charset), phoneNumber,
	                   URLEncoder.encode(response, charset), URLEncoder.encode(priority, charset),
	                   URLEncoder.encode(SmsProperties.ENTITY_ID, charset),
	                   URLEncoder.encode(SmsProperties.TEMPLATE_ID, charset));
	            
   	    
		  LOGGER.error("query -- {}", query);
  	    
		  String url = SmsProperties.API_URL + "?" + query;
    	    
		  LOGGER.error("url -- {}", url);
 	    
		  String pushResponse = RestUtility.sendGetData(url);
  	    
		  LOGGER.error("pushResponse -- {}",pushResponse);
 	    
		  if(pushResponse.contains("Trackid")) {
	    
		      LOGGER.error("SMS SENT SUCCESSFULLY");

		  }
    
      
	      } catch (UnsupportedEncodingException | IntegrationException e) {

		  LOGGER.error("Exception in sendSms: ", e);

	      }
 
	  }

    @Override
    public void close() {
    }
    
    
    public static String phoneNumberWithoutCountryCode(String phoneNumberWithCountryCode){//+91 7698989898
        Pattern compile = Pattern.compile("\\+(?:998|996|995|994|993|992|977|976|975|974|973|972|971|970|968|967|966|965|964|963|962|961|960|886|880|856|855|853|852|850|692|691|690|689|688|687|686|685|683|682|681|680|679|678|677|676|675|674|673|672|670|599|598|597|595|593|592|591|590|509|508|507|506|505|504|503|502|501|500|423|421|420|389|387|386|385|383|382|381|380|379|378|377|376|375|374|373|372|371|370|359|358|357|356|355|354|353|352|351|350|299|298|297|291|290|269|268|267|266|265|264|263|262|261|260|258|257|256|255|254|253|252|251|250|249|248|246|245|244|243|242|241|240|239|238|237|236|235|234|233|232|231|230|229|228|227|226|225|224|223|222|221|220|218|216|213|212|211|98|95|94|93|92|91|90|86|84|82|81|66|65|64|63|62|61|60|58|57|56|55|54|53|52|51|49|48|47|46|45|44\\D?1624|44\\D?1534|44\\D?1481|44|43|41|40|39|36|34|33|32|31|30|27|20|7|1\\D?939|1\\D?876|1\\D?869|1\\D?868|1\\D?849|1\\D?829|1\\D?809|1\\D?787|1\\D?784|1\\D?767|1\\D?758|1\\D?721|1\\D?684|1\\D?671|1\\D?670|1\\D?664|1\\D?649|1\\D?473|1\\D?441|1\\D?345|1\\D?340|1\\D?284|1\\D?268|1\\D?264|1\\D?246|1\\D?242|1)\\D?");
        String number = phoneNumberWithCountryCode.replaceAll(compile.pattern(), "");
        return number;
    }
}

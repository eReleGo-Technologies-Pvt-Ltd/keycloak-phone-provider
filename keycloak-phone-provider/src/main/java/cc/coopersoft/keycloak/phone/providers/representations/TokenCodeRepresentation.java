package cc.coopersoft.keycloak.phone.providers.representations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.logging.Logger;
import org.keycloak.models.utils.KeycloakModelUtils;
import java.security.SecureRandom;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenCodeRepresentation {

    private String id;
    private String phoneNumber;
    private String code;
    private String type;
    private Date createdAt;
    private Date expiresAt;
    private Boolean confirmed;
    
    private static final Logger LOGGER = Logger.getLogger(TokenCodeRepresentation.class);

    public static TokenCodeRepresentation forPhoneNumber(String phoneNumber) {
	
	LOGGER.info("Inside forPhoneNumber");

        TokenCodeRepresentation tokenCode = new TokenCodeRepresentation();

        tokenCode.id = KeycloakModelUtils.generateId();
        
        tokenCode.phoneNumber = phoneNumber;
        
        tokenCode.code = generateTokenCode();
        
        tokenCode.confirmed = false;
        
        LOGGER.debug("Code is - "+tokenCode.code); 
        
        LOGGER.debug("Token id is - "+tokenCode.id); 

        return tokenCode;
    }

    private static String generateTokenCode() {
	
	LOGGER.info("Inside generateTokenCode");
        
	SecureRandom secureRandom = new SecureRandom();
        
        Integer code = secureRandom.nextInt(99_99);

        String tokenCode = String.format("%04d", code);
        
        LOGGER.debug("tokenCode :: "+tokenCode);
        
        return tokenCode;
    }
}

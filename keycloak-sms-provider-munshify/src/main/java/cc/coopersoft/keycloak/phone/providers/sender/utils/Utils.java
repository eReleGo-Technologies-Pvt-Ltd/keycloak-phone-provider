package cc.coopersoft.keycloak.phone.providers.sender.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Utils {
    
	
    public static String convertTemplateToBody(String templateString, Map<String, String> valuesMap) {
	// Regular expression to match placeholders in the template string
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
        
        // Matcher to find matches in the template string
        Matcher matcher = pattern.matcher(templateString);
        
        // StringBuffer to build the result
        StringBuffer result = new StringBuffer();
        
        // Iterate through matches and replace placeholders with values from the map
        while (matcher.find()) {
            String placeholder = matcher.group(1); // Extract placeholder name
            String replacement = valuesMap.get(placeholder); // Get replacement value from the map
            if (replacement != null) {
                matcher.appendReplacement(result, replacement);
            } else {
                // If no replacement found, keep the placeholder as is
                matcher.appendReplacement(result, "\\${" + placeholder + "}");
            }
        }
        
        // Append remaining part of the template string after the last match
        matcher.appendTail(result);
        
        return result.toString();
    }

    public static String getResponseJson(String status, int statusCode, String statusMessage) {
        return "{\"timestamp\": \"" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss"))
                + "\", " + "\"status\": \"" + status + "\", " + "\"statusCode\": " + statusCode + ", "
                + "\"statusMessage\": \"" + statusMessage + "\"}";
    }

    public static boolean patternMatches(String inputString, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(inputString).matches();
    }

}
